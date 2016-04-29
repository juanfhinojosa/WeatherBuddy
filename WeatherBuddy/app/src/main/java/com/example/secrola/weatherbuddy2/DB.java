package com.example.secrola.weatherbuddy2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import java.net.PortUnreachableException;



/**
 * Created by juanhinojosa on 4/29/16.
 */
public class DB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME =  "usr_pass.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "Username";
    public static final String COL_2 = "Password";
    /*
    public static final String COL_3 = "DATE";
    public static final String COL_4 = "LOCATION";
    public static final String COL_5 = "NOTES";
    */

    public DB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(USERNAME TEXT, PASSWORD TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, username);
        contentValues.put(COL_2, password);
        /*
        contentValues.put(COL_3, date);
        contentValues.put(COL_4, location);
        contentValues.put(COL_5, notes);
        */
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getOneData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where Username == "+ id, null);
        return res;
    }




}
