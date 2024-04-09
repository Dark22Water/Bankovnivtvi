package com.example.bankovnivtvi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseManager(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    // Metoda pro přidání nového uživatele do databáze
    public long addUser(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USER_USERNAME, username);
        values.put(DatabaseHelper.USER_PASSWORD, password);
        return database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    // Další metody pro manipulaci s databází (účty, transakce, atd.) podle vašich potřeb
    // Například: získání účtů konkrétního uživatele, přidání transakce s fotografií, atd.
}

