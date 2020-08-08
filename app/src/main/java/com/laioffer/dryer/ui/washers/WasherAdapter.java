package com.laioffer.dryer.ui.washers;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.dryer.R;
import com.laioffer.dryer.database.Washer;
import com.laioffer.dryer.databinding.WasherElementLayoutBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WasherAdapter extends RecyclerView.Adapter<WasherAdapter.WasherViewHolder>{
    public List<Washer> washers = new ArrayList<>();
    public List<Boolean> isSelected = new ArrayList<>();
    public void setWashers(List<Washer> newList) {
        washers.clear();
        isSelected.clear();
        washers.addAll(newList);
        for (int i = 0; i < washers.size(); i++) {
            isSelected.add(false);
        }
        notifyDataSetChanged();
    }
    public void clear() {
        washers.clear();
        isSelected.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public WasherAdapter.WasherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.washer_element_layout, parent, false);
        return new WasherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WasherAdapter.WasherViewHolder holder, int position) {
        Washer washer = washers.get(position);
        holder.wahserTextView.setText(washer.name);
        holder.washerImageView.setImageResource(R.drawable.ic_kitchen_tools_washer_svgrepo_com);
        if (isSelected.get(position)) {
            holder.cardView.setBackgroundColor(0x80808080);
        }
        else {
            holder.cardView.setBackgroundColor(0xffffffff);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSelected.set(position, !isSelected.get(position));
                if (isSelected.get(position))
                    v.setBackgroundColor(0x80808080);
                else
                    v.setBackgroundColor(0xffffffff);
            }
        });
    }

    @Override
    public int getItemCount() {
        return washers.size();
    }
    public static class WasherViewHolder extends RecyclerView.ViewHolder {
        ImageView washerImageView;
        TextView wahserTextView;
        ConstraintLayout cardView;
        public WasherViewHolder(@NonNull View itemView) {
            super(itemView);
            WasherElementLayoutBinding binding = WasherElementLayoutBinding.bind(itemView);
            washerImageView = binding.washerCardImage;
            wahserTextView = binding.washerCardName;
            cardView = binding.selectableWasherCard;
        }
    }
}
