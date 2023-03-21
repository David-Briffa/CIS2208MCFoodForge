package com.example.cis2208mcfoodforge.Database;

import android.content.Context;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;

public class JsonReader {
    public static Ingredient[] convertJsonToIngredient(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/ingredient.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, Ingredient[].class);
    }
    public static MeasurementQty[] convertJsonToMeasurementQty(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/measurement_qty.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, MeasurementQty[].class);
    }

    public static MeasurementUnit[] convertJsonToMeasurementUnit(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/measurement_unit.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, MeasurementUnit[].class);
    }
    public static User[] convertJsonToUser(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/user.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, User[].class);
    }

    public static RecipeIngredients[] convertJsonToRecipeIngredients(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/recipe_ingredients.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, RecipeIngredients[].class);
    }

    public static Recipe[] convertJsonToRecipe(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/recipe.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, Recipe[].class);
    }

    public static Favourites[] convertJsonToFavourites(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/favourited_list.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, Favourites[].class);
    }
}
