package com.example.dailye;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class FavoriteListFragment extends Fragment {

    WordItemView wordItemView;

    public static FavoriteListFragment getInstance() {
        return new FavoriteListFragment();
    }

    public FavoriteListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment_favorite_list, container, false);
        wordItemView = new WordItemView(getContext());
        wordItemView.makeFavoriteItemList();
        ListView listView = rootView.findViewById(R.id.favorite_list);
        listView.setAdapter(MainActivity.favoriteItemAdapter);
        return rootView;
    }

}
