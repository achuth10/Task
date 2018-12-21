package com.example.achuth.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

public class SignUpUser extends AppCompatActivity {
    private EditText email,pass,passConfirm,firstName,lastname;
    private Button signup;
    private User user;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private DatabaseReference mDatabase;
    FirebaseAuth firebaseAuth;
    private int pressbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_user);
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        email=(EditText)findViewById(R.id.getemailuser);
        signup=(Button)findViewById(R.id.signupbutton) ;
        pass=(EditText)findViewById(R.id.getpassuser);
        passConfirm=(EditText)findViewById(R.id.getpassconfirm);
        user=new User();
        gson=new Gson();
        pressbutton=getIntent().getIntExtra("Buttonpressed",0);
        firstName=(EditText)findViewById(R.id.getfirstname);
        lastname=(EditText)findViewById(R.id.getlastname);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(pressbutton);
                if(pressbutton!=2)
                    Toast.makeText(getApplication(),"Broadcaster clicked",Toast.LENGTH_SHORT).show();
                if(email.getText().toString().length()==0)
                {
                    Toast.makeText(getApplication(),"Enter a valid email id",Toast.LENGTH_SHORT).show();
                }
                else if(pass.getText().toString().length()<=4)
                {
                    Toast.makeText(getApplication(),"Enter a valid password",Toast.LENGTH_SHORT).show();
                }
                else if(passConfirm.getText().toString().length()==0 || !passConfirm.getText().toString().equals(pass.getText().toString()))
                {
                    Toast.makeText(getApplication(),"Please confirm your password",Toast.LENGTH_SHORT).show();
                }
                else if(firstName.getText().toString().length()==0)
                {
                    Toast.makeText(getApplication(),"Please enter your first name",Toast.LENGTH_SHORT).show();
                }
                else if(lastname.getText().toString().length()==0)
                {
                    Toast.makeText(getApplication(),"Please enter your last name",Toast.LENGTH_SHORT).show();
                }
                else
                        firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnCompleteListener(SignUpUser.this ,new OnCompleteListener() {
                            @Override
                            public void onComplete( Task task) {

                                if (!task.isSuccessful()) {
                                    Toast.makeText(SignUpUser.this.getApplicationContext(),
                                            "SignUp unsuccessful: " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    FirebaseUser user =  firebaseAuth.getCurrentUser();
                                    String userId = user.getUid();
                                    writeNewUser(email.getText().toString(),firstName.getText().toString(),userId,true);
                                    startActivity(new Intent(SignUpUser.this, Base.class));
                                }
                            }
                        });
            }
        });
    }
    private void changepage()
    {
        Intent i;
        if(pressbutton==2)
         i= new Intent(this, Base.class);
        else
            i= new Intent(this, BroadcasterMain.class);
        startActivity(i);

    }
    private void writeNewUser(String email, String name, String userId,Boolean isUser) {
        User user = new User(email,name,isUser);
        mDatabase.child("Users").child(userId).setValue(user);
        mDatabase.push();

    }
}
