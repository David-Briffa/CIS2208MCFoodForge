package com.example.cis2208mcfoodforge;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        // Get the food details from the extras
        Intent intent = getIntent();
        String foodName = intent.getStringExtra("recipeName");
        String foodDescription = intent.getStringExtra("description");

        int favouriteCount = intent.getIntExtra("favouriteCount", 0);
        int foodImage = intent.getIntExtra("favouriteCount", 0);
        int difficulty = intent.getIntExtra("difficulty",0);


    }
}
