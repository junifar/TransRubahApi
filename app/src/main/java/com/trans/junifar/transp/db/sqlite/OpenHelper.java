package com.trans.junifar.transp.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by junifar on 06-Jun-16.
 */
public class OpenHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "MyDB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CREATE = "CREATE TABLE USER" +
            "(" +
            "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "NAME TEXT," +
            "PHONE TEXT" +
            ")";

    public OpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST USER");
    }
}
