package com.example.cis2208mcfoodforge;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;

import java.util.Arrays;
import java.util.List;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.SearchedListViewHolder> {
    private List<Recipe> mRecipes;
    private static List<Recipe> mAllRecipes;
    private Context mContext;

    public SearchListAdapter(List<Recipe> recipes, Context context) {
        mRecipes = recipes;
        mAllRecipes = Arrays.asList(JsonReader.convertJsonToRecipe(context));
        mContext = context;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SearchedListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.searched_list_item, parent, false);
        return new SearchedListViewHolder(itemView, mContext);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedListViewHolder holder, int position) {
        Recipe recipe = mRecipes.get(position);
        holder.itemView.setTag(position); // set the tag to the current position
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }

    static class SearchedListViewHolder extends RecyclerView.ViewHolder {
        private final TextView mRecipeNameTextView;
        private final Context context;

        public SearchedListViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            mRecipeNameTextView = itemView.findViewById(R.id.searchResultsTextView);
        }

        public void bind(Recipe recipe) {
            mRecipeNameTextView.setText(recipe.getRecipe_name());

            mRecipeNameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                }
            });
        }

    }
}