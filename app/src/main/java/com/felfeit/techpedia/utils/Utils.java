package com.felfeit.techpedia.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.felfeit.techpedia.R;
import com.felfeit.techpedia.domain.models.Brand;
import com.felfeit.techpedia.domain.models.Gadget;

import java.util.Objects;

public class Utils {

    public static final DiffUtil.ItemCallback<Brand> BRAND_DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Brand oldItem, @NonNull Brand newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Brand oldItem, @NonNull Brand newItem) {
            return Objects.equals(oldItem, newItem);
        }
    } ;

    public static final DiffUtil.ItemCallback<Gadget> PHONE_DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Gadget oldItem, @NonNull Gadget newItem) {
            return oldItem.getGadgetName().equals(newItem.getGadgetName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Gadget oldItem, @NonNull Gadget newItem) {
            return Objects.equals(oldItem, newItem);
        }
    };

    public static int getIconForSection(@NonNull String title) {
        switch (title.toLowerCase()) {
            case "network":
                return R.drawable.ic_signal;
            case "launch":
                return R.drawable.ic_calendar_alt;
            case "body":
                return R.drawable.ic_body;
            case "display":
                return R.drawable.ic_display;
            case "platform":
                return R.drawable.ic_chip;
            case "memory":
                return R.drawable.ic_memory;
            case "main camera":
                return R.drawable.ic_camera;
            case "selfie camera":
                return R.drawable.ic_camera_portrait;
            case "sound":
                return R.drawable.ic_speaker;
            case "comms":
                return R.drawable.ic_wifi;
            case "features":
                return R.drawable.ic_fingerprint;
            case "battery":
                return R.drawable.ic_battery_full;
            case "misc":
                return R.drawable.ic_info_square;
            default:
                return R.drawable.ic_info_square; // fallback icon
        }
    }
}
