package com.hojennifer.votefood;

import java.io.Serializable;
import java.util.Comparator;

public class Food implements Serializable, Comparable<Food> {
    public String name;
    public int votes;
    public int drawableID;
    public Food(String name, int votes, int drawableID){
        this.name = name;
        this.votes = votes;
        this.drawableID = drawableID;
    }

    @Override
    public int compareTo(Food otherFood) {
        return otherFood.votes - this.votes;
    }
}
