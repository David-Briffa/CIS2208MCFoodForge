package com.example.cis2208mcfoodforge.ui.favourites;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.RecipeDetailsActivity;

import java.util.Arrays;
import java.util.List;

public class FavouritesAdapter extends CursorAdapter {
    private final List<Recipe> recipes;
    private final Context context;

    public FavouritesAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        recipes = Arrays.asList(JsonReader.convertJsonToRecipe(context));
        this.context = context;

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favourites_list_item, parent, false);
        TextView valueTextView = view.findViewById(R.id.savedFavouritesTextView);
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));

        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            if (recipe.getRecipe_id() == id) {
                valueTextView.setText(recipe.getRecipe_name());
                valueTextView.setTag(i); // Set the position of the recipe as the tag
                break;
            }
        }
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView valueTextView = view.findViewById(R.id.savedFavouritesTextView);
        int position = (int) valueTextView.getTag();

        Recipe selectedRecipe = recipes.get(position);

        valueTextView.setText(selectedRecipe.getRecipe_name());

        valueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start the new activity
                Intent intent = new Intent(context, RecipeDetailsActivity.class);

                // Add any data to the Intent, if needed
                intent.putExtra("recipeName", selectedRecipe.getRecipe_name());
                intent.putExtra("description", selectedRecipe.getRecipe_description());
                intent.putExtra("favouriteCount", selectedRecipe.getFavourite_count());
                intent.putExtra("id", selectedRecipe.getRecipe_id());
                intent.putExtra("difficulty", selectedRecipe.getDifficulty());
                intent.putExtra("author", selectedRecipe.getUser_id());

                context.startActivity(intent);
            }
        });
    }}






