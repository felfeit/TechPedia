package com.felfeit.techpedia.presentation.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.felfeit.techpedia.databinding.ItemSpecKeyValueBinding;
import com.felfeit.techpedia.domain.models.SpecificationDetail;

import java.util.Objects;


public class SpecificationItemAdapter extends ListAdapter<SpecificationDetail, SpecificationItemAdapter.SpecItemViewHolder> {


    public SpecificationItemAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<SpecificationDetail> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override
                public boolean areItemsTheSame(@NonNull SpecificationDetail oldItem, @NonNull SpecificationDetail newItem) {
                    return oldItem.getKey().equals(newItem.getKey());
                }

                @Override
                public boolean areContentsTheSame(@NonNull SpecificationDetail oldItem, @NonNull SpecificationDetail newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };

    @NonNull
    @Override
    public SpecItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSpecKeyValueBinding binding = ItemSpecKeyValueBinding.inflate(inflater, parent, false);
        return new SpecItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecItemViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class SpecItemViewHolder extends RecyclerView.ViewHolder {
        private final ItemSpecKeyValueBinding binding;

        SpecItemViewHolder(@NonNull ItemSpecKeyValueBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(SpecificationDetail spec) {
            binding.tvKey.setText(spec.getKey());
            binding.tvValue.setText(TextUtils.join("\n", spec.getVal()));

            boolean isLastItem = getBindingAdapterPosition() == getItemCount() - 1;
            binding.divider.setVisibility(isLastItem ? View.GONE : View.VISIBLE);
        }
    }
}