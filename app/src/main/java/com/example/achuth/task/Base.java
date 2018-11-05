package com.example.achuth.task;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navview;
    private Toolbar myToolbar;
    private Dashboard dashboard;
    private Membership membership;
    private Account account;
    private FrameLayout mainframe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        sharedPreferences = getApplicationContext().getSharedPreferences("A", 0);
        editor = sharedPreferences.edit();
        dashboard=new Dashboard();
        membership =new Membership();
        account=new Account();
        setfragement(dashboard);
        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        mainframe=(FrameLayout)findViewById(R.id.content_frame);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        navview = (NavigationView) findViewById(R.id.nav_view);
        navview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                drawerLayout.closeDrawers();
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.nav_dash:
                        Toast.makeText(Base.this, "Dashboard", Toast.LENGTH_SHORT).show();
                        setfragement(dashboard);
                        return true;
                    case R.id.nav_account:
                        Toast.makeText(Base.this, "My Account", Toast.LENGTH_SHORT).show();
                        setfragement(account);
                        return true;
                    case R.id.nav_memb:
                        Toast.makeText(Base.this, "Membership plans", Toast.LENGTH_SHORT).show();
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
        fragmentTransaction.replace(R.id.content_frame, frag);
        fragmentTransaction.commit();
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
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

}