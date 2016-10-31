package com.programotuojes.hellointernet.web;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.widget.ImageButton;
import android.widget.Toast;

import com.programotuojes.hellointernet.R;

public class Player {

    private MediaPlayer mediaPlayer = new MediaPlayer();
    private ImageButton playPauseButton;
    private Context context;
    private boolean initialStage = true;
    private String url;

    public void togglePlayer() {
        if (!mediaPlayer.isPlaying()) {
            playPauseButton.setBackgroundResource(R.drawable.ic_media_pause);
            if (initialStage) {
                new DownloadAudio().execute(url);
                Toast.makeText(context, R.string.buffering, Toast.LENGTH_SHORT).show();
            } else
                mediaPlayer.start();
        } else {
            playPauseButton.setBackgroundResource(R.drawable.ic_media_play);
            mediaPlayer.pause();
        }
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }

    public Player(String url, ImageButton playPauseButton, Context context) {
        this.url = url;
        this.playPauseButton = playPauseButton;
        this.context = context;

        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    private class DownloadAudio extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                mediaPlayer.setDataSource(params[0]);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        playPauseButton.setBackgroundResource(R.drawable.ic_media_play);
                        initialStage = true;
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });

                mediaPlayer.prepare();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            initialStage = false;
            mediaPlayer.start();
        }
    }
}
