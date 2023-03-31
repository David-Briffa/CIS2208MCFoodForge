package com.example.cis2208mcfoodforge.ui.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;

import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.RecipeViewHolder> {
    private final List<Recipe> recipeNames;
    public DiscoverAdapter(List<Recipe> recipeNames) {
            this.recipeNames = recipeNames;
        }

        @NonNull
        @Override
        public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recipe_list_item, parent, false);
            return new RecipeViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
            String recipeName = recipeNames.get(position).getRecipe_name();
            holder.bind(recipeName);
        }

        @Override
        public int getItemCount() {
            return recipeNames.size();
        }

        public static class RecipeViewHolder extends RecyclerView.ViewHolder {

            public TextView recipeNameTextView;

            public RecipeViewHolder(@NonNull View itemView) {
                super(itemView);
                recipeNameTextView = itemView.findViewById(R.id.recipeNameTextView);
            }

            public void bind(String recipeName) {
                recipeNameTextView.setText(recipeName);
            }
        }
    }


