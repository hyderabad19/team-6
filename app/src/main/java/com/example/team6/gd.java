package com.example.team6;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

public class gd extends AppCompatActivity {
    private VideoView videoView;
    private ImageView playbtn;
    private TextView stime;
    private TextView etime;
    private ProgressBar progressBar,buffer;
    private Uri uri;
    private boolean isplaying=false;
    private int start=0,duration=0;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gd);
        uri = Uri.parse("dnfe");
        progressBar.setMax(100);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        isplaying=false;
        new gd.VideoProgress().execute();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if(what==MediaPlayer.MEDIA_INFO_BUFFERING_START)
                    buffer.setVisibility(View.VISIBLE);
                else if(what==MediaPlayer.MEDIA_INFO_BUFFERING_END)
                    buffer.setVisibility(View.INVISIBLE);
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                duration=mp.getDuration()/1000;
                String durSting=String.format("%02d:%02d",duration/60,duration%60);
                etime.setText(durSting);
            }
        });
        playbtn.setImageResource(R.mipmap.playbtn);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isplaying)
                {
                    videoView.pause();
                    isplaying=false;
                    playbtn.setImageResource(R.mipmap.pause);
                }
                else
                {
                    videoView.start();
                    isplaying=true;
                    playbtn.setImageResource((R.mipmap.playbtn));
                }
            }
        });


    }
    public  class VideoProgress extends AsyncTask<Void, Integer ,Void>
    {

        @Override
        protected Void doInBackground(Void... voids) {

            do {
                start=videoView.getCurrentPosition()/100;


                publishProgress(start);



            }while(progressBar.getProgress()<=100);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            try {
                int cp=values[0]*100/duration;

                progressBar.setProgress(values[0]);

                stime.setText(String.format("%02d:%02d",values[0]/60,values[0]%60));



            }
            catch (Exception e)
            {

            }

        }
    }


}


