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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Dashboard extends Fragment {

    private RecyclerView recyclerView;
    private StreamerAdapter adapter;
    private ArrayList<Streamer> StreamerArrayList;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_dashboard, container, false);
       recyclerView=v.findViewById(R.id.recyclerView);
       recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        StreamerArrayList=new ArrayList<>();
        adapter=new StreamerAdapter(this.getContext(),StreamerArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        createdata();
        recyclerView.setAdapter(adapter);
       return v;
    }

    private void createdata() {
        Streamer streamer = new Streamer("Name", "Esports", 22, true,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 22, true,false);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 24, false,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 18, true,false);
        StreamerArrayList.add(streamer);
        streamer = new Streamer("Name", "Esports", 22, true,false);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 24, false,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 18, true,false);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 32, true,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 26, false,true);
        StreamerArrayList.add(streamer);
        streamer = new Streamer("Name", "Esports", 32, true,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 26, false,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 22, true,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 28, false,true);
        StreamerArrayList.add(streamer);

        streamer = new Streamer("Name", "Esports", 20, true,false);
        StreamerArrayList.add(streamer);

        adapter.notifyDataSetChanged();
    }
    public boolean checker(Streamer streamer)
    {

    return true;
    }
}

