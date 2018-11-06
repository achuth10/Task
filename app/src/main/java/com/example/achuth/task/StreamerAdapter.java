package com.example.achuth.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class StreamerAdapter extends RecyclerView.Adapter<StreamerHolder> {

    private Context context;
    private ArrayList<Streamer> streamers;


StreamerAdapter(Context context, ArrayList<Streamer> streamers) {
        this.context = context;
            this.streamers = streamers;

    }


    @NonNull
    @Override
    public StreamerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rowlayout,viewGroup, false);
        return new StreamerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StreamerHolder streamerHolder, int i) {
    Streamer streamer=streamers.get(i);
    streamerHolder.setinfo(streamer);

    }

    @Override
    public int getItemCount() {
        return streamers.size();
    }
}
