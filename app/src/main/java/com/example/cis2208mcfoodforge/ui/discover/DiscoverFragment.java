package com.example.cis2208mcfoodforge.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.databinding.FragmentDiscoverBinding;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DiscoverFragment extends Fragment {

    private RecyclerView dailyDishesRecycler;
    private DiscoverAdapter discoverAdapter;
    private List<Recipe> recipes;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        dailyDishesRecycler = view.findViewById(R.id.dailyDishesRecyclerView);
        dailyDishesRecycler.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recipes = Arrays.asList(JsonReader.convertJsonToRecipe(requireContext()));

        discoverAdapter = new DiscoverAdapter(recipes);
        dailyDishesRecycler.setAdapter(discoverAdapter);

        return view;
    }


}