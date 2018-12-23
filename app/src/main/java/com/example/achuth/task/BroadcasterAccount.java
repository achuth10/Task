package com.example.achuth.task;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;


public class BroadcasterAccount extends Fragment {
    private TextView name;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_broadcaster_account, container, false);
        name = v.findViewById(R.id.profilename);
        sharedPreferences=getContext().getSharedPreferences("UserInfo",0);
        Button logout = v.findViewById(R.id.broadlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), MainActivity.class));

            }
        });
        name.setText(sharedPreferences.getString("Name","John Doe"));
        return v;
    }
}