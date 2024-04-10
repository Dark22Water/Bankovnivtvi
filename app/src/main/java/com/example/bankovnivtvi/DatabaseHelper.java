package com.example.bankovnivtvi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "myapp.db";
    private static final int DATABASE_VERSION = 1;

    // Názvy tabulek a sloupců pro tabulku "users"
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    // Názvy tabulek a sloupců pro tabulku "accounts"
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_ACCOUNT_TYPE = "account_type";
    public static final String COLUMN_BALANCE = "balance";

    // Názvy tabulek a sloupců pro tabulku "transactions"
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COLUMN_FROM_ACCOUNT_ID = "from_account_id";
    public static final String COLUMN_TO_ACCOUNT_ID = "to_account_id";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PHOTO_PATH = "photo_path";

    // Konstruktor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Metoda pro vytvoření tabulek při vytvoření databáze
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Vytvoření tabulky "users"
        String createUsersTable = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUsersTable);

        // Vytvoření tabulky "accounts"
        String createAccountsTable = "CREATE TABLE " + TABLE_ACCOUNTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_ID + " INTEGER, " +
                COLUMN_ACCOUNT_TYPE + " TEXT, " +
                COLUMN_BALANCE + " REAL)";
        db.execSQL(createAccountsTable);

        // Vytvoření tabulky "transactions"
        String createTransactionsTable = "CREATE TABLE " + TABLE_TRANSACTIONS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FROM_ACCOUNT_ID + " INTEGER, " +
                COLUMN_TO_ACCOUNT_ID + " INTEGER, " +
                COLUMN_AMOUNT + " REAL, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PHOTO_PATH + " TEXT)";
        db.execSQL(createTransactionsTable);
    }

    // Metoda pro aktualizaci databáze při změně verze
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Odstranění existujících tabulek při aktualizaci
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        // Vytvoření tabulek znovu
        onCreate(db);
    }
}
