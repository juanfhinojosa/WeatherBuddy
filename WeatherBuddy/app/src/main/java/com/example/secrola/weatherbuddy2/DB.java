package com.example.secrola.weatherbuddy2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.util.Log;

import java.net.PortUnreachableException;



/**
 * Created by juanhinojosa on 4/29/16.
 */
public class DB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME =  "USER_PASS.db";
    public static final String TABLE_NAME = "USERS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "USERNAME";
    public static final String COL_3 = "PASS";
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
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASS TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, "'"+username+"'");
        contentValues.put(COL_3, "'"+password+"'");
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

    public Cursor getOneData(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where USERNAME == "+"'"+ username+"'", null);
        return res;
    }

    public Cursor getAllData() {
        // Open the database for reading and writing
        SQLiteDatabase db = this.getWritableDatabase();

        // A Cursor represents the result of a query and basically points to one row of the query result.
        // This way Android can buffer the query results efficiently; as it does not have to load all data into memory.
        // the "*" means select "all"
        Cursor res = db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }




}
