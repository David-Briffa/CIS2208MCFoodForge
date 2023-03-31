package com.example.cis2208mcfoodforge.ui.discover;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cis2208mcfoodforge.Database.JsonReader;
import com.example.cis2208mcfoodforge.Database.Recipe;
import com.example.cis2208mcfoodforge.R;
import com.example.cis2208mcfoodforge.databinding.FragmentDiscoverBinding;

import java.util.Arrays;
import java.util.Objects;

public class DiscoverFragment extends Fragment {

    private FragmentDiscoverBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DiscoverViewModel discoverViewModel =
                new ViewModelProvider(this).get(DiscoverViewModel.class);
        Recipe[] recipes = JsonReader.convertJsonToRecipe(requireContext());
        binding = FragmentDiscoverBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recipeListRecyclerView = root.findViewById(R.id.dailyDishesRecyclerView);
        DiscoverAdapter recipeListAdapter = new DiscoverAdapter(Arrays.asList(recipes));
        recipeListRecyclerView.setAdapter(recipeListAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}