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
import android.widget.ProgressBar;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        private Button login;
        private EditText loginid,password;
        private boolean fraginplace=false;
        private RelativeLayout relativeLayout;
        private AnimationDrawable animationDrawable;
        private SignUpFragment signUpFragment ;
        private FragmentManager fragman;private TextView signup;
        ProgressDialog progressDialog;
        ProgressBar progressBar;

    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    private FirebaseAuth.AuthStateListener authStateListener;

    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        loginid= findViewById(R.id.getemail);
        password= findViewById(R.id.getpassword);
        login= findViewById(R.id.loginbutton);
        signup= findViewById(R.id.signup);
        signUpFragment =new SignUpFragment();

        fragman=getSupportFragmentManager();
         user = firebaseAuth.getCurrentUser();

        //code for transition animation
        relativeLayout = findViewById(R.id.mainframe);
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
                progressBar.setVisibility(View.VISIBLE);
                String logintest = loginid.getText().toString(), passtest = password.getText().toString();
                if (logintest.length() > 0 && passtest.length() > 0) {
                    firebaseAuth.signInWithEmailAndPassword(logintest, passtest).addOnCompleteListener(MainActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login unsuccessful", Toast.LENGTH_SHORT).show();
                            } else {
//                                startActivity(new Intent(MainActivity.this, Base.class));
                                FirebaseUser user1 = firebaseAuth.getCurrentUser();
                                onAuthSuccess(user1);
                            }
                        }
                    });
                    progressDialog.dismiss();
                } else if (logintest.length() == 0) {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(getApplication(), "Please enter a valid username", Toast.LENGTH_SHORT).show();
                    login.requestFocus();
                } else if (passtest.length() == 0) {
                    progressBar.setVisibility(View.GONE);
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

    @Override
    protected void onStart() {
        super.onStart();
        if (user != null) {
            progressBar.setVisibility(View.VISIBLE);
            onAuthSuccess(user);
           }
    }

    private void setfragement(Fragment frag) {
        fraginplace=true;
        FragmentTransaction fragmentTransaction = fragman.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.mainframe, frag).addToBackStack("Frag").commit();
    }
    private void onAuthSuccess(final FirebaseUser user) {

        if (user != null) {
            System.out.println(user.getUid());
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    DataSnapshot contactSnapshot = dataSnapshot.child("Users");
                    Iterable<DataSnapshot> contactChildren = contactSnapshot.getChildren();
                    for (DataSnapshot contact : contactChildren) {
                        User c = contact.getValue(User.class);
                        if(contact.getKey().equals(user.getUid()))
                        {
                            progressBar.setVisibility(View.GONE);
                                changepage(c.getType());
                                Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();


                            break;
                        }
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
    private void changepage(int a)
    {
        Intent i;
        if(a==2)
            i= new Intent(this, Base.class);
        else
            i= new Intent(this, BroadcasterMain.class);
        startActivity(i);

    }
}
