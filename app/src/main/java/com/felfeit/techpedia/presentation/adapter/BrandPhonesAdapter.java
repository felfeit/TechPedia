package com.felfeit.techpedia.presentation.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.felfeit.techpedia.databinding.ItemGadgetBinding;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.utils.Utils;

public class BrandPhonesAdapter extends ListAdapter<Gadget, BrandPhonesAdapter.BrandPhonesViewHolder> {

    public BrandPhonesAdapter() {
        super(Utils.PHONE_DIFF_CALLBACK);
    }

    public interface OnItemClickCallback {
        void onItemClick(Gadget phone);
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback callback) {
        this.onItemClickCallback = callback;
    }

    @NonNull
    @Override
    public BrandPhonesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGadgetBinding binding = ItemGadgetBinding.inflate(inflater, parent, false);
        return new BrandPhonesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrandPhonesViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class BrandPhonesViewHolder extends RecyclerView.ViewHolder {
        private final ItemGadgetBinding binding;

        BrandPhonesViewHolder(@NonNull ItemGadgetBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickCallback != null) {
                    onItemClickCallback.onItemClick(getItem(getBindingAdapterPosition()));
                }
            });
        }

        void bind(Gadget phone) {
            Glide.with(binding.getRoot().getContext())
                    .load(phone.getImage())
                    .into(binding.ivGadgetImage);

            binding.tvBrandName.setVisibility(View.VISIBLE);
            binding.tvBrandName.setText(phone.getBrand());
            binding.tvGadgetName.setText(phone.getBrand() + phone.getGadgetName());

            binding.tvReleaseInfo.setVisibility(View.GONE);
            binding.tvOsInfo.setVisibility(View.GONE);
        }
    }
}
