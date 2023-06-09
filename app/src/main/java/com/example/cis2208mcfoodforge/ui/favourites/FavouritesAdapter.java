package com.example.cis2208mcfoodforge.ui.favourites;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.cis2208mcfoodforge.JsonClasses.JsonReader;
import com.example.cis2208mcfoodforge.JsonClasses.Recipe;
import com.example.cis2208mcfoodforge.Database.DbHelper;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.ui.RecipeDetailsActivity;

import java.util.Arrays;
import java.util.List;

public class FavouritesAdapter extends CursorAdapter {
    private final List<Recipe> recipes;

    public FavouritesAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        recipes = Arrays.asList(JsonReader.convertJsonToRecipe(context));
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
                valueTextView.setTag(i);
                break;
            }
        }
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView valueTextView = view.findViewById(R.id.savedFavouritesTextView);
        Button favButton = view.findViewById(R.id.favButton);
        int position = (int) valueTextView.getTag();
        Recipe selectedRecipe = recipes.get(position);

        valueTextView.setText(selectedRecipe.getRecipe_name());

        //tapping on the favourite item redirects you to its details activity
        valueTextView.setOnClickListener(v -> {
            Intent intent = new Intent(context, RecipeDetailsActivity.class);

            //passing individual attributes instead of whole recipe
            //unsure if good practice
            intent.putExtra("recipeName", selectedRecipe.getRecipe_name());
            intent.putExtra("description", selectedRecipe.getRecipe_description());
            intent.putExtra("favouriteCount", selectedRecipe.getFavourite_count());
            intent.putExtra("id", selectedRecipe.getRecipe_id());
            intent.putExtra("difficulty", selectedRecipe.getDifficulty());
            intent.putExtra("author", selectedRecipe.getUser_id());

            context.startActivity(intent);
        });

        //The heart icon in the favourites interface removes an item from the database and refreshes the view
        favButton.setOnClickListener(view1 -> {
            DbHelper dbHelper = new DbHelper(context);
            dbHelper.removeFavourite(selectedRecipe.getRecipe_id());
            Cursor newCursor = dbHelper.getFavourites();
            swapCursor(newCursor);
        });
    }
}







