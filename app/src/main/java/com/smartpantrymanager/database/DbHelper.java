package com.smartpantrymanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.Models.PantryItem;
import com.smartpantrymanager.Models.Recipe;

import java.util.ArrayList;
import java.util.List;

/**
 * SQLite database: pantry items (add / edit / delete) and a pre-loaded recipe collection.
 * The 20 recipes are seeded once, the first time the database is created.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "PantryDB";
    private static final int DB_VERSION = 2; // v1 only had pantry(id, name)

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE pantry (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, quantity REAL NOT NULL, unit TEXT, expiry TEXT)");
        db.execSQL("CREATE TABLE recipes (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT NOT NULL, steps TEXT NOT NULL)");
        db.execSQL("CREATE TABLE recipe_ingredients (id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "recipe_id INTEGER NOT NULL, name TEXT NOT NULL, quantity REAL NOT NULL, unit TEXT, "
                + "FOREIGN KEY(recipe_id) REFERENCES recipes(id))");
        RecipeSeeder.seed(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS recipe_ingredients");
        db.execSQL("DROP TABLE IF EXISTS recipes");
        db.execSQL("DROP TABLE IF EXISTS pantry");
        onCreate(db);
    }

    // ---------------------------------------------------------------- pantry CRUD

    public long addPantryItem(Recipe) {
        return getWritableDatabase().insert("pantry", null, values(p));
    }

    public int updatePantryItem(Recipe) {
        return getWritableDatabase().update("pantry", values(p), "id=?", new String[]{String.valueOf(p.id)});
    }

    public void deletePantryItem(long id) {
        getWritableDatabase().delete("pantry", "id=?", new String[]{String.valueOf(id)});
    }

    public PantryItem getPantryItem(long id) {
        try (Cursor c = getReadableDatabase().rawQuery(
                "SELECT id, name, quantity, unit, expiry FROM pantry WHERE id=?", new String[]{String.valueOf(id)})) {
            return c.moveToFirst() ? fromCursor(c) : null;
        }
    }

    public List<PantryItem> getAllPantryItems() {
        List<PantryItem> list = new ArrayList<>();
        try (Cursor c = getReadableDatabase().rawQuery(
                "SELECT id, name, quantity, unit, expiry FROM pantry ORDER BY name COLLATE NOCASE", null)) {
            while (c.moveToNext()) list.add(fromCursor(c));
        }
        return list;
    }

    private static ContentValues values(PantryItem p) {
        ContentValues v = new ContentValues();
        v.put("name", p.name);
        v.put("quantity", p.quantity);
        v.put("unit", p.unit);
        v.put("expiry", p.expiry);
        return v;
    }

    private static PantryItem fromCursor(Cursor c) {
        return new PantryItem(c.getLong(0), c.getString(1), c.getDouble(2), c.getString(3), c.getString(4));
    }

    // ---------------------------------------------------------------- recipes

    public List<Recipe> getAllRecipes() {
        List<Recipe> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor c = db.rawQuery("SELECT id, name, steps FROM recipes ORDER BY name COLLATE NOCASE", null)) {
            while (c.moveToNext()) {
                Recipe r = new Recipe(c.getLong(0), c.getString(1), c.getString(2));
                loadIngredients(db, r);
                list.add(r);
            }
        }
        return list;
    }

    public Recipe getRecipe(long id) {
        SQLiteDatabase db = getReadableDatabase();
        try (Cursor c = db.rawQuery("SELECT id, name, steps FROM recipes WHERE id=?", new String[]{String.valueOf(id)})) {
            if (!c.moveToFirst()) return null;
            Recipe r = new Recipe(c.getLong(0), c.getString(1), c.getString(2));
            loadIngredients(db, r);
            return r;
        }
    }

    private static void loadIngredients(SQLiteDatabase db, Recipe r) {
        try (Cursor c = db.rawQuery("SELECT name, quantity, unit FROM recipe_ingredients WHERE recipe_id=? ORDER BY id",
                new String[]{String.valueOf(r.id)})) {
            while (c.moveToNext()) r.ingredients.add(new RecipeIngredient(c.getString(0), c.getDouble(1), c.getString(2)));
        }
    }
}
