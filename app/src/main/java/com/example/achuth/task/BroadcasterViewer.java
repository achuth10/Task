package com.example.achuth.task;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class BroadcasterViewer extends Fragment {
    private RecyclerView recyclerView;
    private ViewerAdapter adapter;
    public ArrayList<Viewer> ViewerArrayList;
    private TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_broadcaster_viewer, container, false);
        textView=v.findViewById(R.id.textview);
        recyclerView = v.findViewById(R.id.recyclerViewBroadcaster);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ViewerArrayList=new ArrayList<>();
        setAdapter(ViewerArrayList);
        createdata();
//        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY > oldScrollY) {
//                    Log.i("scroll", "Scroll DOWN");
//                }
//                if (scrollY < oldScrollY) {
//                    Log.i("scroll", "Scroll UP");
//                }
//
//                if (scrollY == 0) {
//                    Log.i("scroll", "TOP SCROLL");
//                    setNavigationVisibility(true);
//                }
//
//                if (scrollY == ( v.getMeasuredHeight() - v.getMeasuredHeight() )) {
//                    Log.i("scroll", "BOTTOM SCROLL");
//                    setNavigationVisibility(false);
//                }
//            }
//        });
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
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setAdapter(adapter);

    }
    public void setNavigationVisibility(boolean visible) {
        if (textView.isShown() && !visible) {
            textView.setVisibility(View.GONE);
        }
        else if (!textView.isShown() && visible){
            textView.setVisibility(View.VISIBLE);
        }
    }
}
