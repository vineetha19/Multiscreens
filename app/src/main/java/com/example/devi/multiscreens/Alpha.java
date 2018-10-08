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

public class Alpha extends AppCompatActivity {
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

        words.add(new Word("apple",R.drawable.aa,"APPLE",R.raw.apple));
        words.add(new Word("baseball",R.drawable.bb,"BASE BALL",R.raw.ball));
        words.add(new Word("clock",R.drawable.cc,"CLOCK",R.raw.call));
        words.add(new Word("donkey",R.drawable.dd,"DONKEY",R.raw.doll));
        words.add(new Word("elephant",R.drawable.ee,"ELEPHANT",R.raw.egg));
        words.add(new Word("father",R.drawable.ff,"FATHER",R.raw.fan));
        words.add(new Word("grandmother",R.drawable.gg,"GRANDMOTHER",R.raw.girl));
        words.add(new Word("hungry",R.drawable.hh,"HUNGRY",R.raw.hen));
        words.add(new Word("internet",R.drawable.ii,"INTERNET",R.raw.iii));
        words.add(new Word("justice",R.drawable.jj,"JUSTICE",R.raw.jug));
        words.add(new Word("kangaro",R.drawable.kk,"KANGARO",R.raw.kite));
        words.add(new Word("london",R.drawable.ll,"LONDON",R.raw.lion));
        words.add(new Word("monkey",R.drawable.mm,"MONKEY",R.raw.mon));
        words.add(new Word("norway",R.drawable.nn,"NORWAY",R.raw.net));
        words.add(new Word("overtime",R.drawable.oo,"OVERTIME",R.raw.ox));
        words.add(new Word("piligm",R.drawable.pp,"PILIGM",R.raw.pen));
        words.add(new Word("question",R.drawable.qq,"QUESTION",R.raw.quil));
        words.add(new Word("rabbit",R.drawable.rr,"RABBIT",R.raw.rat));
        words.add(new Word("superman",R.drawable.ss,"SUPERMAN",R.raw.sun));
        words.add(new Word("telephone",R.drawable.tt,"TELEPHONE",R.raw.tooth));
        words.add(new Word("underware",R.drawable.uu,"UNDERWARE",R.raw.umber));
        words.add(new Word("vacinate",R.drawable.vv,"VACINATE",R.raw.violin));
        words.add(new Word("worldwideweb",R.drawable.ww,"WORLDWIDEWEB",R.raw.wax));
        words.add(new Word("xlyophone",R.drawable.xx,"XLYOPHONE",R.raw.xay));
        words.add(new Word("yogurt",R.drawable.yy,"YOGURT",R.raw.yak));
        words.add(new Word("zebra",R.drawable.zz,"ZEBRA",R.raw.zebra));



        WordAdapter adapter = new WordAdapter(this,words,R.color.alpha);
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

                    mMediaPlayer = MediaPlayer.create(Alpha.this, word.getAudioResourceId());
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
