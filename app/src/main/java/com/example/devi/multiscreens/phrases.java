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

public class phrases extends AppCompatActivity {
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

        words.add(new Word("What is your name?","WHAT IS YOUR NAME?",R.raw.wht));
        words.add(new Word("My name is","MY NAME IS",R.raw.my));
        words.add(new Word("How are you feeling?","HOW ARE YOU FEELING?",R.raw.how));
        words.add(new Word("I'm feeling good","I'M FEELING GOOD",R.raw.good));
        words.add(new Word("Are you coming?","ARE YOU COMING?",R.raw.are));
        words.add(new Word("Yes,I'm Coming","YES,I,M COMING",R.raw.yess));
        words.add(new Word("I am proud of you","I  AM PROUD OF YOU",R.raw.proud));
        words.add(new Word("welcome","WELCOME",R.raw.welcome));
        words.add(new Word("good morning","GOOD MORNING",R.raw.morning));
        words.add(new Word("goodnight","GOOD NIGHT",R.raw.night));
        words.add(new Word("have a great day","HAVE A GREAT DAY",R.raw.have));



        WordAdapter adapter = new WordAdapter(this,words,R.color.category_phrases);
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

                    mMediaPlayer = MediaPlayer.create(phrases.this, word.getAudioResourceId());
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
