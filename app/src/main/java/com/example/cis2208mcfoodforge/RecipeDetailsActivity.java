package com.example.cis2208mcfoodforge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class RecipeDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView recipeNameView;
    private TextView recipeDescriptionView;
    private TextView recipeDifficultyView;
    private TextView favouriteCountView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //assigning the views
        Intent intent = getIntent();
        //getting food details from extras
        String foodName = intent.getStringExtra("recipeName");
        String foodDescription = intent.getStringExtra("description");
        int favouriteCount = intent.getIntExtra("favouriteCount", 0); // default value 0
        int foodImageId = intent.getIntExtra("id", 0);
        int difficulty = intent.getIntExtra("difficulty",0);

        //imageView = findViewById(R.id.recipeImageView);
        recipeNameView = findViewById(R.id.recipeNameTextView);
        recipeDescriptionView = findViewById(R.id.recipeDescriptionTextView);
        recipeDifficultyView = findViewById(R.id.recipeDifficultyTextView);
        favouriteCountView = findViewById(R.id.favouriteCountTextView);

        recipeNameView.setText(foodName);
        recipeDescriptionView.setText(foodDescription);
        recipeDifficultyView.setText("Difficulty: " + difficulty);
        favouriteCountView.setText("Favourited by: " + favouriteCount + " users");

// Load the image using Glide or any other image loading library
        //Glide.with(this).load(foodImageId).into(imageView);
    }


}
