package com.example.cis2208mcfoodforge.Database;

public class Favourites {
    private int recipe_id;
    private int user_id;

    public int getRecipe_id() {
        return recipe_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public Favourites(int recipe_id, int user_id) {
        this.recipe_id = recipe_id;
        this.user_id = user_id;
    }
}
