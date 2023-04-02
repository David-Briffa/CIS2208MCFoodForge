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

import java.util.List;

public class RecipeDetailsAdapter extends BaseAdapter {
    private Context context;
    private List<Recipe> recipes;
    private List<RecipeIngredients> recipeIngredients;
    private List<Ingredient> ingredients;
    private List<MeasurementQty> measurementQties;
    private List<MeasurementUnit> measurementUnits;

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
            convertView = LayoutInflater.from(context).inflate(R.layout.recipe_details_item, parent, false);
        }

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
                        .override(600))                .into(imageView);
        nameTextView.setText(recipe.getRecipe_name());
        descriptionTextView.setText(recipe.getRecipe_description());
        difficultyTextview.setText(recipe.getDifficulty());
        favouriteCountTextView.setText(recipe.getFavourite_count());

        return convertView;
    }
}
