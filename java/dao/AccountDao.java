package dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.Account;
import model.Database;

public class AccountDao {
    private final Database database;

    public AccountDao(Context context) {
        database = new Database(context);
    }

    public boolean insertAccount(String bankName, double balance, int codUser) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("bankName", bankName);
        content.put("balance", balance);
        content.put("codUser", codUser);

        long result = db.insert(Database.TABLE_ACCOUNT, null, content);
        db.close();

        return result != -1;
    }

    public ArrayList<Account> selectAccount() {
        ArrayList<Account> account = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_ACCOUNT + ";";
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            Account ac = new Account( result.getString(0), result.getDouble(1), result.getInt(2) );
            account.add(ac);
        }
        result.close();
        db.close();

        return account;
    }

    public Account selectOnceAccount(String bankName) {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_ACCOUNT + " where bankName = '" + bankName + "';";
        Cursor result = db.rawQuery(sql, null);
        result.moveToFirst();
        Account account = new Account( result.getString(0), result.getDouble(1), result.getInt(2) );
        result.close();
        db.close();

        return account;
    }

    public void updateBalanceAccount(Account account) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("bankName", account.getBankName() );
        content.put("balance", account.getBalance() );
        content.put("codUser", account.getCodUser() );
        db.update( Database.TABLE_ACCOUNT, content, "bankName = ?", new String[]{account.getBankName()} );

        db.close();

    }

    public Integer deleteAccount(String bankName) {
        SQLiteDatabase db = database.getReadableDatabase();
        int result = db.delete(Database.TABLE_ACCOUNT, "bankName = ?", new String[] {bankName} );
        db.close();

        return result;
    }

}