package com.trans.junifar.transp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_up);

//        DbUser db = new DbUser(getApplicationContext());
//        db.open();
//        db.insertUser("Junifar Hidayat","08567988422");
//
//        DbUser.User user = db.getUser("Junifar Hidayat");
//        AlertDialog ad = new AlertDialog.Builder(this).create();
//        ad.setMessage("Nama : "+user.name+" Phone : "+user.phone);
//        ad.show();
    }
}
