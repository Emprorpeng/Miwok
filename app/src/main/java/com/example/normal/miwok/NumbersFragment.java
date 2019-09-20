package com.example.normal.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
//The Numbers fragment class. Displays all the words for numbers and translations.
public class NumbersFragment extends Fragment {

    private AudioManager am;
    private MediaPlayer mMediaPlayer;
    private AudioManager.OnAudioFocusChangeListener afChangeListner =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int i) {
                    if (i == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    }
                    if (i == AudioManager.AUDIOFOCUS_LOSS) {
                        mMediaPlayer.stop();
                    }
                }
            };
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();

            mMediaPlayer = null;
            am.abandonAudioFocus(afChangeListner);
        }
    }
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_numbers,container,false);
        am =(AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> Numbers = new ArrayList<>();
        Numbers.add(new Word("One", "lutti", R.drawable.number_one, R.color.category_numbers, R.raw.number_one));
        Numbers.add(new Word("Two", "otiiko", R.drawable.number_two, R.color.category_numbers, R.raw.number_two));
        Numbers.add(new Word("Three", "tolookosu", R.drawable.number_three, R.color.category_numbers, R.raw.number_three));
        Numbers.add(new Word("Four", "oyyisa", R.drawable.number_four, R.color.category_numbers, R.raw.number_four));
        Numbers.add(new Word("Five", "massokka", R.drawable.number_five, R.color.category_numbers, R.raw.number_five));
        Numbers.add(new Word("Six", "temmokka", R.drawable.number_six, R.color.category_numbers, R.raw.number_six));
        Numbers.add(new Word("Seven", "kenekaku", R.drawable.number_seven, R.color.category_numbers, R.raw.number_seven));
        Numbers.add(new Word("Eight", "kawinta", R.drawable.number_eight, R.color.category_numbers, R.raw.number_eight));
        Numbers.add(new Word("Nine", "wo'e", R.drawable.number_nine, R.color.category_numbers, R.raw.number_nine));
        Numbers.add(new Word("Ten", "na'aacha", R.drawable.number_ten, R.color.category_numbers, R.raw.number_ten));
        WordAdapter itemadapter = new WordAdapter(getActivity(),Numbers);
        final ListView listview = rootview.findViewById(R.id.numbers_xml);
        listview.setAdapter(itemadapter);
        if (am.requestAudioFocus(afChangeListner,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT)==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Word word = Numbers.get(i);
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(getActivity(),word.GetSoundResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            });
        }
        return rootview;
    }

}
