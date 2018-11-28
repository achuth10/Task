package com.example.achuth.task;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StreamerAdapter extends RecyclerView.Adapter<StreamerAdapter.StreamerHolder>{

    private Context context;

    public ArrayList<Streamer> streamers;
    public int count=0;


StreamerAdapter(Context context, ArrayList<Streamer> streamers) {
        this.context = context;
            this.streamers = streamers;

    }

    @NonNull
    @Override
    public StreamerAdapter.StreamerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
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

    private void delete(int position) { //removes the row
        Toast.makeText(context, "Press again to report user", Toast.LENGTH_SHORT).show();
        if (++count>=2) {
            Streamer streamer =streamers.get(position);
            streamers.remove(position);
            streamer.setBlocked(true);
            notifyItemRemoved(position);
            Toast.makeText(context,"Blocked Streamer for current session",Toast.LENGTH_LONG).show();
        }new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                count=0;
            }
        }, 3000);


    }
    public class StreamerHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Name, Age, Genre, TypeStreamer;
        public ImageView dp;
        public ImageView menu;
        public StreamerHolder(final View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.Name);
            Age=itemView.findViewById(R.id.Age);
            Genre=itemView.findViewById(R.id.Genre);
            dp=itemView.findViewById(R.id.icon1);
            menu=itemView.findViewById(R.id.streammenu);
            menu.setOnClickListener(this);
            TypeStreamer=itemView.findViewById(R.id.TypeStreamer);
        }
        public void setinfo(Streamer streamer) {
            if (!streamer.isBlocked()) {
                Name.setText(streamer.getName());
                Age.setText("Age:" + String.valueOf(streamer.getAge()));
                Genre.setText("Genre:" + streamer.getGenre());
                dp.setImageResource(streamer.getDpid());
                if (streamer.isAstreamer() && streamer.isVstreamer())
                    TypeStreamer.setText("Streamer Type:Audio/Video Streamer");
                else if (streamer.isAstreamer())
                    TypeStreamer.setText("Streamer Type:Audio Streamer");
                else if (streamer.isVstreamer())
                    TypeStreamer.setText("Streamer Type:Video Streamer");
            }
        }
        @Override
        public void onClick(View v) {
           delete(getAdapterPosition());
        }
    }

}
