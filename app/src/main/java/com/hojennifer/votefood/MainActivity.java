package com.hojennifer.votefood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Food> foods;
    RecyclerView recyclerView;
    FoodRecyclerViewAdapter foodRecyclerViewAdapter;
    SharedPreferences sharedPrefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        foods = loadFoods();

        recyclerView = findViewById(R.id.rv_foodList);
        foodRecyclerViewAdapter = new FoodRecyclerViewAdapter(foods, this);

        foodRecyclerViewAdapter.notifyDataSetChanged();

        RecyclerView.LayoutManager rvlManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(rvlManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(foodRecyclerViewAdapter);

    }
    public void createDialog(Food food, final int position){
        VoteDialog voteDialog = new VoteDialog(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Bundle bundle = new Bundle();
        bundle.putSerializable("food info", food);
        bundle.putInt("food position", position);
        voteDialog.setArguments(bundle);
        voteDialog.show(fragmentManager, "vote food");
    }
    private ArrayList<Food> loadFoods(){
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String foodArray = sharedPrefs.getString("food list", null);
        if(foodArray == null) {
            ArrayList<Food> foods = new ArrayList<Food>();
            foods.add(new Food("apple", 0, R.drawable.apple));
            foods.add(new Food("burger", 0, R.drawable.burger));
            foods.add(new Food("cake", 0, R.drawable.cake));
            foods.add(new Food("chicken", 0, R.drawable.chicken));
            foods.add(new Food("coffee", 0, R.drawable.coffee));
            foods.add(new Food("croissant", 0, R.drawable.croissant));
            foods.add(new Food("cupcake", 0, R.drawable.cupcake));
            foods.add(new Food("donut", 0, R.drawable.donut));
            foods.add(new Food("fries", 0, R.drawable.fries));
            foods.add(new Food("hotdog", 0, R.drawable.hotdog));
            foods.add(new Food("icecream", 0, R.drawable.icecream));
            foods.add(new Food("kabob", 0, R.drawable.kabob));
            foods.add(new Food("pizza", 0, R.drawable.pizza));
            foods.add(new Food("popcorn", 0, R.drawable.popcorn));
            foods.add(new Food("ramen", 0, R.drawable.ramen));
            foods.add(new Food("sub", 0, R.drawable.sub));
            foods.add(new Food("taco", 0, R.drawable.taco));
            foods.add(new Food("toast", 0, R.drawable.toast));
            return foods;
        }
        else {
            Gson gson = new Gson();
            ArrayList<Food> foods = gson.fromJson(foodArray, new TypeToken<ArrayList<Food>>(){}.getType());
            return foods;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Gson gson = new Gson();
        String arrayData = gson.toJson(foods);
        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sharedPrefs.edit();
        editor.putString("food list", arrayData);
        editor.apply();

    }
}
