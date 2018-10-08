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

public class Family extends AppCompatActivity {
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

        words.add(new Word("father",R.drawable.family_father,"FATHER",R.raw.father));
        words.add(new Word("mother",R.drawable.family_mother,"MOTHER",R.raw.mother));
        words.add(new Word("son",R.drawable.family_son,"SON",R.raw.son));
        words.add(new Word("daughter",R.drawable.family_daughter,"DAUGHTER",R.raw.daughter));
        words.add(new Word("younger_sister",R.drawable.family_younger_sister,"YOUNGERSISTER",R.raw.youngersister));
        words.add(new Word("younger_brother",R.drawable.family_younger_brother,"YOUNGERBROTHER",R.raw.youngerbrother));
        words.add(new Word("grandfather",R.drawable.family_grandfather,"GRANDFATHER",R.raw.grandfather));
        words.add(new Word("grandmother",R.drawable.family_grandmother,"GRANDMOTHER",R.raw.grandmother));
        words.add(new Word("oldersister",R.drawable.family_older_sister,"OLDERSISTER",R.raw.oldersister));
        words.add(new Word("olderbrother",R.drawable.family_older_brother,"OLDERBROTHER",R.raw.olderbrother));



        WordAdapter adapter = new WordAdapter(this,words,R.color.category_family);
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

                    mMediaPlayer = MediaPlayer.create(Family.this, word.getAudioResourceId());
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
