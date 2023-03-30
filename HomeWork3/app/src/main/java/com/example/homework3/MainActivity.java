package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewTreeViewModelKt;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener {
    private Button button_start_music;
    private Button button_pause_music;
    private Button button_stop_music;
    private TextView nameaudio;

    private Button internet_audio_file;
    private Button phone_audio_file;

    private String nameAudioMusic = "";
    private MediaPlayer mediaPlayer;
    private String Data_Http = "https://notka.net/wp-content/uploads/01-Barbariki.-CHto-takoe-dobrota.mp3";
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_start_music = findViewById(R.id.button_start_music);
        button_pause_music = findViewById(R.id.button_pause_music);
        button_stop_music = findViewById(R.id.button_stop_music);

        nameaudio = findViewById(R.id.nameaudio);

        internet_audio_file = findViewById(R.id.internet_audio_file);
        phone_audio_file = findViewById(R.id.phone_audio_file);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

    }

    public void ChooseResource(View view) {
        deleteMediaPLayer();
        try {
            switch (view.getId()) {
                case R.id.phone_audio_file:
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kefteme);
                    mediaPlayer.start();
                    nameAudioMusic = "kefteme";
                    nameaudio.setText("Название трека "+nameAudioMusic+" проигрывание "+mediaPlayer.isPlaying());
                    break;
                case R.id.internet_audio_file:
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(Data_Http);
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.prepareAsync();
                    nameAudioMusic = "Барбарики";
                    nameaudio.setText("Название трека "+nameAudioMusic+" проигрывание "+mediaPlayer.isPlaying());
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(mediaPlayer == null) {
            return;
        }
        mediaPlayer.setOnCompletionListener(this);
    }

    public void PlaySong(View view) {
        switch (view.getId()) {
            case R.id.button_start_music:
                if(mediaPlayer!= null && !mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case R.id.button_pause_music:
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.button_stop_music:
                mediaPlayer.stop();
                break;
        }
        nameaudio.setText("Название трека "+nameAudioMusic+" проигрывание "+mediaPlayer.isPlaying());
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }

    public void deleteMediaPLayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteMediaPLayer();
    }
}