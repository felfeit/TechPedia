package com.felfeit.techpedia.presentation.pages.saved;

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

import com.felfeit.techpedia.databinding.FragmentSavedGadgetsBinding;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.presentation.adapter.SavedPhoneAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SavedGadgetsFragment extends Fragment {

    private FragmentSavedGadgetsBinding binding;
    private SavedGadgetsViewModel viewModel;
    private SavedPhoneAdapter adapter;

    public SavedGadgetsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSavedGadgetsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SavedGadgetsViewModel.class);

        setupAdapter();
        setupListeners();
        observeSavedPhones();
    }

    private void setupAdapter() {
        adapter = new SavedPhoneAdapter();
        binding.rvSavedGadgets.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvSavedGadgets.setAdapter(adapter);
    }

    private void setupListeners() {
        adapter.setOnItemClickCallback(
                new SavedPhoneAdapter.OnItemClickCallback() {
                    @Override
                    public void onItemClick(Gadget phone) {
                        SavedGadgetsFragmentDirections.ActionSavedToPhoneDetail action = SavedGadgetsFragmentDirections.actionSavedToPhoneDetail(phone);
                        Navigation.findNavController(requireView()).navigate(action);
                    }

                    @Override
                    public void onRemoveClick(Gadget phone) {
                        phone.setSaved(!phone.isSaved());
                        viewModel.setSavedPhone(phone, false);
                    }
                }
        );
    }

    private void observeSavedPhones() {
        viewModel.phones.observe(getViewLifecycleOwner(), phones -> {
            if (!phones.isEmpty()) {
                adapter.submitList(phones);
            } else {
                binding.tvEmpty.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}