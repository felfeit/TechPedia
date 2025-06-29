package com.felfeit.techpedia.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.felfeit.techpedia.databinding.ItemSavedGadgetsBinding;
import com.felfeit.techpedia.domain.models.Gadget;
import com.felfeit.techpedia.utils.Utils;

public class SavedPhoneAdapter extends ListAdapter<Gadget, SavedPhoneAdapter.SavedPhoneViewHolder> {

    public SavedPhoneAdapter() {
        super(Utils.PHONE_DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public SavedPhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSavedGadgetsBinding binding = ItemSavedGadgetsBinding.inflate(inflater, parent, false);
        return new SavedPhoneViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedPhoneViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public interface OnItemClickCallback {
        void onItemClick(Gadget phone);
        void onRemoveClick(Gadget phone);
    }

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback callback) {
        this.onItemClickCallback = callback;
    }

    class SavedPhoneViewHolder extends RecyclerView.ViewHolder {

        private final ItemSavedGadgetsBinding binding;

        SavedPhoneViewHolder(@NonNull ItemSavedGadgetsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.getRoot().setOnClickListener(v -> {
                if (onItemClickCallback != null) {
                    onItemClickCallback.onItemClick(getItem(getBindingAdapterPosition()));
                }
            });

            binding.iconRemoveBookmark.setOnClickListener(v -> {
                if(onItemClickCallback != null) {
                    onItemClickCallback.onRemoveClick(getItem(getBindingAdapterPosition()));
                }
            });
        }

        void bind(Gadget phone) {
            Glide.with(binding.getRoot().getContext())
                    .load(phone.getImage())
                    .into(binding.ivGadgetImage);

            binding.tvPhoneName.setText(phone.getBrand() + " " + phone.getGadgetName());
            binding.tvReleaseInfo.setText(phone.getReleaseInfo());
            binding.tvOsInfo.setText(phone.getOsInfo());
        }
    }
}
