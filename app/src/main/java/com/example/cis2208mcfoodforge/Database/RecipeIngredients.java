package com.example.cis2208mcfoodforge.Database;

//contract class for Recipe Ingredients json
public class RecipeIngredients {

    private final int ingredient_id;
    private final int recipe_id;

    public int getIngredient_id() {
        return ingredient_id;
    }
    public int getRecipe_id() {
        return recipe_id;
    }

    public RecipeIngredients(int ingredient_id, int measurement_qty_id, int measurement_unit_id, int recipe_id) {
        this.ingredient_id = ingredient_id;
        this.recipe_id = recipe_id;
    }
}
