package com.trans.junifar.transp.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by junifar on 06-Jun-16.
 */
public class DbUser {
    private final OpenHelper dbHelper;

    public static class User {
        public String name;
        public String phone;
    }

    private SQLiteDatabase db;

    public DbUser(Context c){
        dbHelper = new OpenHelper(c);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long insertUser(
            String name,
            String phone
    ){
        ContentValues newValues = new ContentValues();
        newValues.put("name",name);
        newValues.put("phone",phone);
        return db.insert("USER",null,newValues);
    }

    public User getUser(String name){
        Cursor cur = null;
        User usr = new User();

        String[] cols = new String[]{"ID","NAME","PHONE"};
        String[] param = {name};

        cur = db.query("USER",cols,"NAME=?",param,null,null,null);

        if (cur.getCount() > 0){
            cur.moveToFirst();
            usr.name = cur.getString(1);
            usr.phone = cur.getString(2);
        }
        return usr;
    }
}
