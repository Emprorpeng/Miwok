package com.example.normal.miwok;

/**
 * Created by Normal on 2/18/18.
 */
//The Word POJO class
public class Word {
    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceID;
    private int mRecord;
    private int mBackgroundColour;
    private int mSoundResourceID;
    public Word(String DefaultTranslation,String MiwokTranslation,int BackgroundColour, int SoundResourceID){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mBackgroundColour = BackgroundColour;
        mSoundResourceID = SoundResourceID;
        int record = 1;
        mRecord = record;
    }
    public Word(String DefaultTranslation,String MiwokTranslation,int ImageResourceID,int BackgroundColour, int SoundResourceID){
        mDefaultTranslation = DefaultTranslation;
        mMiwokTranslation = MiwokTranslation;
        mImageResourceID = ImageResourceID;
        mBackgroundColour = BackgroundColour;
        mSoundResourceID = SoundResourceID;
        int record = 2;
        mRecord = record;
    }
    public String GetDefaultTranslation(){
     return mDefaultTranslation;
    }
    public String GetMiwokTranslation(){
        return mMiwokTranslation;
    }
    public int GetImageID(){
        return mImageResourceID;
    }
    public int GetRecord(){
        return mRecord;
    }
    public int GetColour(){
        return mBackgroundColour;
    }
    public int GetSoundResourceID(){
        return mSoundResourceID;
    }
}
