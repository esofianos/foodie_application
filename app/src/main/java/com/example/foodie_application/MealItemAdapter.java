package com.example.foodie_application;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.MyViewHolder> {

    private List<MealItem> mealItems = new ArrayList<MealItem>();

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView descTextView;
        ImageView imageView;

        MyViewHolder(View itemView) {
            super(itemView);
            this.nameTextView = itemView.findViewById(R.id.name);
            this.descTextView = itemView.findViewById(R.id.desc);
            this.imageView = itemView.findViewById(R.id.image_v);
        }

        public Context getContext() {
            return itemView.getContext();
        }
    }

    public MealItemAdapter(List<MealItem> mealItems) {
        this.mealItems = mealItems;
    }

    @NonNull
    @Override
    public MealItemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_layout, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MealItemAdapter.MyViewHolder holder, final int position) {

        TextView nameTextView = holder.nameTextView;
        TextView descTextView = holder.descTextView;
        ImageView imageView = holder.imageView;

        // Setting the values for each view.
        nameTextView.setText(mealItems.get(position).getTitle());
        descTextView.setText(mealItems.get(position).getDesc());

        Glide.with(holder.getContext()).load(mealItems.get(position).getImage()).into(imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String json = gson.toJson(mealItems);
                Intent intent = new Intent(holder.getContext(), DetailActivity.class);
                intent.putExtra("list", json);
                intent.putExtra("pos", position);
                holder.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(holder.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")

                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mealItems.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, mealItems.size());
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mealItems.size();
    }
}

