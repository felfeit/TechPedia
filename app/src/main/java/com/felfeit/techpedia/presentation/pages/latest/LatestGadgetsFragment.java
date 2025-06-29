package com.felfeit.techpedia.presentation.pages.latest;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.databinding.FragmentLatestGadgetsBinding;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.presentation.adapter.LatestPhoneAdapter;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LatestGadgetsFragment extends Fragment {
    private FragmentLatestGadgetsBinding binding;
    private LatestGadgetsViewModel viewModel;
    private final LatestPhoneAdapter adapter = new LatestPhoneAdapter();

    public LatestGadgetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLatestGadgetsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(LatestGadgetsViewModel.class);

        setupAdapter();
        observePhones();
    }

    private void setupAdapter() {
        binding.rvLatestGadgets.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvLatestGadgets.setAdapter(adapter);

        adapter.setOnItemClickCallback(phone -> {
            LatestGadgetsFragmentDirections.ActionLatestToPhoneDetail action = LatestGadgetsFragmentDirections.actionLatestToPhoneDetail(phone);
            Navigation.findNavController(requireView()).navigate(action);
        });
    }

    private void observePhones() {
        viewModel.phones.observe(getViewLifecycleOwner(), resource -> {
            if (resource instanceof Resource.Loading) {
                binding.circleLoading.setVisibility(View.VISIBLE);
            } else if (resource instanceof Resource.Success) {
                binding.circleLoading.setVisibility(View.GONE);
                List<Gadget> phones = resource.data;
                adapter.submitList(phones);
            } else if (resource instanceof Resource.Error) {
                binding.circleLoading.setVisibility(View.GONE);
                String errorMessage = resource.message;
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}