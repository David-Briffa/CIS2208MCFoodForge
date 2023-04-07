package com.example.cis2208mcfoodforge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchedListActivity extends AppCompatActivity {


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_list);

        Intent intent = getIntent();
        List<Recipe> recipes = Arrays.asList(JsonReader.convertJsonToRecipe(this));

        //getting recipe ids from intent
        ArrayList<Integer> recipeIds = intent.getIntegerArrayListExtra("recipeIds");
        System.out.println(recipeIds);
    }
}
