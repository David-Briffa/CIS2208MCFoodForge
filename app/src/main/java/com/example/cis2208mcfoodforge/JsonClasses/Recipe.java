package com.example.cis2208mcfoodforge.JsonClasses;

//contract class for Recipe json
public class Recipe {
    private final String recipe_description;
    private final String recipe_name;
    private final int favourite_count;
    private final int user_id;
    private final int recipe_id;
    private final int difficulty;

    public String getRecipe_description() {
        return recipe_description;
    }
    public String getRecipe_name() {
        return recipe_name;
    }
    public int getFavourite_count() {
        return favourite_count;
    }
    public int getUser_id() {
        return user_id;
    }
    public int getRecipe_id() {
        return recipe_id;
    }
    public int getDifficulty() { return difficulty; }

    public Recipe(String recipe_description, String recipe_name, int favourite_count, int user_id, int recipe_id, int difficulty) {
        this.difficulty = difficulty;
        this.recipe_description = recipe_description;
        this.recipe_name = recipe_name;
        this.favourite_count = favourite_count;
        this.user_id = user_id;
        this.recipe_id = recipe_id;
    }

}
