package com.example.cis2208mcfoodforge.Database;

import android.content.Context;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class JsonReader {
    public static Ingredient[] convertJsonToIngredient(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/JsonDB/ingredient.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, Ingredient[].class);
    }

    public static User[] convertJsonToUser(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/JsonDB/user.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, User[].class);
    }

    public static RecipeIngredients[] convertJsonToRecipeIngredients(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/JsonDB/recipe_ingredients.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, RecipeIngredients[].class);
    }

    public static Recipe[] convertJsonToRecipe(Context context) {

        String jsonString = "";
        try {
            InputStream inputStream = context.getAssets().open("raw/JsonDB/recipe.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            jsonString = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) { e.printStackTrace(); }

        Gson gson = new Gson();
        return gson.fromJson(jsonString, Recipe[].class);
    }


}
