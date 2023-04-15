package com.example.cis2208mcfoodforge.ui.search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;

import java.util.ArrayList;
import java.util.List;

public class SearchedListActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_list);
        RecyclerView searchedListRecyclerView = findViewById(R.id.searchedListRecyclerView);
        searchedListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Intent intent = getIntent();
        Recipe[] recipes = JsonReader.convertJsonToRecipe(this);

        //getting recipe ids from intent
        List<Recipe> searchResults = new ArrayList<>();
        ArrayList<Integer> recipeIds = intent.getIntegerArrayListExtra("recipeIds");
        if (recipeIds != null) {
            for (Recipe recipe : recipes) {
                for (Integer id : recipeIds) {
                    if (recipe.getRecipe_id() == id) {
                        searchResults.add(recipe);
                    }
                }
            }
        }
        else {
            System.out.println("False");
        }
        SearchListAdapter adapter = new SearchListAdapter(searchResults, this);
        searchedListRecyclerView.setAdapter(adapter);
    }
}


