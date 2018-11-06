package com.example.achuth.task;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class Dashboard extends Fragment {

    private RecyclerView recyclerView;
    private CheckBox asbox,vsbox;
    private StreamerAdapter adapter;
    private boolean assort=false,vssort=false;
    String value;
    public ArrayList<Streamer> tempas,tempvs,temp;
    public ArrayList<Streamer> StreamerArrayList;
    private Switch aSwitch;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        asbox=v.findViewById(R.id.audiostreamercheck);
        vsbox=v.findViewById(R.id.videostreamercheck);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        StreamerArrayList = new ArrayList<>();
        tempas = new ArrayList<>();
        temp=new ArrayList<>();
        tempvs=new ArrayList<>();
        asbox.setChecked(false);
        vsbox.setChecked(false);
        setAdapter(StreamerArrayList);
        createdata();
        sort();
        asbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asbox.isChecked() && !vsbox.isChecked()) {
                    setAdapter(tempas);
                }
                else if(!asbox.isChecked() && vsbox.isChecked())
                {
                    setAdapter(tempvs);
                }
                else if(!asbox.isChecked() && !vsbox.isChecked())
                {
                    setAdapter(StreamerArrayList);
                }
                else if(asbox.isChecked() && vsbox.isChecked())
                {
                    setAdapter(temp);
                }
            }
        });
        vsbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (asbox.isChecked() && !vsbox.isChecked()) {
                    setAdapter(tempas);
                }
                else if(!asbox.isChecked() && vsbox.isChecked())
                {
                    setAdapter(tempvs);
                }
                else if(!asbox.isChecked() && !vsbox.isChecked())
                {
                    setAdapter(StreamerArrayList);
                }
                else if(asbox.isChecked() && vsbox.isChecked())
                {
                    setAdapter(temp);
                }
            }
        });
        return v;
    }

    public void createdata() {
        Streamer streamer = new Streamer("Name", "Esports", 22, true, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 22, true, false);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 24, false, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 18, true, false);
        StreamerArrayList.add(streamer);
        streamer = new Streamer("Name", "Esports", 22, true, false);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 24, false, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 18, true, false);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 32, true, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 26, false, true);
        StreamerArrayList.add(streamer);
        streamer = new Streamer("Name", "Esports", 32, true, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 26, false, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 22, true, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 28, false, true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 20, true, false);
        StreamerArrayList.add(streamer);

        adapter.notifyDataSetChanged();
    }

    public void sort(){

            for (int i = 0; i < StreamerArrayList.size(); i++) {
                if(StreamerArrayList.get(i).isVstreamer()&&StreamerArrayList.get(i).isAstreamer())
                {
                    temp.add(StreamerArrayList.get(i));
                }
                else if (StreamerArrayList.get(i).isVstreamer()) {
                    tempvs.add(StreamerArrayList.get(i));

                }
                else if(StreamerArrayList.get(i).isAstreamer())
                {
                    tempas.add(StreamerArrayList.get(i));
                }
            }
        }


    public void setAdapter(ArrayList<Streamer> streamers)
    {
        adapter = new StreamerAdapter(this.getContext(), streamers);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setAdapter(adapter);
    }

}

