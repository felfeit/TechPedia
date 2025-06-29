package com.felfeit.techpedia.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.felfeit.techpedia.R;
import com.felfeit.techpedia.databinding.ItemBrandBinding;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.utils.Utils;

public class BrandAdapter extends ListAdapter<Brand, BrandAdapter.BrandViewHolder> {

    public interface OnItemClickCallback {
        void onItemClick(Brand brand);
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback callback) {
        this.onItemClickCallback = callback;
    }

    public BrandAdapter() {
        super(Utils.BRAND_DIFF_CALLBACK);
    }

    class BrandViewHolder extends RecyclerView.ViewHolder {
        private final ItemBrandBinding binding;

        BrandViewHolder(@NonNull ItemBrandBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickCallback != null) {
                    onItemClickCallback.onItemClick(getItem(getBindingAdapterPosition()));
                }
            });
        }

        void bind(Brand brand) {
            binding.tvBrandName.setText(brand.getName());
            binding.tvDeviceCount.setText(brand.getDeviceCount() + " devices");
        }
    }

    @NonNull
    @Override
    public BrandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemBrandBinding binding = ItemBrandBinding.inflate(inflater, parent, false);
        return new BrandViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandViewHolder holder, int position) {
        holder.bind(getItem(position));
    }
}