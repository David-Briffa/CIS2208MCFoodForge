package com.example.cis2208mcfoodforge.ui.favourites;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FavouritesAdapter extends CursorAdapter {
    private List<Integer> ids;

    private List<Recipe> recipes;

    public FavouritesAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
        ids = new ArrayList<>();
        recipes = Arrays.asList(JsonReader.convertJsonToRecipe(context));

    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.favourites_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView valueTextView = view.findViewById(R.id.savedFavouritesTextView);
        int id = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        String recipeName;

        for(Recipe recipe : recipes){
                if(recipe.getRecipe_id() == id){
                    recipeName = recipe.getRecipe_name();
                    System.out.println(recipeName);
                    valueTextView.setText( recipeName);
                }

        }
    }

}