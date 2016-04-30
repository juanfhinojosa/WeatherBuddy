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
    public static final String TABLE_NAME_TWO = "PREF";
    public static final String COL_1_2 = "ID";
    public static final String COL_2_2 = "USER_ID";
    public static final String COL_3_2 = "ONE";
    public static final String COL_4_2 = "TWO";
    public static final String COL_5_2 = "THREE";
    public static final String COL_6_2 = "FOUR";


    public DB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, PASS TEXT);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_TWO + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, USER_ID TEXT, ONE TEXT, TWO TEXT, THREE TEXT, FOUR TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, username);
        contentValues.put(COL_3, password);
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


    public boolean insertDataTwo(String username, String one, String two, String three, String four) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_2, username);
        contentValues.put(COL_3_2, one);
        contentValues.put(COL_4_2, two);
        contentValues.put(COL_5_2, three );
        contentValues.put(COL_6_2, four);
        long result = db.insert(TABLE_NAME_TWO, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getOneDataTwo(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME_TWO + " where USER_ID == "+"'"+ username+"'", null);
        return res;
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
