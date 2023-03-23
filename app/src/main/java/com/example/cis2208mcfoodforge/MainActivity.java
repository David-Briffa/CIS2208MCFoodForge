package com.example.cis2208mcfoodforge;

import android.os.Bundle;
import android.util.Log;

import com.example.cis2208mcfoodforge.Database.Favourites;
import com.example.cis2208mcfoodforge.Database.Ingredient;
import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.MeasurementQty;
import com.example.cis2208mcfoodforge.Database.MeasurementUnit;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.Database.RecipeIngredients;
import com.example.cis2208mcfoodforge.Database.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cis2208mcfoodforge.databinding.ActivityMainBinding;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_discover, R.id.navigation_favourites, R.id.navigation_timer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        Ingredient[] ingredients = JsonReader.convertJsonToIngredient(getApplicationContext());
        MeasurementQty[] measurementQties = JsonReader.convertJsonToMeasurementQty(getApplicationContext());
        MeasurementUnit[] measurementUnits = JsonReader.convertJsonToMeasurementUnit(getApplicationContext());
        User[] users = JsonReader.convertJsonToUser(getApplicationContext());
        RecipeIngredients[] recipeIngredients = JsonReader.convertJsonToRecipeIngredients(getApplicationContext());
        Recipe[] recipes = JsonReader.convertJsonToRecipe(this);
        Favourites[] favourites = JsonReader.convertJsonToFavourites(getApplicationContext());
        
    }
}