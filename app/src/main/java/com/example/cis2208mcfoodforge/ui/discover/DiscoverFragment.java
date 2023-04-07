package com.example.cis2208mcfoodforge.ui.discover;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.Ingredient;
import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.Database.RecipeIngredients;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.SearchedListActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiscoverFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        //three bindings for three recycler views
        RecyclerView dailyDishesRecycler = view.findViewById(R.id.dailyDishesRecyclerView);
        RecyclerView mostFavouritedRecycler = view.findViewById(R.id.mostFavouritedRecyclerView);
        RecyclerView easiestRecipesRecycler = view.findViewById(R.id.easiestRecipesRecyclerView);

        //side scrolling action
        dailyDishesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mostFavouritedRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        easiestRecipesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //reading Json database and storing it as a List
        List<Recipe> recipes = Arrays.asList(JsonReader.convertJsonToRecipe(requireContext()));


        //10 random recipes for the daily dishes recycler view
        Collections.shuffle(recipes);
        List<Recipe> shuffledTemp = new ArrayList<>(recipes);
        List<Recipe> dailyRecipes = shuffledTemp.subList(0, 10);

        //top 10 most favourited recipes
        recipes.sort(Comparator.comparingInt(Recipe::getFavourite_count).reversed());
        List<Recipe> mostFavourited = recipes.subList(0, Math.min(10, recipes.size()));

        //all recipes with the easiest difficulty, difficulty level is stored as part of recipe's json
        List<Recipe> easiestRecipes = new ArrayList<>();
        loadEasiest(easiestRecipes,recipes);

        DiscoverAdapter dailyDishesAdapter = new DiscoverAdapter(dailyRecipes, getContext());
        DiscoverAdapter mostFavouritedAdapter = new DiscoverAdapter(mostFavourited, getContext());
        DiscoverAdapter easiestRecipesAdapter = new DiscoverAdapter(easiestRecipes, getContext());

        dailyDishesRecycler.setAdapter(dailyDishesAdapter);
        mostFavouritedRecycler.setAdapter(mostFavouritedAdapter);
        easiestRecipesRecycler.setAdapter(easiestRecipesAdapter);

        SearchView searchView = view.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                List<Integer> recipeIds = filter(query);
                Intent intent = new Intent(getContext(), SearchedListActivity.class);

                intent.putExtra("recipeIds", new ArrayList<>(recipeIds));
                startActivity(intent);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //do nothing
                return false;
            }
        });
        return view;
    }

    public void loadEasiest(List<Recipe> easiestRecipes , List<Recipe> recipes ){
        for(Recipe recipe : recipes){
            if(recipe.getDifficulty() == 1){
                easiestRecipes.add(recipe);
            }
        }
    }
    private List<Integer> filter(String query) {
        List<RecipeIngredients> recipeIngredients = Arrays.asList(JsonReader.convertJsonToRecipeIngredients(requireContext()));
        List<Ingredient> ingredients = Arrays.asList(JsonReader.convertJsonToIngredient(requireContext()));
        int match;
        List<Integer> recipeIds = new ArrayList<>();
            for (int i=0; i<ingredients.size(); i++) {
                if (ingredients.get(i).getIngredient_name().toLowerCase().contains(query.toLowerCase())) {
                    match = ingredients.get(i).getIngredient_id();
                    for (RecipeIngredients recipeIngredient : recipeIngredients) {
                        if(recipeIngredient.getIngredient_id() == match){
                            recipeIds.add(recipeIngredient.getRecipe_id());                        }
                     }
                }
            }
        return recipeIds;
    }
}
