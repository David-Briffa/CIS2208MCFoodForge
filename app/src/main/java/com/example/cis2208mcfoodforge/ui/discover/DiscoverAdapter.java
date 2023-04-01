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
        imageFilenameMap.put(1, "raw/Images/BeefLasagna.jpg");
        imageFilenameMap.put(2, "raw/Images/ChickenPasta.jpg");
        imageFilenameMap.put(3, "raw/Images/RibeyeMushroom.jpg");
        imageFilenameMap.put(4, "raw/Images/StuffedPork.jpg");
        imageFilenameMap.put(5, "raw/Images/Salmon.webp");
        imageFilenameMap.put(6, "raw/Images/Parmigiana.webp");
        imageFilenameMap.put(7, "raw/Images/CurriedRice.jpg");
        imageFilenameMap.put(8, "raw/Images/ChickenThighs.jpg");
        imageFilenameMap.put(9, "raw/Images/PeanutBars.jpg");
        imageFilenameMap.put(10, "raw/Images/CauliflowerChorizo.jpg");
        imageFilenameMap.put(11, "raw/Images/BroccoliGratin.jpg");
        imageFilenameMap.put(12, "raw/Images/BeefGoulash.jpg");
        imageFilenameMap.put(13, "raw/Images/PotatoSalad.jpg");
        imageFilenameMap.put(14, "raw/Images/CaesarSalad.webp");
        imageFilenameMap.put(15, "raw/Images/PorkChops.jpg");
        imageFilenameMap.put(16, "raw/Images/BeefBrisket.webp");
        imageFilenameMap.put(17, "raw/Images/FarfalleSalmon.webp");
        imageFilenameMap.put(18, "raw/Images/BeefSalad.jpg");
        imageFilenameMap.put(19, "raw/Images/ChickenRice.jpg");
        imageFilenameMap.put(20, "raw/Images/SpagBol.webp");
        imageFilenameMap.put(21, "raw/Images/CrustyBread.jpg");
        imageFilenameMap.put(22, "raw/Images/BaconOnionOmelette.jpg");
        imageFilenameMap.put(23, "raw/Images/mushroomomelette.webp");
        imageFilenameMap.put(24, "raw/Images/Couscous.jpg");
        imageFilenameMap.put(25, "raw/Images/CrispyPotatoes.jpg");
        imageFilenameMap.put(26, "raw/Images/PenneNorma.jpg");
        imageFilenameMap.put(27, "raw/Images/BeefTacos.jpg");
        imageFilenameMap.put(28, "raw/Images/MashedPotatoes.webp");
        imageFilenameMap.put(29, "raw/Images/MushroomSauce.jpg");
        imageFilenameMap.put(30, "raw/Images/PizzaDough.webp");

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


