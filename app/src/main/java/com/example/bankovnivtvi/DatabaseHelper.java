package com.example.bankovnivtvi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "finance_db";
    private static final int DATABASE_VERSION = 1;

    // Tabulka pro uživatele
    public static final String TABLE_USERS = "users";
    public static final String USER_ID = "id";
    public static final String USER_USERNAME = "username";
    public static final String USER_PASSWORD = "password";

    // Tabulka pro účty
    public static final String TABLE_ACCOUNTS = "accounts";
    public static final String ACCOUNT_ID = "id";
    public static final String ACCOUNT_USER_ID = "user_id";
    public static final String ACCOUNT_TYPE = "type"; // 'debit' or 'savings'
    public static final String ACCOUNT_BALANCE = "balance";

    // Tabulka pro transakce
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String TRANSACTION_ID = "id";
    public static final String TRANSACTION_ACCOUNT_ID = "account_id";
    public static final String TRANSACTION_AMOUNT = "amount";
    public static final String TRANSACTION_TYPE = "type"; // 'transfer' or 'payment'
    public static final String TRANSACTION_DESCRIPTION = "description";
    public static final String TRANSACTION_PHOTO_PATH = "photo_path";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Vytvoření tabulek při vytvoření databáze
        String createUserTable = "CREATE TABLE " + TABLE_USERS + "("
                + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + USER_USERNAME + " TEXT,"
                + USER_PASSWORD + " TEXT"
                + ")";
        db.execSQL(createUserTable);

        String createAccountsTable = "CREATE TABLE " + TABLE_ACCOUNTS + "("
                + ACCOUNT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ACCOUNT_USER_ID + " INTEGER,"
                + ACCOUNT_TYPE + " TEXT,"
                + ACCOUNT_BALANCE + " REAL"
                + ")";
        db.execSQL(createAccountsTable);

        String createTransactionsTable = "CREATE TABLE " + TABLE_TRANSACTIONS + "("
                + TRANSACTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TRANSACTION_ACCOUNT_ID + " INTEGER,"
                + TRANSACTION_AMOUNT + " REAL,"
                + TRANSACTION_TYPE + " TEXT,"
                + TRANSACTION_DESCRIPTION + " TEXT,"
                + TRANSACTION_PHOTO_PATH + " TEXT"
                + ")";
        db.execSQL(createTransactionsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        onCreate(db);
    }
}
