package com.example.achuth.task;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
        private Button login;
        private EditText loginid,password;
        private boolean fraginplace=false;
        private RelativeLayout relativeLayout;
        private AnimationDrawable animationDrawable;
        private SignUpFragment signUpFragment ;
        private FragmentManager fragman;
        private TextView signup;
        ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;

    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        loginid= findViewById(R.id.getemail);
        password= findViewById(R.id.getpassword);
        login= findViewById(R.id.loginbutton);
        signup=(TextView)findViewById(R.id.signup);
        signUpFragment =new SignUpFragment();

        fragman=getSupportFragmentManager();
        FirebaseUser user = firebaseAuth.getCurrentUser();
//        onAuthSuccess(user);
        if (user != null) {
            onAuthSuccess(user);
            Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
            Intent I = new Intent(MainActivity.this, Base.class);
            startActivity(I);
        }
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
               // onAuthSuccess(user);
                if (user != null) {
                    Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(MainActivity.this, Base.class);
                    startActivity(I);
                } else {
                    Toast.makeText(MainActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };
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

             login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                progressDialog.show();
                progressDialog.dismiss();
                String logintest = loginid.getText().toString(), passtest = password.getText().toString();
                if (logintest.length() > 0 && passtest.length() > 0) {
                    firebaseAuth.signInWithEmailAndPassword(logintest, passtest).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(MainActivity.this, Base.class));
                            }
                        }
                    });

                    progressDialog.dismiss();
                } else if (logintest.length() == 0) {
                    Toast.makeText(getApplication(), "Please enter a valid username", Toast.LENGTH_SHORT).show();
                    login.requestFocus();
                } else if (passtest.length() == 0) {
                    Toast.makeText(getApplication(), "Please enter a valid password", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                }
            }
        });

             signup.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     setfragement(signUpFragment);
                 }
             });
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
    private void onAuthSuccess(FirebaseUser user) {

        //String username = usernameFromEmail(user.getEmail())
        if (user != null) {
            //Toast.makeText(signinActivity.this, user.getUid(), Toast.LENGTH_SHORT).show();
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String value = (String) dataSnapshot.getValue(Boolean.parseBoolean("isUser"));
                    Log.i("inside fn  "," " + value);
                    //for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                     Toast.makeText(MainActivity.this, value, Toast.LENGTH_SHORT).show();
//                    if(Boolean.(value) == 1) {
//                        //String jason = (String) snapshot.getValue();
//                        //Toast.makeText(signinActivity.this, jason, Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(signinActivity.this, MainActivity.class));
//                        Toast.makeText(signinActivity.this, "You're Logged in as Seller", Toast.LENGTH_SHORT).show();
//                        finish();
//                    } else {
//                        startActivity(new Intent(signinActivity.this, BuyerActivity.class));
//                        Toast.makeText(signinActivity.this, "You're Logged in as Buyer", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }

}
