package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import model.Database;
import model.User;

public class UserDao {
    private final Database database;

    public UserDao(Context context) {
        database = new Database(context);
    }

    public User selectUser() {
        SQLiteDatabase db = database.getReadableDatabase();
        String sql = "select * from " + Database.TABLE_USER + ";";
        Cursor result =  db.rawQuery(sql, null);

        if ( result.moveToFirst() ) {
            User user = new User( result. getString(1), result.getString(2), result.getInt(0) );
            result.close();
            db.close();
            return user;
        }

        result.close();
        db.close();
        return null;
    }

    public boolean insertUser(String name, String email) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("name", name);
        content.put("email", email);

        long result = db.insert(Database.TABLE_USER, null, content);

        db.close();

        return result != -1;
    }
}