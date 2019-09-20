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
//The Phrases fragment class. Displays all the words for common phrases and translations.

public class PhrasesFragment extends Fragment {
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
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


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_numbers,container,false);
        am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> Numbers = new ArrayList<>();
        Numbers.add(new Word("Where are you going?", "minto wuksus", R.color.category_phrases, R.raw.phrase_where_are_you_going));
        Numbers.add(new Word("What is your name?", "tinnə oyaase'nə", R.color.category_phrases, R.raw.phrase_what_is_your_name));
        Numbers.add(new Word("My name is...", "oyaaset", R.color.category_phrases, R.raw.phrase_my_name_is));
        Numbers.add(new Word("How are you feeling?", "michəksəs?", R.color.category_phrases, R.raw.phrase_how_are_you_feeling));
        Numbers.add(new Word("I'm feeling good", "kuchi achit", R.color.category_phrases, R.raw.phrase_im_feeling_good));
        Numbers.add(new Word("Are you coming?", "əənəs'aa?", R.color.category_phrases, R.raw.phrase_are_you_coming));
        Numbers.add(new Word("Yes,I'm coming", "həə'əənəm", R.color.category_phrases, R.raw.phrase_yes_im_coming));
        Numbers.add(new Word("I'm coming", "əənəm", R.color.category_phrases, R.raw.phrase_im_coming));
        Numbers.add(new Word("Lets go", "yoowutis", R.color.category_phrases, R.raw.phrase_lets_go));
        Numbers.add(new Word("Come here", "ənni'nem", R.color.category_phrases, R.raw.phrase_come_here));
        WordAdapter itemadapter = new WordAdapter(getActivity(), Numbers);
        ListView listview = rootview.findViewById(R.id.numbers_xml);
        listview.setAdapter(itemadapter);
        if (am.requestAudioFocus(afChangeListner, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Word word = Numbers.get(i);
                    releaseMediaPlayer();
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.GetSoundResourceID());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            });
        }
    return rootview;}

}
