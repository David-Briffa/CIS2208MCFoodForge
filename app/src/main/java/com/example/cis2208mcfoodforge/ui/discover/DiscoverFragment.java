package com.example.cis2208mcfoodforge.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.databinding.FragmentDiscoverBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class DiscoverFragment extends Fragment {

    private RecyclerView dailyDishesRecycler;
    private RecyclerView mostFavouritedRecycler;
    private RecyclerView easiestRecipesRecycler;
    private DiscoverAdapter mostFavouritedAdapter;
    private DiscoverAdapter easiestRecipesAdapter;
    private DiscoverAdapter dailyDishesAdapter;
    private List<Recipe> recipes;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        dailyDishesRecycler = view.findViewById(R.id.dailyDishesRecyclerView);
        mostFavouritedRecycler = view.findViewById(R.id.mostFavouritedRecyclerView);
        easiestRecipesRecycler = view.findViewById(R.id.easiestRecipesRecyclerView);

        dailyDishesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mostFavouritedRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        easiestRecipesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        //reading Json DB data
        recipes = Arrays.asList(JsonReader.convertJsonToRecipe(requireContext()));

        //10 random recipes for the daily dishes recycler view
        Collections.shuffle(recipes);
        List<Recipe> shuffledTemp = new ArrayList<>(recipes);
        List<Recipe> dailyRecipes = shuffledTemp.subList(0, 10);

        //top 10 most favourited recipes
        recipes.sort(Comparator.comparingInt(Recipe::getFavourite_count).reversed());
        List<Recipe> mostFavourited = recipes.subList(0, Math.min(10, recipes.size()));

        //all recipes with the easiest difficulty
        List<Recipe> easiestRecipes = new ArrayList<>();
        for(Recipe recipe : recipes){
            if(recipe.getDifficulty() == 1){
                easiestRecipes.add(recipe);
            }
        }

        dailyDishesAdapter = new DiscoverAdapter(dailyRecipes);
        mostFavouritedAdapter = new DiscoverAdapter(mostFavourited);
        easiestRecipesAdapter = new DiscoverAdapter(easiestRecipes);

        dailyDishesRecycler.setAdapter(dailyDishesAdapter);
        mostFavouritedRecycler.setAdapter(mostFavouritedAdapter);
        easiestRecipesRecycler.setAdapter(easiestRecipesAdapter);

        return view;
    }
}
