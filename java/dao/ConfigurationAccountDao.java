package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.ConfigurationAccount;
import model.Database;

public class ConfigurationAccountDao {
    private final Database database;

    public ConfigurationAccountDao(Context c) {
        database = new Database(c);
    }

    public ArrayList<ConfigurationAccount> selectConfigAccount() {
        ArrayList<ConfigurationAccount> configAccount = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_CONFIGURATION_ACCOUNT + ";";
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            ConfigurationAccount ca = new ConfigurationAccount( result.getString(0), result.getFloat(1), result.getString(2) );
            configAccount.add(ca);
        }
        result.close();
        db.close();

        return configAccount;
    }

    public ConfigurationAccount selectOnceConfigurationAccount(String bankName) {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_CONFIGURATION_ACCOUNT + " where bankName = '" + bankName + "';";
        Cursor result = db.rawQuery(sql, null);
        ConfigurationAccount config = null;

        if ( result.moveToFirst() ) {
            config = new ConfigurationAccount( result.getString(0), result.getFloat(1), result.getString(2) );
        }

        result.close();
        db.close();

        return config;
    }

    public boolean insertConfigurationAccount(String bankName, double balance, String receiptDate) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("bankName", bankName);
        content.put("balance", balance);
        content.put("receiptDate", receiptDate);

        long result = db.insert(Database.TABLE_CONFIGURATION_ACCOUNT, null, content);

        db.close();

        return result != -1;
    }

    public boolean updateConfigurationAccount(ConfigurationAccount configAccount) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("bankName", configAccount.getBankName());
        content.put("balance", configAccount.getBalance());
        content.put("receiptDate", configAccount.getReceiptDate() );
        int result = db.update(Database.TABLE_CONFIGURATION_ACCOUNT, content, "bankName = ?", new String[] {configAccount.getBankName()} );

        db.close();

        return result > 0;
    }
}
