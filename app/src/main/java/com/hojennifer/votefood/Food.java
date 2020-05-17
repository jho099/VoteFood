package com.hojennifer.votefood;

import java.io.Serializable;

public class Food implements Serializable {
    public String name;
    public int votes;
    public int drawableID;
    public Food(String name, int votes, int drawableID){
        this.name = name;
        this.votes = votes;
        this.drawableID = drawableID;
    }
}
