package com.example.achuth.task;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SignUpFragment extends Fragment {
private TextView close;
private FragmentManager fragman;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_sign_up, container, false);
        close=v.findViewById(R.id.close);
        fragman=getFragmentManager();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragman.getBackStackEntryCount() == 0) {
                    getActivity().finish();
                } else {
                    fragman.popBackStack();
                }
            }
        });
        return v;
    }

}
