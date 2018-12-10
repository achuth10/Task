package com.example.achuth.task;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class Dashboard extends Fragment {

    private RecyclerView recyclerView;
    private CheckBox asbox,vsbox;
    private StreamerAdapter adapter;
    public ArrayList<Streamer> tempas,tempvs,temp;
    public ArrayList<Streamer> StreamerArrayList;
    TextView textView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        asbox=v.findViewById(R.id.audiostreamercheck);
        vsbox=v.findViewById(R.id.videostreamercheck);
        textView=(TextView)v.findViewById(R.id.streamer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        StreamerArrayList = new ArrayList<>();
        tempas = new ArrayList<>();
        temp=new ArrayList<>();
        tempvs=new ArrayList<>();
        asbox.setChecked(false);
        vsbox.setChecked(false);
        setAdapter(StreamerArrayList);
        createdata();

        asbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sort();
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
                sort();
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
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                Toast.makeText(getActivity(),"clicked at "+ position,Toast.LENGTH_LONG).show();
                Intent i =new Intent(getActivity(),StreamerPage.class);
                //textView.setText(StreamerArrayList.get(position).getName());
                i.putExtra("Index",position);
               Log.i("View pressed","Herere");
        getActivity().startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getActivity(), "Long press on position :"+position,
                        Toast.LENGTH_LONG).show();
            }
        }));
        return v;
    }
    public boolean setstreamer(int a)
    {
        return a == 1;
    }


    public void createdata() {
        Random ranum=new Random();
        for(int i=0;i<20;i++)
        {
            Streamer streamer = new Streamer("Name", "Esports", 18+ranum.nextInt(20), setstreamer((int)Math.round(Math.random())), setstreamer((int)Math.round(Math.random())),R.drawable.blank,setstreamer((int)Math.round(Math.random())));
            StreamerArrayList.add(streamer);
        }
        adapter.notifyDataSetChanged();
    }

    public void sort(){
        tempas.clear();
        tempvs.clear();
        blocked();
            for (int i = 0; i < StreamerArrayList.size(); i++) {
                if(StreamerArrayList.get(i).isVstreamer()&&StreamerArrayList.get(i).isAstreamer())
                {
                    if(!StreamerArrayList.get(i).isBlocked())
                        temp.add(StreamerArrayList.get(i));
                }
                else if (StreamerArrayList.get(i).isVstreamer()) {
                    if(!StreamerArrayList.get(i).isBlocked())
                        tempvs.add(StreamerArrayList.get(i));

                }
                else if(StreamerArrayList.get(i).isAstreamer())
                {
                    if(!StreamerArrayList.get(i).isBlocked())
                        tempas.add(StreamerArrayList.get(i));
                }
            }
            adapter.notifyDataSetChanged();
        }

public void blocked()
{
    for (int i = 0; i < StreamerArrayList.size(); i++) {
        if(StreamerArrayList.get(i).isBlocked()) {
            StreamerArrayList.remove(i);
        }
    }
    adapter.notifyDataSetChanged();
}
    public void setAdapter(ArrayList<Streamer> streamers)
    {
        adapter = new StreamerAdapter(this.getContext(), streamers);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setAdapter(adapter);

    }
    public  interface ClickListener{
         void onClick(View view,int position);
         void onLongClick(View view,int position);
    }

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }

}

