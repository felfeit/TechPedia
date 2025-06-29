package com.felfeit.techpedia.presentation.pages.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.felfeit.techpedia.R;
import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.databinding.FragmentGadgetDetailBinding;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.domain.models.GadgetSpecificationDetail;
import com.felfeit.techpedia.presentation.adapter.PhoneImagePagerAdapter;
import com.felfeit.techpedia.presentation.adapter.SpecificationSectionAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhoneDetailFragment extends Fragment {

    private FragmentGadgetDetailBinding binding;
    private PhoneDetailViewModel viewModel;
    private PhoneImagePagerAdapter imagePagerAdapter;
    private SpecificationSectionAdapter sectionAdapter;
    private boolean isSavedState;


    public PhoneDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentGadgetDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PhoneDetailFragmentArgs args = PhoneDetailFragmentArgs.fromBundle(getArguments());
        Gadget selectedPhone = args.getPhoneSpecs();
        isSavedState = selectedPhone.isSaved();

        setAppBarTitle(selectedPhone.getBrand() + " " + selectedPhone.getGadgetName());

        viewModel = new ViewModelProvider(this).get(PhoneDetailViewModel.class);

        viewModel.loadPhoneSpecDetail(selectedPhone.getSlug());
        setupAdapters();
        observePhoneDetail(selectedPhone);
    }

    private void setupAdapters() {
        imagePagerAdapter = new PhoneImagePagerAdapter();
        binding.contentDetail.viewPagerImages.setAdapter(imagePagerAdapter);
        binding.contentDetail.indicator.attachTo(binding.contentDetail.viewPagerImages);

        sectionAdapter = new SpecificationSectionAdapter();
        binding.rvSpecifications.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSpecifications.setAdapter(sectionAdapter);
    }

    private void setupListeners(Gadget phone, GadgetSpecificationDetail detail) {
        binding.contentDetail.btnSave.setOnClickListener(v -> {
            boolean newState = !isSavedState; // toggle state
            phone.setBrand(detail.getBrand());
            phone.setReleaseInfo(detail.getReleaseDate());
            phone.setOsInfo(detail.getOs());
            viewModel.setSavedPhone(phone, newState);

            updateSaveButtonUI(newState);

            String message = newState ? "Added to Saved" : "Removed from Saved";
            Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();

            isSavedState = newState; // update local state
        });
    }

    private void observePhoneDetail(Gadget selectedPhone) {
        viewModel.phoneDetail.observe(getViewLifecycleOwner(), resource -> {
            if (resource instanceof Resource.Loading) {
                showLoadingState();
            } else if (resource instanceof Resource.Success) {
                showContentState(resource.data);
                setupListeners(selectedPhone, resource.data);
            } else if (resource instanceof Resource.Error) {
                showErrorState(resource.message);
            }
        });
    }

    private void showLoadingState() {
        binding.circleLoading.setVisibility(View.VISIBLE);
        binding.containerTopContents.setVisibility(View.GONE);
        binding.rvSpecifications.setVisibility(View.GONE);
    }

    private void showContentState(GadgetSpecificationDetail detail) {
        binding.circleLoading.setVisibility(View.GONE);
        binding.containerTopContents.setVisibility(View.VISIBLE);
        binding.rvSpecifications.setVisibility(View.VISIBLE);

        imagePagerAdapter.submitList(detail.getPhoneImages());

        binding.contentDetail.chipRelease.setText(detail.getReleaseDate());
        binding.contentDetail.chipOs.setText(detail.getOs());
        binding.contentDetail.chipDimension.setText(detail.getDimension());
        binding.contentDetail.chipStorage.setText(detail.getStorage());

        sectionAdapter.submitList(detail.getSpecifications());
        updateSaveButtonUI(isSavedState);
    }

    private void updateSaveButtonUI(boolean isSaved) {
        if (isSaved) {
            binding.contentDetail.btnSave.setText(R.string.saved_text);
            binding.contentDetail.btnSave.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark_fill));
        } else {
            binding.contentDetail.btnSave.setText(R.string.save_gadget_text);
            binding.contentDetail.btnSave.setIcon(ContextCompat.getDrawable(requireContext(), R.drawable.ic_bookmark));
        }
    }

    private void showErrorState(String message) {
        binding.circleLoading.setVisibility(View.GONE);
        Toast.makeText(requireContext(), message != null ? message : "Unknown error", Toast.LENGTH_SHORT).show();
    }

    private void setAppBarTitle(String title) {
        AppCompatActivity activity = (AppCompatActivity) requireActivity();
        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}