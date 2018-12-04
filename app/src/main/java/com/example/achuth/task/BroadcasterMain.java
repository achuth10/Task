package com.example.achuth.task;

import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class BroadcasterMain extends AppCompatActivity {
    private int count = 0;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    BottomNavigationView bottomNavigationView;
    private BroadcasterAccount broadcasterAccount;
    private BroadcasterViewer broadcasterviewer;
    private BroadcasterDash broadcasterDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcaster_main);
        broadcasterAccount= new BroadcasterAccount();
        broadcasterDash=new BroadcasterDash();
        broadcasterviewer=new BroadcasterViewer();
        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_nav);
        setfragment(broadcasterDash);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setItemBackgroundResource(R.color.colorAccent);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.account:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorbegin);
                        setfragment(broadcasterAccount);
                        return true;
                    case R.id.dashboard:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorAccent);
                        setfragment(broadcasterDash);
                        return true;
                    case R.id.viewers:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setfragment(broadcasterviewer);
                        return true;

                    default:return false;
                }
            }
        });
        sharedPreferences = getApplicationContext().getSharedPreferences("UserInfo", 0);
        editor = sharedPreferences.edit();
    }

    public void onBackPressed() {

        Toast.makeText(this, "Press again to log out", Toast.LENGTH_SHORT).show();
        if (++count >= 2) {
            editor.putString("Login", "NO").commit();
            super.onBackPressed();
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                count = 0;
            }
        }, 2000);
    }
    void setfragment(Fragment fragment)
    {
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainframe,fragment);
        fragmentTransaction.commit();

    }
}

