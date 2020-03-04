package com.example.android.tourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.util.List;




public class Adapter extends ArrayAdapter<LocationInfo> {

    //setting the variable of the Audio images
    private int playIcon = R.drawable.round_play_arrow_black_24dp;
    private int pauseIcon = R.drawable.round_pause_black_24dp;
    private int stopIcon = R.drawable.baseline_stop_black_18dp;

    // instance of the SongsActivity
    final SongsActivity songsActivity = new SongsActivity();


    // adpater constructor
    public Adapter(Context context, List<LocationInfo> objects) {
        // setting resource to 0
        super(context, 0, objects);


    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {

        View list = convertView;
        if (list == null){

            list = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_item,parent,false);
        }
        //getting the current position
        LocationInfo currentPos = getItem(position);



        // in case we open the songsActivity
        if (currentPos.hasAudio()){

            final ImageView mPlayIcon = (ImageView)list.findViewById(R.id.image_view);
            mPlayIcon.setImageResource(R.drawable.round_play_arrow_black_24dp);

            final ImageView mStopIcon = (ImageView)list.findViewById(R.id.stop_image);
            mStopIcon.setImageResource(R.drawable.baseline_stop_black_18dp);
            mStopIcon.setVisibility(View.INVISIBLE);

            final TextView songName = (TextView)list.findViewById(R.id.landmark_name);
            songName.setText(currentPos.getmName());

            TextView artistName = (TextView)list.findViewById(R.id.landmark_location);
            artistName.setText(currentPos.getmArtistName());

        }
        //        in case we open the locationActivity
        else {
            TextView name = (TextView)list.findViewById(R.id.landmark_name);
            name.setText(currentPos.getmName());

            TextView address = (TextView)list.findViewById(R.id.landmark_location);
            address.setText(currentPos.getmAddress());


            ImageView photo = (ImageView)list.findViewById(R.id.image_view);
            photo.setImageResource(currentPos.getImageId());
            photo.setVisibility(View.VISIBLE);


        }

        return list;
    }
}
