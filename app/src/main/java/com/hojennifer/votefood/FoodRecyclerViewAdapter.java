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
    MainActivity mainActivity;

    public FoodRecyclerViewAdapter(ArrayList<Food> foodList, MainActivity mainActivity){
        this.foods = foodList;
        this.mainActivity = mainActivity;
    }

    final class FoodViewHolder extends RecyclerView.ViewHolder{
        ImageView foodPic;
        TextView name;
        TextView score;
        ImageView star;
        public FoodViewHolder(View view){
            super(view);
            foodPic = view.findViewById(R.id.iv_pic);
            name = view.findViewById(R.id.tv_name);
            score = view.findViewById(R.id.tv_score);
            star = view.findViewById(R.id.iv_star);
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
        final Food food = foods.get(position);
        holder.foodPic.setImageResource(food.drawableID);
        holder.name.setText(food.name);
        holder.score.setText(String.valueOf(food.votes));
        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createDialog(food);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
