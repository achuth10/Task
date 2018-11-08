package com.example.achuth.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
       Button login;
       EditText loginid,password;
        private final String pass="abc123";
        public  static final String email1=" ",pass1="";
        boolean logincheck=false;
        private final String email="abc123";
        SharedPreferences sharedPreferences;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
             login= findViewById(R.id.loginbutton);
             loginid= findViewById(R.id.getemail);
             password= findViewById(R.id.getpassword);
             sharedPreferences=getApplicationContext().getSharedPreferences("A",0);
             final SharedPreferences.Editor editor= sharedPreferences.edit();
             if(sharedPreferences.contains(email1)) {
                 if(sharedPreferences.contains(pass1))
                 {
                     logincheck=true;
                     changepage();
                 }
                 else
                 loginid.setText(sharedPreferences.getString(email1, ""));
             }
             login.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {

                String logintest =loginid.getText().toString(),passtest =password.getText().toString();
                editor.putString(email1,logintest).apply();
                if(logintest.equals(email))
                {
                    if ((passtest.equals(pass)))
                    {
                        logincheck=true;
                        editor.putString(pass1,passtest).apply();
                        changepage();

                    }
                    else
                    {
                        logincheck=false;
                        changepage();
                    }
                }
                else
                {
                    logincheck=false;
                    changepage();
                }
            }
        });

    }

    public void changepage() {
        if (logincheck) {
            Intent i = new Intent(this, Base.class);
            startActivity(i);
            Toast.makeText(this, "Logged in successfully", Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(this,"Username/Password is incorrect",Toast.LENGTH_SHORT).show();

    }

    public void signup(View view) {
        String url = "http://www.paramhansltd.com/";
        Intent i =new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
