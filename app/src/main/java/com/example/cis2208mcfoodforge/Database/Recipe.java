package com.example.cis2208mcfoodforge.Database;

public class Recipe {
    private String recipe_description;
    private String recipe_name;
    private String favourite_count;
    private int user_id;
    private int recipe_id;

    public String getRecipe_description() {
        return recipe_description;
    }

    public String getRecipe_name() {
        return recipe_name;
    }

    public String getFavourite_count() {
        return favourite_count;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public Recipe(String recipe_description, String recipe_name, String favourite_count, int user_id, int recipe_id) {
        this.recipe_description = recipe_description;
        this.recipe_name = recipe_name;
        this.favourite_count = favourite_count;
        this.user_id = user_id;
        this.recipe_id = recipe_id;
    }
}
