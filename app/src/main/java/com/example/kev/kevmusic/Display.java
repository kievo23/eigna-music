package com.example.kev.kevmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class Display extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private YouTubePlayerView youTubeView;
    private String videoID;
    private String artist;
    private String track;
    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        Intent thisIntent = getIntent();
        videoID = thisIntent.getStringExtra("videoID");
        track = thisIntent.getStringExtra("track");
        artist = thisIntent.getStringExtra("artist");
        back = (Button) findViewById(R.id.back);

        TextView artistHolder = (TextView) findViewById(R.id.artistlabel);
        TextView trackHolder = (TextView) findViewById(R.id.trackName);

        artistHolder.setText(track);
        trackHolder.setText(artist);

        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(DeveloperKey.YOUTUBE_API_KEY, this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean b) {
        if (!b) {
            player.cueVideo(videoID);
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


}
