package com.example.wadamikako.googlemapsapplication01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MyDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "myDatabase.db";
    static final int DATABASE_VERSION = 1;
    static final String TABLE_NAME = "myData";

    static final String ID = "_id";

    static final String ADDRESS = "address";
    static final String PLACE = "place";
    static final String DATE = "date";
    static final String TELL = "tell";
    static final String MEMO = "memo";

    public MyDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        String query = "create table "+ TABLE_NAME + "(" +
                ID + " INTEGER PRIMARY KEY autoincrement,"+
                ADDRESS + " TEXT," +
                PLACE + " TEXT," +
                DATE + " TEXT," +
                TELL + " TEXT," +
                MEMO + " TEXT);";
        db.execSQL(query);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

}
