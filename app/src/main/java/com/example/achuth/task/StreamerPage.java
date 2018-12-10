package com.example.achuth.task;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class StreamerPage extends AppCompatActivity {
private VideoView videoView;
private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamer_page);
        videoView=(VideoView)findViewById(R.id.videoview);
        textView =(TextView)findViewById(R.id.streamer);
        String vidAddress = "https://archive.org/download/BanjoTooie_100p_45413/BanjoTooie_100p_45413_HQ_part01_512kb.mp4";
        Uri vidUri = Uri.parse(vidAddress);
        MediaController mediaController =new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(vidUri);
        videoView.start();
    }
}
