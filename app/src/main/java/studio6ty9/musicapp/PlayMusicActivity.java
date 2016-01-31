package studio6ty9.musicapp;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.File;
import java.util.List;

public class PlayMusicActivity extends AppCompatActivity {

    private TextView songTitle;
    private TextView songPath;
    private String name;
    private String path;
    private static MediaPlayer player;
    private static SeekBar seekBar;
    private static TextView textView;
    private AudioManager audioManager;
    private ToggleButton toggleButtonPlayStop;
    private static List<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        songTitle = (TextView) findViewById(R.id.textViewSongTitle);
        songPath = (TextView) findViewById(R.id.textViewSongPath);

        //Song aSong = getIntent().getExtras().getParcelable("oneSong");

        Bundle extras = getIntent().getExtras();
        path = extras.getString("path");
        name = extras.getString("title");

        songTitle.setText(name);
        songPath.setText(path);

        firstStartMusic();
        seekBarVolume();

        toggleButtonPlayStop = (ToggleButton) findViewById(R.id.toggleButtonPlayStop);
        toggleButtonPlayStop.setChecked(true);
        toggleButtonPlayStop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    resumeMusic();
                } else {
                    stopMusic();
                }
            }
        });
    }

    public void seekBarVolume() {
        seekBar = (SeekBar) findViewById(R.id.seekBarVolume);
        textView = (TextView) findViewById(R.id.textViewStatus);
        textView.setText("Progress:" + seekBar.getProgress());
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        seekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressValue;
                    int progressVolume;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        progressValue = progress;
                        textView.setText("Progress:" + progressValue);
                        progressVolume = progressValue;
                        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progressVolume, progressVolume);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        textView.setText("Progress:" + progressValue);

                    }
                }
        );
    }


    public void firstStartMusic() {
        if (player != null && player.isPlaying()) {
            player.stop();
        }
        player = MediaPlayer.create(this, Uri.fromFile(new File(path)));
        player.start();
    }

    public void resumeMusic() {
        player.start();
    }

    public void stopMusic() {
        player.pause();
    }

    public void nextSong(View view) {

    }

    public void lastSong(View view) {

    }


}
