package dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.ConfigurationCard;
import model.Database;

public class ConfigurationCardDao {
    private final Database database;

    public ConfigurationCardDao(Context c) {
        database = new Database(c);
    }

    public ArrayList<ConfigurationCard> selectConfigurationCard() {
        ArrayList<ConfigurationCard> configCard = new ArrayList<>();
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_CONFIGURATION_CARD + ";";
        Cursor result = db.rawQuery(sql, null);

        while ( result.moveToNext() ) {
            ConfigurationCard ca = new ConfigurationCard( result.getString(0), result.getFloat(1), result.getString(2) );
            configCard.add(ca);
        }

        result.close();
        db.close();
        return configCard;
    }

    public ConfigurationCard selectOnceConfigurationCard(String cardName) {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_CONFIGURATION_CARD + " where cardName = '" + cardName + "';";
        Cursor result = db.rawQuery(sql, null);
        ConfigurationCard configCard = null;

        if ( result.moveToFirst() ) {
            configCard = new ConfigurationCard( result.getString(0), result.getFloat(1), result.getString(2)  );
        }

        result.close();
        db.close();

        return configCard;
    }

    public boolean insertConfigurationCard(String cardName, double credit, String receiptDate) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("cardName", cardName);
        content.put("credit", credit);
        content.put("receiptDate", receiptDate);

        long result = db.insert(Database.TABLE_CONFIGURATION_CARD, null, content);

        db.close();

        return result != -1;
    }

    public boolean updateConfigurationCard(ConfigurationCard configCard) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();

        content.put("cardName", configCard.getCardName());
        content.put("credit", configCard.getCredit());
        content.put("receiptDate", configCard.getReceiptDate() );
        int result = db.update( Database.TABLE_CONFIGURATION_CARD, content, "cardName = ?", new String[] {configCard.getCardName()} );

        db.close();

        return result > 0;
    }
}

