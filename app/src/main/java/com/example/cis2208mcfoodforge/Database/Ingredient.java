package com.example.cis2208mcfoodforge.Database;

public class Ingredient {
    private final int ingredient_id;
    private final String ingredient_name;

    public Ingredient(int ingredient_id, String ingredient_name) {
        this.ingredient_id = ingredient_id;
        this.ingredient_name = ingredient_name;
    }
    public int getIngredient_id() {
        return ingredient_id;
    }

    public String getIngredient_name() {
        return ingredient_name;
    }
}
