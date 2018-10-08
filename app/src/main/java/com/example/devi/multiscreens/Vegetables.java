package com.example.devi.multiscreens;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Vegetables extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    AudioManager.OnAudioFocusChangeListener mAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange ==  AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    // Pause playback


                    else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                        // Resume playback
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                        // Stop playback
                    }
                }
            };
    private MediaPlayer.OnCompletionListener mCompletionListener =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager  = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words =new ArrayList<Word>();


        words.add(new Word("lady finger",R.drawable.lagy,"LADY FINGER",R.raw.ladyfinger));
        words.add(new Word("cabbage",R.drawable.cabbage,"CABBAGE",R.raw.cabbage));
        words.add(new Word("carrot",R.drawable.carrot,"CARROT",R.raw.carrot));
        words.add(new Word("califlower",R.drawable.califlower1,"CALIFLOWER",R.raw.califlower));
        words.add(new Word("brinjal",R.drawable.brinjal,"BRINJAL",R.raw.brinjal));
        words.add(new Word("ginger",R.drawable.ginger,"GINGER",R.raw.ginger));
        words.add(new Word("mushroom",R.drawable.mushrooms,"MUSHROOM",R.raw.mushroom));
        words.add(new Word("onion",R.drawable.onion,"ONION",R.raw.onion));
        words.add(new Word("potato",R.drawable.potato1,"POTATO",R.raw.potato));
        words.add(new Word("tomato",R.drawable.tomota,"TOMATO",R.raw.tomota));




        WordAdapter adapter = new WordAdapter(this,words,R.color.sky);
        ListView listView =(ListView)findViewById(R.id.listview);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(Numbers.this, "clicked numbers", Toast.LENGTH_SHORT).show();
                Word word =words.get(i);

                releaseMediaPlayer();
                int result = mAudioManager.requestAudioFocus(mAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // mAudioManager.registerMediaButtonEventReceiver(RemoteControlReceiver);
                    // Start playback.

                    mMediaPlayer = MediaPlayer.create(Vegetables.this, word.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });

    }

    @Override
    protected void onStop()
    {
        super.onStop();
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer(){

        if(mMediaPlayer !=null){mMediaPlayer.release();
            mMediaPlayer =null;
            mAudioManager.abandonAudioFocus(mAudioFocusChangeListener);
        }
    }
}
