package com.example.cis2208mcfoodforge.ui.favourites;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.cis2208mcfoodforge.Database.DbHelper;
import com.example.cis2208mcfoodforge.R;

public class FavouritesFragment extends Fragment {
    private ListView listView;
    private ImageView imageView;
    private ActivityResultLauncher<String> pickImageLauncher;
    private Uri selectedImageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        listView = view.findViewById(R.id.favouritesListView);
        imageView = view.findViewById(R.id.uploadImageView);

        // Restore selected image URI from saved state if exists, so image upload persists through tabs
        if (savedInstanceState != null) {
            selectedImageUri = savedInstanceState.getParcelable("selectedImageUri");
            if (selectedImageUri != null) {
                Glide.with(requireContext()).load(selectedImageUri).into(imageView);
            }
        }

        pickImageLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uri -> {
                    selectedImageUri = uri;
                    Glide.with(requireContext()).load(uri).into(imageView);
                });

        Button button = view.findViewById(R.id.chooseImage);
        button.setOnClickListener(view1 -> pickImageLauncher.launch("image/*"));

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (selectedImageUri != null) {
            outState.putParcelable("selectedImageUri", selectedImageUri);
        }
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
