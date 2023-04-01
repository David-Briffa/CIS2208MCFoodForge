package com.example.cis2208mcfoodforge.ui.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;

import java.util.HashMap;
import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.RecipeViewHolder> {
    private final List<Recipe> recipeNames;
    private HashMap<Integer, String> imageFilenameMap;

    public DiscoverAdapter(List<Recipe> recipeNames) {
            this.recipeNames = recipeNames;
        imageFilenameMap = new HashMap<>();
        imageFilenameMap.put(1, "raw/recipe.json");
        imageFilenameMap.put(2, "image2.jpg");
        imageFilenameMap.put(3, "image1.jpg");
        imageFilenameMap.put(4, "image2.jpg");
        imageFilenameMap.put(5, "image1.jpg");
        imageFilenameMap.put(6, "image2.jpg");
        imageFilenameMap.put(7, "image1.jpg");
        imageFilenameMap.put(8, "image2.jpg");
        imageFilenameMap.put(9, "image1.jpg");
        imageFilenameMap.put(10, "image2.jpg");
        imageFilenameMap.put(11, "image1.jpg");
        imageFilenameMap.put(12, "image2.jpg");
        imageFilenameMap.put(13, "image1.jpg");
        imageFilenameMap.put(14, "image2.jpg");
        imageFilenameMap.put(15, "image1.jpg");
        imageFilenameMap.put(16, "image2.jpg");
        imageFilenameMap.put(17, "image1.jpg");
        imageFilenameMap.put(18, "image2.jpg");
        imageFilenameMap.put(19, "image2.jpg");
        imageFilenameMap.put(20, "image1.jpg");
        imageFilenameMap.put(21, "image2.jpg");
        imageFilenameMap.put(22, "image1.jpg");
        imageFilenameMap.put(23, "image2.jpg");
        imageFilenameMap.put(24, "image1.jpg");
        imageFilenameMap.put(25, "image2.jpg");
        imageFilenameMap.put(26, "image1.jpg");
        imageFilenameMap.put(27, "image2.jpg");
        imageFilenameMap.put(28, "image1.jpg");
        imageFilenameMap.put(29, "image2.jpg");
        imageFilenameMap.put(30, "image1.jpg");

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


