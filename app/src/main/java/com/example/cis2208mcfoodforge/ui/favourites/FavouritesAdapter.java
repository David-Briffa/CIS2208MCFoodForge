package com.example.cis2208mcfoodforge.ui.favourites;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.cis2208mcfoodforge.R;

public class FavouritesAdapter extends CursorAdapter {
    public FavouritesAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.favourites_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView valueTextView = view.findViewById(R.id.savedFavouritesTextView);
        int value = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
        valueTextView.setText(String.valueOf(value));
    }
}