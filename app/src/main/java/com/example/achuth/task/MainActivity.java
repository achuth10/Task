package com.example.achuth.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
        private Button login;
        private EditText loginid,password;
        private boolean logincheck=false,fraginplace=false;
        private SharedPreferences sharedPreferences;
        private RelativeLayout relativeLayout;
        private AnimationDrawable animationDrawable;
        private SignUpFragment signUpFragment ;
        private FragmentManager fragman;
        private TextView signup;
        private  User user;
        private Gson gson;
        private String storeduser;

    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        loginid= findViewById(R.id.getemail);
        password= findViewById(R.id.getpassword);
        login= findViewById(R.id.loginbutton);
        signup=(TextView)findViewById(R.id.signup);
        signUpFragment =new SignUpFragment();
        user=new User();
        gson=new Gson();
        fragman=getSupportFragmentManager();

        //code for animation transition
        relativeLayout = (RelativeLayout) findViewById(R.id.mainframe);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        animationDrawable = (AnimationDrawable) login.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

             //code to check whether to sign in or not based on stored data and logout/back action

             sharedPreferences=getApplicationContext().getSharedPreferences("UserInfo",0);
              final SharedPreferences.Editor editor= sharedPreferences.edit();
             if(sharedPreferences.getString("UserDetail",null)!=null) {
                 storeduser = sharedPreferences.getString("UserDetail", null);
                 if (storeduser != null)
                 {
                     user = gson.fromJson(storeduser, User.class);
                             if (user != null)
                             {
                                 if(sharedPreferences.getString("Login",null).equals("YES"))
                                 {
                                     logincheck = true;
                                     changepage();
                                 }
                             }

                 }

             }
             login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String logintest = loginid.getText().toString(), passtest = password.getText().toString();
                if (logintest.length() > 0 && passtest.length() > 0)
                {
                if (logintest.equals(user.getLoginId())) {

                    if ((passtest.equals(user.getPassword()))) {
                        logincheck = true;
                        changepage();

                    } else {
                        logincheck = false;
                        changepage();
                    }
                } else {
                    logincheck = false;
                    changepage();
                }
            }
            else if(logintest.length()==0)
                Toast.makeText(getApplication(),"Please enter a valid username",Toast.LENGTH_SHORT).show();
            else if(passtest.length()==0)
                    Toast.makeText(getApplication(),"Please enter a valid password",Toast.LENGTH_SHORT).show();
             }
        });

             signup.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     setfragement(signUpFragment);
                 }
             });
    }
    public void changepage() {
        if (logincheck) {
            final SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putString("Login","YES").apply();
            Intent i;
            if(user.isUser())
                i= new Intent(this, Base.class);
            else
                i=new Intent(this,BroadcasterMain.class);
            startActivity(i);
            Toast.makeText(this, "Logged in successfully as " + user.getFirstName() + " " + user.getLastName(), Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(this,"Username/Password is incorrect",Toast.LENGTH_SHORT).show();

    }
    public void onBackPressed() {

        if(fraginplace)
        {
            if (fragman.getBackStackEntryCount() == 0) {
                fraginplace=false;
                this.finish();
            } else {
                fragman.popBackStack();
            }
        }
    }
    private void setfragement(Fragment frag) {
        fraginplace=true;
        FragmentTransaction fragmentTransaction = fragman.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.mainframe, frag).addToBackStack("Frag").commit();
    }
}
