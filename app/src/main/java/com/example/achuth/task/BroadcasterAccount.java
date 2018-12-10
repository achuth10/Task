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
import com.google.gson.Gson;


public class BroadcasterAccount extends Fragment {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView name;
    private Gson gson;
    private String storeduser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_broadcaster_account, container, false);
        sharedPreferences = getContext().getSharedPreferences("UserInfo", 0);
        editor = sharedPreferences.edit();
        name = v.findViewById(R.id.profilename);
        Button logout = v.findViewById(R.id.broadlogout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("Login", "NO").commit();
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });
        gson = new Gson();
        if (sharedPreferences.getString("UserDetail", null) != null) {
            storeduser = sharedPreferences.getString("UserDetail", null);
            System.out.println(storeduser);
            User temp = gson.fromJson(storeduser, User.class);
            if (temp != null) {
                name.setText(temp.getFirstName() + " " + temp.getLastName());
            }

        }
        return v;
    }
}