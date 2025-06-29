package com.felfeit.techpedia.presentation.pages.browse;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.felfeit.techpedia.data.Resource;
import com.felfeit.techpedia.databinding.FragmentBrowseBrandsBinding;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.presentation.adapter.BrandAdapter;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BrowseBrandFragment extends Fragment {

    private FragmentBrowseBrandsBinding binding;


    private BrowseBrandViewModel viewModel;
    private final BrandAdapter adapter = new BrandAdapter();


    public BrowseBrandFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentBrowseBrandsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BrowseBrandViewModel.class);
        setupRecyclerView();
        observeBrands();
    }

    private void setupRecyclerView() {
        binding.rvBrands.setLayoutManager(new GridLayoutManager(getContext(), 3));
        binding.rvBrands.setHasFixedSize(true);
        binding.rvBrands.setAdapter(adapter);

        adapter.setOnItemClickCallback(brand -> {
            BrowseBrandFragmentDirections.ActionBrowseToBrandPhones action = BrowseBrandFragmentDirections.actionBrowseToBrandPhones(brand);
            Navigation.findNavController(requireView()).navigate(action);
        });
    }

    private void observeBrands() {
        viewModel.brands.observe(getViewLifecycleOwner(), resource -> {
            if (resource instanceof Resource.Loading) {
                binding.circleLoading.setVisibility(View.VISIBLE);
            } else if (resource instanceof Resource.Success) {
                binding.circleLoading.setVisibility(View.GONE);
                List<Brand> brands = resource.data;
                adapter.submitList(brands);
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
