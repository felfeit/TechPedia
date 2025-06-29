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

public class LatestPhoneAdapter extends ListAdapter<Gadget, LatestPhoneAdapter.LatestPhoneViewHolder> {
    public LatestPhoneAdapter() {
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
    public LatestPhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemGadgetBinding binding = ItemGadgetBinding.inflate(inflater, parent, false);
        return new LatestPhoneViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull LatestPhoneViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    class LatestPhoneViewHolder extends RecyclerView.ViewHolder {
        private final ItemGadgetBinding binding;

        LatestPhoneViewHolder(@NonNull ItemGadgetBinding binding) {
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

            binding.tvGadgetName.setText(phone.getGadgetName());

            binding.tvBrandName.setVisibility(View.GONE);
            binding.tvOsInfo.setVisibility(View.GONE);
            binding.tvReleaseInfo.setVisibility(View.GONE);
        }
    }
}
