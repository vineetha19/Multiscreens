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

public class Numbers extends AppCompatActivity {
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

        words.add(new Word("one",R.drawable.number_one,"ONE",R.raw.one));
        words.add(new Word("two",R.drawable.number_two,"TWO",R.raw.two));
        words.add(new Word("three",R.drawable.number_three,"THREE",R.raw.three));
        words.add(new Word("four",R.drawable.number_four,"FOUR",R.raw.four));
        words.add(new Word("five",R.drawable.number_five,"FIVE",R.raw.five));
        words.add(new Word("six",R.drawable.number_six,"SIX",R.raw.six));
        words.add(new Word("seven",R.drawable.number_seven,"SEVEN",R.raw.seven));
        words.add(new Word("eight",R.drawable.number_eight,"EIGHT",R.raw.eight));
        words.add(new Word("nine",R.drawable.number_nine,"NINE",R.raw.nine));
        words.add(new Word("ten",R.drawable.number_ten,"TEN",R.raw.ten));


        WordAdapter adapter = new WordAdapter(this,words,R.color.category_numbers);
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

                    mMediaPlayer = MediaPlayer.create(Numbers.this, word.getAudioResourceId());
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
