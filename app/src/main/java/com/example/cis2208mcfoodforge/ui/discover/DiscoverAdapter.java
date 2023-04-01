package com.example.cis2208mcfoodforge.ui.discover;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.RecipeDetailsActivity;

import java.util.HashMap;
import java.util.List;

public class DiscoverAdapter extends RecyclerView.Adapter<DiscoverAdapter.RecipeViewHolder> {
    private final List<Recipe> recipes;
    private HashMap<Integer, String> imageHashMap;
    private Context context;

    public DiscoverAdapter(List<Recipe> recipes, Context context) {
            this.recipes = recipes;
            this.context = context;

        //placing images into a hashmap, the key is the recipe ID, the value is the image path
        imageHashMap = new HashMap<>();
        MapImages(imageHashMap);

        }

        public void MapImages(HashMap<Integer, String> hashmap){
            hashmap.put(1, "raw/Images/BeefLasagna.jpg");
            hashmap.put(2, "raw/Images/ChickenPasta.jpg");
            hashmap.put(3, "raw/Images/RibeyeMushroom.jpg");
            hashmap.put(4, "raw/Images/StuffedPork.jpg");
            hashmap.put(5, "raw/Images/Salmon.webp");
            hashmap.put(6, "raw/Images/Parmigiana.webp");
            hashmap.put(7, "raw/Images/CurriedRice.jpg");
            hashmap.put(8, "raw/Images/ChickenThighs.jpg");
            hashmap.put(9, "raw/Images/PeanutBars.jpg");
            hashmap.put(10, "raw/Images/CauliflowerChorizo.jpg");
            hashmap.put(11, "raw/Images/BroccoliGratin.jpg");
            hashmap.put(12, "raw/Images/BeefGoulash.jpg");
            hashmap.put(13, "raw/Images/PotatoSalad.jpg");
            hashmap.put(14, "raw/Images/CaesarSalad.webp");
            hashmap.put(15, "raw/Images/PorkChops.jpg");
            hashmap.put(16, "raw/Images/BeefBrisket.webp");
            hashmap.put(17, "raw/Images/FarfalleSalmon.webp");
            hashmap.put(18, "raw/Images/BeefSalad.jpg");
            hashmap.put(19, "raw/Images/ChickenRice.jpg");
            hashmap.put(20, "raw/Images/SpagBol.webp");
            hashmap.put(21, "raw/Images/CrustyBread.jpg");
            hashmap.put(22, "raw/Images/BaconOnionOmelette.jpg");
            hashmap.put(23, "raw/Images/mushroomomelette.webp");
            hashmap.put(24, "raw/Images/Couscous.jpg");
            hashmap.put(25, "raw/Images/CrispyPotatoes.jpg");
            hashmap.put(26, "raw/Images/PenneNorma.jpg");
            hashmap.put(27, "raw/Images/BeefTacos.jpg");
            hashmap.put(28, "raw/Images/MashedPotatoes.webp");
            hashmap.put(29, "raw/Images/MushroomSauce.jpg");
            hashmap.put(30, "raw/Images/PizzaDough.webp");
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
            Recipe recipe = recipes.get(position);
            String imageFilename = imageHashMap.get(recipe.getRecipe_id());
            RequestOptions requestOptions = new RequestOptions();

            holder.bind(recipe.getRecipe_name());

            Glide.with(context)
                    .load("file:///android_asset/" + imageFilename)
                    .apply(requestOptions
                            .transforms(new CenterCrop(), new RoundedCorners(40))
                            .override(600))
                    .into(holder.recipeImageView);
            holder.recipeImageView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Recipe selectedRecipe = recipes.get(position);
                    Intent intent = new Intent(context, RecipeDetailsActivity.class);

                    intent.putExtra("recipeName", selectedRecipe.getRecipe_name());
                    intent.putExtra("description", selectedRecipe.getRecipe_description());
                    intent.putExtra("favouriteCount", selectedRecipe.getFavourite_count());
                    intent.putExtra("id", selectedRecipe.getRecipe_id());
                    intent.putExtra("difficulty", selectedRecipe.getDifficulty());

                    // Launch the new activity
                    context.startActivity(intent);
                }
            });
            }


        @Override
        public int getItemCount() {
            return recipes.size();
        }

        public static class RecipeViewHolder extends RecyclerView.ViewHolder {

            public TextView recipeNameTextView;
            public ImageView recipeImageView;

            public RecipeViewHolder(@NonNull View itemView) {
                super(itemView);
                recipeNameTextView = itemView.findViewById(R.id.recipeNameTextView);
                recipeImageView = itemView.findViewById(R.id.recipeImageView);

            }

            public void bind(String recipeName) {
                recipeNameTextView.setText(recipeName);
            }
        }
    }


