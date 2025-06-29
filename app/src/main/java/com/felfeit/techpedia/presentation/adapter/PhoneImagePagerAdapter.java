package com.felfeit.techpedia.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.felfeit.techpedia.databinding.ItemGadgetImageBinding;

public class PhoneImagePagerAdapter extends ListAdapter<String, PhoneImagePagerAdapter.ImageViewHolder> {

    public PhoneImagePagerAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<String> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override
                public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
                    return oldItem.equals(newItem);
                }
            };

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGadgetImageBinding binding = ItemGadgetImageBinding.inflate(inflater, parent, false);
        return new ImageViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder {
        private final ItemGadgetImageBinding binding;

        ImageViewHolder(@NonNull ItemGadgetImageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(String imageUrl) {
            Glide.with(binding.getRoot().getContext())
                    .load(imageUrl)
                    .into(binding.ivGadgetImage);
        }
    }
}
