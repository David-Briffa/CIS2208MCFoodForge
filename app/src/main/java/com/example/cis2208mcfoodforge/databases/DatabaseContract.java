package com.example.cis2208mcfoodforge.databases;

import android.provider.BaseColumns;

public final class DatabaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DatabaseContract() {}

    // Inner class that defines the table contents
    public static class Favourites implements BaseColumns {
        public static final String TABLE_NAME = "favourites";
        public static final String COLUMN_NAME_ID = "recipe_id";
    }
}