package com.example.cis2208mcfoodforge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.Database.RecipeIngredients;

import java.util.ArrayList;
import java.util.List;

public class SearchedListActivity extends AppCompatActivity {

    private List<Recipe> searchResults;

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
        searchResults = new ArrayList<>();
        ArrayList<Integer> recipeIds = intent.getIntegerArrayListExtra("recipeIds");
        if (recipeIds != null) {
            for (Recipe recipe : recipes){
                for(Integer id : recipeIds){
                    if(recipe.getRecipe_id() == id){
                        searchResults.add(recipe);
                    }
                }
            }
        } else {
            System.out.println("False");
        }


        SearchListAdapter adapter = new SearchListAdapter(searchResults,this);
        searchedListRecyclerView.setAdapter(adapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                adapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //do nothing
                return false;
            }
        });
    }

    private List<Integer> filter(String query) {
        List<Integer> recipeIds = new ArrayList<>();
        // Get the list of recipes
        Recipe[] recipes = JsonReader.convertJsonToRecipe(this);
        RecipeIngredients[] recipeIngredients = JsonReader.convertJsonToRecipeIngredients(this);


        // Loop through each recipe to find a match for the query
        for (Recipe recipe : recipes) {
            // Search for the query in the recipe's name or ingredients
            for(RecipeIngredients ingredient : recipeIngredients){
                if (recipe.getRecipe_name().toLowerCase().contains(query.toLowerCase())
                        || recipe.getRecipe_id() == ingredient.getIngredient_id()) {
                    recipeIds.add(recipe.getRecipe_id());
            }

            }
        }
        return recipeIds;
    }
}
