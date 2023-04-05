package com.example.cis2208mcfoodforge.ui.favourites;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cis2208mcfoodforge.DbHelper;
import com.example.cis2208mcfoodforge.R;

public class FavouritesFragment extends Fragment {
    private ListView listView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        listView = view.findViewById(R.id.favouritesListView);
        return view;
    }

    //Queries the user's favourites database and binds them to the view
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DbHelper dbHelper = new DbHelper(getContext());
        Cursor cursor = dbHelper.getFavourites();
        FavouritesAdapter adapter = new FavouritesAdapter(getContext(), cursor);
        listView.setAdapter(adapter);
    }
}