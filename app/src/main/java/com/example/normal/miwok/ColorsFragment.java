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
import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    private AudioManager am;
    private MediaPlayer mMediaPlayer;
    private AudioManager.OnAudioFocusChangeListener afChangeListner =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int i) {
                    if (i == AudioManager.AUDIOFOCUS_GAIN) {
                        mMediaPlayer.start();
                    }
//                    if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
//                        mMediaPlayer.pause();
//                    }
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


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_numbers,container,false);
        am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> Numbers = new ArrayList<>();
        Numbers.add(new Word("Red", "wetetti", R.drawable.color_red, R.color.category_colors, R.raw.color_red));
        Numbers.add(new Word("Green", "chokokki", R.drawable.color_green, R.color.category_colors, R.raw.color_green));
        Numbers.add(new Word("Brown", "takaakki", R.drawable.color_brown, R.color.category_colors, R.raw.color_brown));
        Numbers.add(new Word("Gray", "topoppi", R.drawable.color_gray, R.color.category_colors, R.raw.color_gray));
        Numbers.add(new Word("Black", "kululli", R.drawable.color_black, R.color.category_colors, R.raw.color_black));
        Numbers.add(new Word("White", "kelelli", R.drawable.color_white, R.color.category_colors, R.raw.color_white));
        Numbers.add(new Word("Dusty Yellow", "topiisə", R.drawable.color_dusty_yellow, R.color.category_colors, R.raw.color_dusty_yellow));
        Numbers.add(new Word("Mustard Yellow", "chiwiitə", R.drawable.color_mustard_yellow, R.color.category_colors, R.raw.color_mustard_yellow));
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
     return rootview;
    }

}
