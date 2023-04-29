package com.example.cis2208mcfoodforge.ui.favourites;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cis2208mcfoodforge.Database.DbHelper;
import com.example.cis2208mcfoodforge.R;

public class FavouritesFragment extends Fragment {
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        listView = view.findViewById(R.id.favouritesListView);

        //opens the users' gallery
        Button button = view.findViewById(R.id.chooseImage);
        button.setOnClickListener(view1 -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 3);
        });
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
    //allows users to upload an image for their favourites list
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            ImageView image = getView().findViewById(R.id.uploadImageView);
            image.setImageURI(selectedImage);
        }
    }
}