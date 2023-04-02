package com.example.cis2208mcfoodforge;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.cis2208mcfoodforge.Database.Ingredient;
import com.example.cis2208mcfoodforge.Database.MeasurementQty;
import com.example.cis2208mcfoodforge.Database.MeasurementUnit;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.Database.RecipeIngredients;
import com.example.cis2208mcfoodforge.R;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

public class RecipeDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<Recipe> recipes;
    private List<RecipeIngredients> recipeIngredients;
    private List<Ingredient> ingredients;
    private List<MeasurementQty> measurementQties;
    private List<MeasurementUnit> measurementUnits;
    private HashMap<Integer, String> imageHashMap;

    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Object getItem(int pos) {
        return recipes.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;

    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.activity_recipe_details, parent, false);
        }

        imageHashMap = new HashMap<>();
        MapImages(imageHashMap);

        // Bind the data to the views
        Recipe recipe = (Recipe) getItem(position);
        ImageView imageView = convertView.findViewById(R.id.recipeImageView);
        TextView nameTextView = convertView.findViewById(R.id.recipeNameTextView);
        TextView descriptionTextView = convertView.findViewById(R.id.recipeDescriptionTextView);
        TextView difficultyTextview = convertView.findViewById(R.id.recipeDifficultyTextView);
        TextView favouriteCountTextView = convertView.findViewById(R.id.favouriteCountTextView);

        RequestOptions requestOptions = new RequestOptions();

        Glide.with(context)
                .load("file:///android_asset/" + recipe.getRecipe_id())
                .apply(requestOptions
                        .transforms(new CenterCrop(), new RoundedCorners(40))
                        .override(600)).into(imageView);
        nameTextView.setText(recipe.getRecipe_name());
        descriptionTextView.setText(recipe.getRecipe_description());
        difficultyTextview.setText(recipe.getDifficulty());
        favouriteCountTextView.setText(recipe.getFavourite_count());

        return convertView;
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
}
