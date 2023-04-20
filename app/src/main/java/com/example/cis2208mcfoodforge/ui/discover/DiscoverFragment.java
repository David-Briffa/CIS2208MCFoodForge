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

import com.example.cis2208mcfoodforge.JsonClasses.Ingredient;
import com.example.cis2208mcfoodforge.JsonClasses.JsonReader;
import com.example.cis2208mcfoodforge.JsonClasses.Recipe;
import com.example.cis2208mcfoodforge.JsonClasses.RecipeIngredients;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.ui.search.SearchedListActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DiscoverFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        //reading Json recipe database and storing it as a List
        List<Recipe> recipes = Arrays.asList(JsonReader.convertJsonToRecipe(requireContext()));

        //three bindings for three recycler views
        RecyclerView dailyDishesRecycler = view.findViewById(R.id.dailyDishesRecyclerView);
        RecyclerView mostFavouritedRecycler = view.findViewById(R.id.mostFavouritedRecyclerView);
        RecyclerView easiestRecipesRecycler = view.findViewById(R.id.easiestRecipesRecyclerView);

        //side scrolling action for recycler views
        dailyDishesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mostFavouritedRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        easiestRecipesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        DiscoverAdapter dailyDishesAdapter = new DiscoverAdapter(loadDailyDishes(recipes), getContext());
        DiscoverAdapter mostFavouritedAdapter = new DiscoverAdapter(loadMostFavourited(recipes), getContext());
        DiscoverAdapter easiestRecipesAdapter = new DiscoverAdapter(loadEasiest(recipes), getContext());

        dailyDishesRecycler.setAdapter(dailyDishesAdapter);
        mostFavouritedRecycler.setAdapter(mostFavouritedAdapter);
        easiestRecipesRecycler.setAdapter(easiestRecipesAdapter);

        //query is submitted upon pressing the magnifying glass in the keyboard
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

    //all recipes with the easiest difficulty, difficulty level is stored as part of recipe's json
    public List<Recipe> loadEasiest(List<Recipe> recipes) {
        List<Recipe> easiestRecipes = new ArrayList<>();
        for (Recipe recipe : recipes) {
            if (recipe.getDifficulty() == 1) {
                easiestRecipes.add(recipe);
            }
        }
        return easiestRecipes;
    }

    //10 random recipes for the daily dishes recycler view
    public List<Recipe> loadDailyDishes(List<Recipe> recipes) {
        Collections.shuffle(recipes);
        List<Recipe> shuffledTemp = new ArrayList<>(recipes);
        return shuffledTemp.subList(0, 10);
    }

    //top 10 most recipes with the highest favourite count, count is stored in json
    public List<Recipe> loadMostFavourited(List<Recipe> recipes) {
        recipes.sort(Comparator.comparingInt(Recipe::getFavourite_count).reversed());
        return recipes.subList(0, Math.min(10, recipes.size()));
    }

    // method used by the search view to return recipes containing the searched ingredient
    private List<Integer> filter(String query) {

        List<RecipeIngredients> recipeIngredients = Arrays.asList(JsonReader.convertJsonToRecipeIngredients(requireContext()));
        List<Ingredient> ingredients = Arrays.asList(JsonReader.convertJsonToIngredient(requireContext()));
        int match;
        List<Integer> recipeIds = new ArrayList<>();
        for (int i = 0; i < ingredients.size(); i++) {
            if (ingredients.get(i).getIngredient_name().toLowerCase().contains(query.toLowerCase())) {
                match = ingredients.get(i).getIngredient_id();

                for (RecipeIngredients recipeIngredient : recipeIngredients) {
                    if (recipeIngredient.getIngredient_id() == match) {
                        recipeIds.add(recipeIngredient.getRecipe_id());
                    }
                }
            }
        }
        return recipeIds;
    }
}
