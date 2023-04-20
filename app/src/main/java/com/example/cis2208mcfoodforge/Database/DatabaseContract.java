package com.example.cis2208mcfoodforge.Database;

import android.provider.BaseColumns;
public final class DatabaseContract {

    private DatabaseContract() {}
    public static class Favourites implements BaseColumns {
        public static final String TABLE_NAME = "favourites";
        public static final String COLUMN_NAME_ID = "recipe_id";
    }
}