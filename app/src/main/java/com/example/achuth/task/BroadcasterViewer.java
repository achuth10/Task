package com.example.achuth.task;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class BroadcasterViewer extends Fragment {
    private RecyclerView recyclerView;
    private ViewerAdapter adapter;
    public ArrayList<Viewer> ViewerArrayList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_broadcaster_viewer, container, false);
        recyclerView = v.findViewById(R.id.recyclerViewBroadcaster);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ViewerArrayList=new ArrayList<>();
        setAdapter(ViewerArrayList);
        createdata();
        return v;
    }
    public void createdata() {
        Random ranum=new Random();
        for(int i=0;i<50;i++)
        {
            Viewer viewer = new Viewer("Name", 10+ranum.nextInt(20),R.drawable.blank,"India");
            ViewerArrayList.add(viewer);
        }
        adapter.notifyDataSetChanged();
    }
    public void blocked()
    {
        for (int i = 0; i < ViewerArrayList.size(); i++) {
            if(ViewerArrayList.get(i).isBlocked()) {
                ViewerArrayList.remove(i);
            }
        }
        adapter.notifyDataSetChanged();
    }
    public void setAdapter(ArrayList <Viewer> viewers)
    {
        adapter = new ViewerAdapter(this.getContext(),viewers);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 1));
        recyclerView.setAdapter(adapter);

    }
}
