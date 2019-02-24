package com.example.normal.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Normal on 3/15/18.
 */

class FixedTabsPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    public FixedTabsPagerAdapter(Context context,FragmentManager fm){
        super(fm);
        mContext = context;
    }
    @Override
    public int getCount(){
        return 4;
    }
    @Override
    public Fragment getItem(int position){
        if(position==0) {
            return new NumbersFragment();
        }else if (position==1) {
            return new FamilyFragment();
        }else if(position==2) {
            return new ColorsFragment();
        }else {
            return new PhrasesFragment();
        }
        }

    @Override
    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return mContext.getString(R.string.category_numbers);
            case 1:
                return mContext.getString(R.string.category_family);
            case 2:
                   return mContext.getString(R.string.category_colors);
            case 3:
                return mContext.getString(R.string.category_phrases);
                default: return null;
        }
    }
}
