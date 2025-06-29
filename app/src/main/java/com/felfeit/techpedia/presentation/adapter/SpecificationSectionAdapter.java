package com.felfeit.techpedia.presentation.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.felfeit.techpedia.databinding.ItemSpecSectionsBinding;
import com.felfeit.techpedia.domain.models.GadgetSpecification;
import com.felfeit.techpedia.utils.Utils;

import java.util.Objects;

public class SpecificationSectionAdapter extends ListAdapter<GadgetSpecification, SpecificationSectionAdapter.SectionViewHolder> {

    public SpecificationSectionAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<GadgetSpecification> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<>() {
                @Override
                public boolean areItemsTheSame(@NonNull GadgetSpecification oldItem, @NonNull GadgetSpecification newItem) {
                    return oldItem.getTitle().equals(newItem.getTitle());
                }

                @Override
                public boolean areContentsTheSame(@NonNull GadgetSpecification oldItem, @NonNull GadgetSpecification newItem) {
                    return Objects.equals(oldItem, newItem);
                }
            };

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemSpecSectionsBinding binding = ItemSpecSectionsBinding.inflate(inflater, parent, false);
        return new SectionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    static class SectionViewHolder extends RecyclerView.ViewHolder {
        private final ItemSpecSectionsBinding binding;

        SectionViewHolder(@NonNull ItemSpecSectionsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(GadgetSpecification specification) {
            int iconResId = Utils.getIconForSection(specification.getTitle());
            binding.ivSectionIcon.setImageResource(iconResId);
            binding.tvSectionTitle.setText(specification.getTitle());

            SpecificationItemAdapter adapter = new SpecificationItemAdapter();
            binding.rvSpecs.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
            binding.rvSpecs.setAdapter(adapter);
            adapter.submitList(specification.getSpecs());
        }
    }
}
