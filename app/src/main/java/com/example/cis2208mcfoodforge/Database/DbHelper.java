package com.example.cis2208mcfoodforge.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

//database helper class for use with the favourites database component of the application
public class DbHelper extends SQLiteOpenHelper {
    private final Context context;

    //database details
    private static final String DATABASE_NAME = "favouritesDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "favourites";
    private static final String RECIPE_ID = "recipe_id";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + RECIPE_ID + " INTEGER PRIMARY KEY);";
        db.execSQL(query);
    }

    //methods for adding or deleting recipes to the user's personal favourite list, used by the heart buttons
    public void addFavourite(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        //content values represents a key-value pair
        ContentValues cv = new ContentValues();
        cv.put(RECIPE_ID, id);

        long result = db.insert(TABLE_NAME, null, cv);

        //this is redundant as you can't favourite a recipe twice with the way the app works, but it is here just in case
        if(result == -1){
            Toast.makeText(context, "This recipe is already favourited", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Favourite saved", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //does what it says
    public void removeFavourite(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        boolean result = db.delete(DatabaseContract.Favourites.TABLE_NAME,
                DatabaseContract.Favourites.COLUMN_NAME_ID + " = ?",
                new String[] { String.valueOf(id) }) > 0;
        Toast.makeText(context, "Favourite removed", Toast.LENGTH_SHORT).show();

        db.close();
    }

    //checks whether a recipe is a favourite for button icon changing purposes
    public boolean isFavoriteButton(int buttonId) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + RECIPE_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(buttonId)});

        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        return isFavorite;
    }

    //Request all favourite dish ids from within the database, used by the favourites fragment to populate the view
    public Cursor getFavourites() {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT recipe_id as _id FROM " + TABLE_NAME;

        return db.rawQuery(query, null);
    }
}