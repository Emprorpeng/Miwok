package com.example.normal.miwok;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Normal on 2/18/18.
 */
//The array adapter to display the various translations in a ListView.
public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity context, ArrayList<Word> word){
        super(context,0,word);
    }
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Word currentWord = getItem(position);
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_layout,parent,false);
        }
        if (currentWord.GetRecord()==1){
            TextView MiwokTextView =listItemView.findViewById(R.id.Miwok_textview);
            MiwokTextView.setText(currentWord.GetMiwokTranslation());
            TextView DefaultTextView =listItemView.findViewById(R.id.Default_textview);
            DefaultTextView.setText(currentWord.GetDefaultTranslation());
            listItemView.findViewById(R.id.imageView).setVisibility(View.GONE);
            int colour = ContextCompat.getColor(getContext(),currentWord.GetColour());
            listItemView.findViewById(R.id.LinearLayout2).setBackgroundColor(colour);
            return listItemView;
        }else {
            TextView MiwokTextView =listItemView.findViewById(R.id.Miwok_textview);
            MiwokTextView.setText(currentWord.GetMiwokTranslation());
            TextView DefaultTextView =listItemView.findViewById(R.id.Default_textview);
            DefaultTextView.setText(currentWord.GetDefaultTranslation());
            ImageView ImageView = listItemView.findViewById(R.id.imageView);
            ImageView.setImageResource(currentWord.GetImageID());
            listItemView.findViewById(R.id.LinearLayout2).setBackgroundColor(currentWord.GetColour());
            int colour = ContextCompat.getColor(getContext(),currentWord.GetColour());
            listItemView.findViewById(R.id.LinearLayout2).setBackgroundColor(colour);
            return listItemView;
        }

    }
}
