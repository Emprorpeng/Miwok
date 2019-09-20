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
import java.util.ArrayList;

//The Family fragment class. Displays all the words for family relations and translations.
public class FamilyFragment extends Fragment {

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
    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    public FamilyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.activity_numbers,container,false);
        am = (AudioManager)getActivity().getSystemService(Context.AUDIO_SERVICE);
        final ArrayList<Word> Numbers = new ArrayList<>();
        Numbers.add(new Word("Father", "əpə", R.drawable.family_father, R.color.category_family, R.raw.family_father));
        Numbers.add(new Word("Mother", "əta", R.drawable.family_mother, R.color.category_family, R.raw.family_mother));
        Numbers.add(new Word("Son", "angsi", R.drawable.family_son, R.color.category_family, R.raw.family_son));
        Numbers.add(new Word("Daughter", "tune", R.drawable.family_daughter, R.color.category_family, R.raw.family_daughter));
        Numbers.add(new Word("Older Brother", "taachi", R.drawable.family_older_brother, R.color.category_family, R.raw.family_older_brother));
        Numbers.add(new Word("Younger Brother", "chalitti", R.drawable.family_younger_brother, R.color.category_family, R.raw.family_younger_brother));
        Numbers.add(new Word("Older Sister", "tete", R.drawable.family_older_sister, R.color.category_family, R.raw.family_older_sister));
        Numbers.add(new Word("Younger Sister", "kolliti", R.drawable.family_younger_sister, R.color.category_family, R.raw.family_younger_sister));
        Numbers.add(new Word("Grandmother", "ama", R.drawable.family_grandmother, R.color.category_family, R.raw.family_grandmother));
        Numbers.add(new Word("Grandfather", "paapa", R.drawable.family_grandfather, R.color.category_family, R.raw.family_grandfather));
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
