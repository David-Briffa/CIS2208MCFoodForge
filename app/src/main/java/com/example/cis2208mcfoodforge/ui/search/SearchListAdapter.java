package com.example.cis2208mcfoodforge.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.JsonClasses.Recipe;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.ui.RecipeDetailsActivity;

import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchedListViewHolder> {
    private final List<Recipe> recipes;
    private final Context context;

    @SuppressLint("NotifyDataSetChanged")
    public SearchListAdapter(List<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.searched_list_item, parent, false);
        return new SearchedListViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedListViewHolder holder, int position) {
        Recipe recipe = recipes.get(position);
        holder.itemView.setTag(position); // set the tag to the current position
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    static class SearchedListViewHolder extends RecyclerView.ViewHolder {
        private final TextView recipeNameTextView;
        private final Context context;

        public SearchedListViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            recipeNameTextView = itemView.findViewById(R.id.searchResultsTextView);
        }

        public void bind(Recipe recipe) {
            recipeNameTextView.setText(recipe.getRecipe_name());

            recipeNameTextView.setOnClickListener(v -> {
                // Create an Intent to start the new activity
                Intent intent = new Intent(context, RecipeDetailsActivity.class);

                // Add any data to the Intent, if needed
                intent.putExtra("recipeName", recipe.getRecipe_name());
                intent.putExtra("description", recipe.getRecipe_description());
                intent.putExtra("favouriteCount", recipe.getFavourite_count());
                intent.putExtra("id", recipe.getRecipe_id());
                intent.putExtra("difficulty", recipe.getDifficulty());
                intent.putExtra("author", recipe.getUser_id());

                context.startActivity(intent);
            });
        }
    }
}