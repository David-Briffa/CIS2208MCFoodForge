package com.example.cis2208mcfoodforge.Database;

public class RecipeIngredients {

    private int ingredient_id;
    private int measurement_qty_id;
    private int measurement_unit_id;
    private int recipe_id;

    public int getIngredient_id() {
        return ingredient_id;
    }

    public int getMeasurement_qty_id() {
        return measurement_qty_id;
    }

    public int getMeasurement_unit_id() {
        return measurement_unit_id;
    }

    public int getRecipe_id() {
        return recipe_id;
    }

    public RecipeIngredients(int ingredient_id, int measurement_qty_id, int measurement_unit_id, int recipe_id) {
        this.ingredient_id = ingredient_id;
        this.measurement_qty_id = measurement_qty_id;
        this.measurement_unit_id = measurement_unit_id;
        this.recipe_id = recipe_id;
    }
}
