package com.hojennifer.votefood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.FoodViewHolder> {
    ArrayList<Food> foods;

    public FoodRecyclerViewAdapter(ArrayList<Food> foodList){
        this.foods = foodList;

    }

    final class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView foodPic;
        TextView name;
        TextView score;
        public FoodViewHolder(View view){
            super(view);
            foodPic = view.findViewById(R.id.iv_pic);
            name = view.findViewById(R.id.tv_name);
            score = view.findViewById(R.id.tv_score);
        }
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.food_row, parent, false);
        return new FoodViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        Food food = foods.get(position);
        holder.foodPic.setImageResource(food.drawableID);
        holder.name.setText(food.name);
        holder.score.setText(String.valueOf(food.votes));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
