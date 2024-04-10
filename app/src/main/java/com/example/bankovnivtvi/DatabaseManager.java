package com.example.bankovnivtvi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
        values.put(DatabaseHelper.COLUMN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);
        return database.insert(DatabaseHelper.TABLE_USERS, null, values);
    }

    // Metoda pro vytvoření nového účtu pro daného uživatele
    public long addAccount(long userId, String accountType) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_ID, userId);
        values.put(DatabaseHelper.COLUMN_ACCOUNT_TYPE, accountType);
        values.put(DatabaseHelper.COLUMN_BALANCE, 0.0); // Nový účet začíná s nulovým zůstatkem
        return database.insert(DatabaseHelper.TABLE_ACCOUNTS, null, values);
    }

    // Metoda pro získání všech účtů daného uživatele
    public List<Account> getAllAccountsForUser(long userId) {
        List<Account> accountList = new ArrayList<>();
        String query = "SELECT * FROM " + DatabaseHelper.TABLE_ACCOUNTS +
                " WHERE " + DatabaseHelper.COLUMN_USER_ID + " = ?";
        Cursor cursor = database.rawQuery(query, new String[] { String.valueOf(userId) });

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long accountId = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String accountType = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_ACCOUNT_TYPE));
                double balance = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_BALANCE));

                Account account = new Account(accountId, userId, accountType, balance);
                accountList.add(account);
            } while (cursor.moveToNext());

            cursor.close();
        }

        return accountList;
    }

    // Metoda pro provedení transakce mezi účty
    public boolean performTransaction(long fromAccountId, long toAccountId, double amount, String description, String photoPath) {
        double balanceFrom = getAccountBalance(fromAccountId);
        if (balanceFrom >= amount) {
            // Snížení zůstatku z účtu odesílatele
            updateAccountBalance(fromAccountId, balanceFrom - amount);

            // Zvýšení zůstatku na účtu příjemce
            double balanceTo = getAccountBalance(toAccountId);
            updateAccountBalance(toAccountId, balanceTo + amount);

            // Zaznamenání transakce
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.COLUMN_FROM_ACCOUNT_ID, fromAccountId);
            values.put(DatabaseHelper.COLUMN_TO_ACCOUNT_ID, toAccountId);
            values.put(DatabaseHelper.COLUMN_AMOUNT, amount);
            values.put(DatabaseHelper.COLUMN_DESCRIPTION, description);
            values.put(DatabaseHelper.COLUMN_PHOTO_PATH, photoPath);
            database.insert(DatabaseHelper.TABLE_TRANSACTIONS, null, values);

            return true; // Transakce úspěšně provedena
        } else {
            return false; // Nedostatečný zůstatek na účtu odesílatele
        }
    }

    // Metoda pro aktualizaci zůstatku účtu
    private void updateAccountBalance(long accountId, double newBalance) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_BALANCE, newBalance);
        database.update(DatabaseHelper.TABLE_ACCOUNTS, values,
                DatabaseHelper.COLUMN_ID + " = ?",
                new String[] { String.valueOf(accountId) });
    }

    // Metoda pro získání zůstatku daného účtu
    private double getAccountBalance(long accountId) {
        String query = "SELECT " + DatabaseHelper.COLUMN_BALANCE +
                " FROM " + DatabaseHelper.TABLE_ACCOUNTS +
                " WHERE " + DatabaseHelper.COLUMN_ID + " = ?";
        Cursor cursor = database.rawQuery(query, new String[] { String.valueOf(accountId) });

        double balance = 0.0;
        if (cursor != null && cursor.moveToFirst()) {
            balance = cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.COLUMN_BALANCE));
            cursor.close();
        }

        return balance;
    }

    // metoda pro volání user by id
    public long getUserIdByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long userId = -1;

        String[] columns = {DatabaseHelper.COLUMN_ID};
        String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            userId = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
            cursor.close();
        }

        return userId;
    }
}
