package com.hojennifer.votefood;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

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
        EditText voterName;
        public FoodViewHolder(View view){
            super(view);
            foodPic = view.findViewById(R.id.iv_pic);
            name = view.findViewById(R.id.tv_name);
            score = view.findViewById(R.id.tv_score);
            star = view.findViewById(R.id.iv_star);
            voterName = view.findViewById(R.id.et_name);
        }
    }

    public int updateFoods(Food food, int position){



        Food toBeUpdated = foods.get(position);
        toBeUpdated.votes = food.votes;
        Collections.sort(foods);
        int newPosition = 0;
        for(int i = 0; i < foods.size(); i++){
            Food curr = foods.get(i);
            if(curr.drawableID == food.drawableID){
                newPosition = i;
                break;
            }
        }
        notifyDataSetChanged();
        return newPosition;
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
        final int index = position;
        holder.foodPic.setImageResource(food.drawableID);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createDialog(food, index);
            }
        });
        /*holder.foodPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createDialog(food, index);
            }
        });*/
        holder.name.setText(food.name);
        holder.score.setText(String.valueOf(food.votes));
        /*holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.createDialog(food, index);
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
