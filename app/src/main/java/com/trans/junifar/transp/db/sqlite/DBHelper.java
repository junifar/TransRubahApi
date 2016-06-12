package com.trans.junifar.transp.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by junifar on 12-Jun-16.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final int version = '1';
    public static String DBName = "sample";
    public static Context currentContext;
    public static String tableName = "Resource";
    public SQLiteDatabase DB;
    public String DBPath;

    public DBHelper(Context context) {
        super(context, DBName, null, version);
        currentContext = context;
        DBPath = "/data/data/" + context.getPackageName() + "/databases";
        createDatabase();
    }

    private void createDatabase() {
        boolean dbExist = checkDbExists();

        if (!dbExist) {
            DB = currentContext.openOrCreateDatabase(DBName, 0, null);
            DB.execSQL("CREATE TABLE IF NOT EXISTS " +
                    tableName +
                    " (LastName VARCHAR, FirstName VARCHAR," +
                    " Country VARCHAR, Age INT(3));");

            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('M','shumi','India',25);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('C','sarah','India',25);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('D','Lavya','USA',20);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('V','Avi','EU',25);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('T','Shenoi','Bangla',25);");
            DB.execSQL("INSERT INTO " +
                    tableName +
                    " Values ('L','Lamha','Australia',20);");
        }
    }

    private boolean checkDbExists() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DBPath + DBName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch (SQLiteException e) {

        }

        if (checkDB != null) {
            checkDB.close();
        }
        return (checkDB != null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
