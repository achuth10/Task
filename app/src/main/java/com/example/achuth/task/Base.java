package com.example.achuth.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;


public class Base extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private boolean open=false;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navview;
    private Toolbar myToolbar;
    private Dashboard dashboard;
    private Membership membership;
    private int count=0;
    private FrameLayout mainframe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        sharedPreferences = getApplicationContext().getSharedPreferences("A", 0);
        editor = sharedPreferences.edit();
        dashboard=new Dashboard();
        membership =new Membership();
        setfragement(dashboard);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        drawerLayout = findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        navview = findViewById(R.id.nav_view);
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_dash:
                        setfragement(dashboard);
                        return true;
                    case R.id.nav_memb:
                        setfragement(membership);
                        return true;
                    default:
                        return false;
                }


            }
        });

    }
    private void setfragement(Fragment frag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.content_frame, frag).commit();
    }
    public void onBackPressed() {

        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(this, "Press again to log out", Toast.LENGTH_SHORT).show();
            if (++count>=2) {
                editor.clear();
                editor.commit();
                super.onBackPressed();
            }
        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                count=0;
            }
        }, 2000);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_action:
                editor.clear();
                editor.commit();
                getSupportFragmentManager().popBackStack();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case android.R.id.home:
                if(!open) {
                    drawerLayout.openDrawer(GravityCompat.START);
                    open=true;
                    return true;
                }
                else if(open)
                {
                   open=false;
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
                default:
                return super.onOptionsItemSelected(item);

        }
    }

}