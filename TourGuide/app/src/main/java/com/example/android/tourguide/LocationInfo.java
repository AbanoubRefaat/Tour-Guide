package com.example.android.tourguide;

import androidx.annotation.NonNull;

public class LocationInfo {
    private String mName , mAddress,mInfo,mArtistName ;
    private int imageId,mPlayIcon,mStopIcon;
    private int NO_AUDIO_PROVIDED = -1;
    private int mRaw = NO_AUDIO_PROVIDED;

    //Displaying Location
    public LocationInfo(String name,String address, int imageResource){

        // setting this in  wrong way made the items on the list invisible
            mName = name;
         mAddress = address;
         imageId = imageResource;
    }
    //Displaying Song
    public LocationInfo(int stopIcon ,int playIcon ,String name,String artist,int raw){
        mName = name;
        mArtistName = artist;
        mPlayIcon = playIcon;
        mStopIcon = stopIcon;
        mRaw = raw;
    }

    public String getmName() {
        return mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public int getImageId() {
        return imageId;
    }

    public String getmArtistName() {
        return mArtistName;
    }

    public int getmRaw() {
        return mRaw;
    }

    public int getmPlayIcon() {
        return mPlayIcon;
    }

    public int getmStopIcon() {
        return mStopIcon;
    }

    public boolean hasAudio (){

        return mRaw != NO_AUDIO_PROVIDED;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
