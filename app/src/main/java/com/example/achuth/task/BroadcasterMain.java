package com.example.achuth.task;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BroadcasterMain extends AppCompatActivity {
private int count =0;
SharedPreferences sharedPreferences;
SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcaster_main);
        sharedPreferences=getApplicationContext().getSharedPreferences("UserInfo",0);
        editor= sharedPreferences.edit();
    }
    public void onBackPressed() {

            Toast.makeText(this, "Press again to log out", Toast.LENGTH_SHORT).show();
            if (++count>=2) {
                editor.putString("Login","NO").commit();
                super.onBackPressed();
            }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                count=0;
            }
        }, 2000);
    }
}
