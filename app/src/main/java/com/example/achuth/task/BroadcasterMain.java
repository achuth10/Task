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
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;


public class BroadcasterMain extends AppCompatActivity {
    private int count = 0;
    BottomNavigationView bottomNavigationView;
    private BroadcasterAccount broadcasterAccount;
    private BroadcasterViewer broadcasterviewer;
    private BroadcasterDash broadcasterDash;
    FirebaseAuth firebaseAuth;
    private int Measuredwidth, Measuredheight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcaster_main);

        DisplayMetrics metrics = new DisplayMetrics();   //for all android versions
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
         Measuredwidth  = metrics.widthPixels;
         Measuredheight = metrics.heightPixels;
        broadcasterAccount= new BroadcasterAccount();
        broadcasterDash=new BroadcasterDash();
        broadcasterviewer=new BroadcasterViewer();
        bottomNavigationView= findViewById(R.id.bottom_nav);
        setfragment(broadcasterDash);
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setItemBackgroundResource(R.color.colorbegin);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.account:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorend);
                        setfragment(broadcasterAccount);
                        return true;
                    case R.id.dashboard:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorbegin);
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
    }

    public void onBackPressed() {

        Toast.makeText(this, "Press again to log out", Toast.LENGTH_SHORT).show();
        if (++count >= 2) {
            FirebaseAuth.getInstance().signOut();
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
    public void setNavigationVisibility(boolean visible) {
        if (bottomNavigationView.isShown() && !visible) {
            bottomNavigationView.setVisibility(View.GONE);
        }
        else if (!bottomNavigationView.isShown() && visible){
            bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }
    private void slideUp(BottomNavigationView child) {
        child.clearAnimation();
        child.animate().translationY(0).setDuration(200);
    }

    private void slideDown(BottomNavigationView child) {
        child.clearAnimation();
        child.animate().translationY(Measuredheight).setDuration(200);
    }
}

