package com.example.cis2208mcfoodforge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.Database.RecipeIngredients;
import com.example.cis2208mcfoodforge.ui.discover.DiscoverFragment;
import com.example.cis2208mcfoodforge.ui.favourites.FavouritesFragment;
import com.example.cis2208mcfoodforge.ui.timer.TimerFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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

        //back button to return to discover
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

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
        } else {
            System.out.println("False");
        }
        SearchListAdapter adapter = new SearchListAdapter(searchResults, this);
        searchedListRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}


