package com.example.devi.multiscreens;

/**
 * Created by devi on 11/16/2016.
 */
public class Word {
    private String mDefaultTranslation;
    private  String mMiwokTranslation;
    private int mImageResourceId=NO_IMAGE_PROVIDED;
    private static final int NO_IMAGE_PROVIDED =-1;
    private int mAudioResourceId;
    public Word(String defaultTranslation, String miwokTranslation,int audioResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMiwokTranslation = miwokTranslation;
        mAudioResourceId = audioResourceId;
    }
    public Word(String defaultTranslation,int imageResourceId,String miwokTranslation){
        mImageResourceId= imageResourceId;

        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
    }
    public Word(String defaultTranslation,int imageResourceId,String miwokTranslation,int audioResourceId){
        mImageResourceId= imageResourceId;

        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mAudioResourceId = audioResourceId;
    }

public String getDefaultTranslation()
{

    return mDefaultTranslation;
}
    public  String getmMiwokTranslation()
    {
        return  mMiwokTranslation;
    }
    public int getImageResouceId()
    {
        return mImageResourceId;
    }

    public boolean hasImage()
    {
        return mImageResourceId !=NO_IMAGE_PROVIDED;
    }
    public int getAudioResourceId(){return mAudioResourceId;}
}
