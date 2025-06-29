package com.felfeit.techpedia.presentation.pages.gadgets;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.databinding.FragmentBrandGadgetsBinding;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.presentation.adapter.BrandPhonesAdapter;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BrandGadgetsFragment extends Fragment {
    private FragmentBrandGadgetsBinding binding;
    private BrandGadgetsViewModel viewModel;
    private final BrandPhonesAdapter adapter = new BrandPhonesAdapter();

    public BrandGadgetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBrandGadgetsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BrandGadgetsFragmentArgs args = BrandGadgetsFragmentArgs.fromBundle(getArguments());
        Brand selectedBrand = args.getBrand();

        setAppBarTitle(selectedBrand.getName() + " Gadgets");

        viewModel = new ViewModelProvider(this).get(BrandGadgetsViewModel.class);
        setupAdapter();

        viewModel.loadBrandPhones(selectedBrand.getSlug());
        observeBrandPhones();
    }

    private void setupAdapter() {
        binding.rvBrandGadgets.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvBrandGadgets.setAdapter(adapter);

        adapter.setOnItemClickCallback(phone -> {
            BrandGadgetsFragmentDirections.ActionBrandPhonesToPhoneDetail action = BrandGadgetsFragmentDirections.actionBrandPhonesToPhoneDetail(phone);
            Navigation.findNavController(requireView()).navigate(action);
        });
    }

    private void observeBrandPhones() {
        viewModel.brandPhones.observe(getViewLifecycleOwner(), resource -> {
            if (resource instanceof Resource.Loading) {
                showLoading(true);
            } else if (resource instanceof Resource.Success) {
                showLoading(false);
                List<Gadget> phones = resource.data;
                adapter.submitList(phones);
            } else if (resource instanceof Resource.Error) {
                showLoading(false);
                String message = resource.message;
                Toast.makeText(requireContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showLoading(boolean isLoading) {
        binding.circleLoading.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        binding.rvBrandGadgets.setVisibility(isLoading ? View.GONE : View.VISIBLE);
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