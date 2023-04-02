package com.example.cis2208mcfoodforge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


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
        String favouriteCount = intent.getStringExtra("favouriteCount");
        String foodImageId = intent.getStringExtra("id");
        String difficulty = intent.getStringExtra("difficulty");

        imageView = findViewById(R.id.recipeImageView);
        recipeNameView = findViewById(R.id.recipeNameTextView);
        recipeDescriptionView = findViewById(R.id.recipeDescriptionTextView);
        recipeDifficultyView = findViewById(R.id.recipeDifficultyTextView);
        favouriteCountView = findViewById(R.id.favouriteCountTextView);


        //assigning the content
        recipeNameView.setText(foodName);
        recipeDescriptionView.setText(foodDescription);
        recipeDifficultyView.setText(difficulty);
        favouriteCountView.setText(favouriteCount);
    }


}
