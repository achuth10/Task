package com.example.achuth.task;

import android.annotation.SuppressLint;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class StreamerHolder  extends RecyclerView.ViewHolder {
    private TextView Name, Age, Genre, TypreStreamer;
    private ArrayList<Streamer> streamers;
    public StreamerHolder(View itemView) {
        super(itemView);
        Name=itemView.findViewById(R.id.Name);
        Age=itemView.findViewById(R.id.Age);
        Genre=itemView.findViewById(R.id.Genre);
        TypreStreamer=itemView.findViewById(R.id.TypeStreamer);
    }
    public void setinfo(Streamer streamer)
    {
        Name.setText(streamer.getName());
        Age.setText("Age:"+ String.valueOf(streamer.getAge()));
        Genre.setText("Genre:"+streamer.getGenre());
        if(streamer.isAstreamer()&&streamer.isVstreamer())
        TypreStreamer.setText("Streamer Type:Audio/Video Streamer");
        else if(streamer.isAstreamer())
            TypreStreamer.setText("Streamer Type:Audio Streamer");
        else if(streamer.isVstreamer())
            TypreStreamer.setText("Streamer Type:Video Streamer");
    }

    }
