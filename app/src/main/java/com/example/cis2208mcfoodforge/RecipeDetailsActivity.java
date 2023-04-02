package com.example.cis2208mcfoodforge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.cis2208mcfoodforge.Database.Ingredient;
import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.Database.RecipeIngredients;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class RecipeDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView recipeNameView;
    private TextView recipeDescriptionView;
    private TextView recipeDifficultyView;
    private TextView favouriteCountView;

    private HashMap<Integer, String> imageHashMap;
    private List<Ingredient> ingredients;
    private List<Recipe> recipes;
    private List<RecipeIngredients> recipeIngredients;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);
        ingredients = Arrays.asList(JsonReader.convertJsonToIngredient(this));
        recipes = Arrays.asList(JsonReader.convertJsonToRecipe(this));
        recipeIngredients = Arrays.asList(JsonReader.convertJsonToRecipeIngredients(this));


        imageHashMap = new HashMap<>();
        MapImages(imageHashMap);
        //assigning the views
        Intent intent = getIntent();
        //getting food details from extras
        String foodName = intent.getStringExtra("recipeName");
        String foodDescription = intent.getStringExtra("description");
        int favouriteCount = intent.getIntExtra("favouriteCount", 0); // default value 0
        int foodImageId = intent.getIntExtra("id", 0);
        int difficulty = intent.getIntExtra("difficulty",0);
        List<Integer> ingredientsUsed = new ArrayList<>();
        String imageFilename = imageHashMap.get(foodImageId);

            for(RecipeIngredients recIngredient : recipeIngredients) {
                int commonIng = recIngredient.getRecipe_id();
                if (commonIng == foodImageId) {
                    ingredientsUsed.add(recIngredient.getIngredient_id());
                    System.out.println(ingredientsUsed.size());
                }
            }
            for(int id : ingredientsUsed){
                for(int i=0; i<ingredients.size(); i++){
                    if(ingredients.get(i).getIngredient_id() == id){
                        System.out.println(ingredients.get(i).getIngredient_name());
                    }
                }
            }

        imageView = findViewById(R.id.recipeImageView);
        recipeNameView = findViewById(R.id.recipeNameTextView);
        recipeDescriptionView = findViewById(R.id.recipeDescriptionTextView);
        recipeDifficultyView = findViewById(R.id.recipeDifficultyTextView);
        favouriteCountView = findViewById(R.id.favouriteCountTextView);

        recipeNameView.setText(foodName);
        recipeDescriptionView.setText(foodDescription);
        recipeDifficultyView.setText("Difficulty: " + difficulty);
        favouriteCountView.setText("Favourited by: " + favouriteCount + " users");

        RequestOptions requestOptions = new RequestOptions();

        // Load the image using Glide or any other image loading library
        Glide.with(this)
                .load("file:///android_asset/" + imageFilename)
                .apply(requestOptions
                        .transforms(new CenterCrop(), new RoundedCorners(40))
                        .override(600))
                .into(imageView);
    }

    public void MapImages(HashMap<Integer, String> hashmap){
        hashmap.put(1, "raw/Images/BeefLasagna.jpg");
        hashmap.put(2, "raw/Images/ChickenPasta.jpg");
        hashmap.put(3, "raw/Images/RibeyeMushroom.jpg");
        hashmap.put(4, "raw/Images/StuffedPork.jpg");
        hashmap.put(5, "raw/Images/Salmon.webp");
        hashmap.put(6, "raw/Images/Parmigiana.webp");
        hashmap.put(7, "raw/Images/CurriedRice.jpg");
        hashmap.put(8, "raw/Images/ChickenThighs.jpg");
        hashmap.put(9, "raw/Images/PeanutBars.jpg");
        hashmap.put(10, "raw/Images/CauliflowerChorizo.jpg");
        hashmap.put(11, "raw/Images/BroccoliGratin.jpg");
        hashmap.put(12, "raw/Images/BeefGoulash.jpg");
        hashmap.put(13, "raw/Images/PotatoSalad.jpg");
        hashmap.put(14, "raw/Images/CaesarSalad.webp");
        hashmap.put(15, "raw/Images/PorkChops.jpg");
        hashmap.put(16, "raw/Images/BeefBrisket.webp");
        hashmap.put(17, "raw/Images/FarfalleSalmon.webp");
        hashmap.put(18, "raw/Images/BeefSalad.jpg");
        hashmap.put(19, "raw/Images/ChickenRice.jpg");
        hashmap.put(20, "raw/Images/SpagBol.webp");
        hashmap.put(21, "raw/Images/CrustyBread.jpg");
        hashmap.put(22, "raw/Images/BaconOnionOmelette.jpg");
        hashmap.put(23, "raw/Images/mushroomomelette.webp");
        hashmap.put(24, "raw/Images/Couscous.jpg");
        hashmap.put(25, "raw/Images/CrispyPotatoes.jpg");
        hashmap.put(26, "raw/Images/PenneNorma.jpg");
        hashmap.put(27, "raw/Images/BeefTacos.jpg");
        hashmap.put(28, "raw/Images/MashedPotatoes.webp");
        hashmap.put(29, "raw/Images/MushroomSauce.jpg");
        hashmap.put(30, "raw/Images/PizzaDough.webp");
    }

}
