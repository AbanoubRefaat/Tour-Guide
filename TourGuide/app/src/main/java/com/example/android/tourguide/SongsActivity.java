package com.example.android.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class SongsActivity extends AppCompatActivity {

    //resource id for the play and pause icons
    private int playIcon = R.drawable.round_play_arrow_black_24dp;
    private int pauseIcon = R.drawable.round_pause_black_24dp;
    private int stopIcon = R.drawable.baseline_stop_black_18dp;

private boolean isPlaying ;

    //integer to keep track of the current position
    private int pos;

    //object instance of the localInfo class
   public LocationInfo locationInfo;

    /**
     * Handles playback of all the sound files
     */
    MediaPlayer mediaPlayer;

    /**
     * Handles audio focus when playing a sound file
     */
    AudioManager manager;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

//
//         play = (ImageView)findViewById(R.id.image_view);
//         stop = (ImageView)findViewById(R.id.stop_image);


        // setup audio manager to get audio focus
        manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //initializing the arrayList
        final ArrayList<LocationInfo> songs = new ArrayList<>();

        // add elements to the arrayList
        songs.add(new LocationInfo(stopIcon,playIcon, "Ya Eskenrya", "Mohamed Mounir", R.raw.mohamed_mounir_yaeskendria));
        songs.add(new LocationInfo(stopIcon, playIcon, "Shat Ekendrya", "Fayrouz", R.raw.shat_eskendrya));
        songs.add(new LocationInfo(stopIcon,playIcon, "Eskendrya el mahrousa", "M.el Helw", R.raw.raya_skina));
        songs.add(new LocationInfo(stopIcon,playIcon, "Eskendrya tany", "m.El Helw", R.raw.amaar_yaeskndrya));

        //making object of ListView to refer to our list
        final ListView listView = (ListView) findViewById(R.id.list_layout);

        //initializing adapter to hold the view of the ListView
        final Adapter adapter = new Adapter(getApplicationContext(), songs);

        // setting the Initialized adapter to our listView object
        listView.setAdapter(adapter);

        //setting a setOnItemClickListener to listen to user clicks on certain view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

//                 ImageView play_pause =view.findViewById(R.id.image_view);
                final   ImageView stop = view.findViewById(R.id.stop_image);
              final ImageView play = view.findViewById(R.id.image_view);



                   // Get the object at the given position the user clicked on
                    locationInfo = songs.get(position);


                    //storing the result of the requested audio focus
                    int result = manager.requestAudioFocus(changeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                        play.setVisibility(View.VISIBLE);
                        stop.setVisibility(View.VISIBLE);



                    //checking if the audioFocus is granted
                    if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                            play.setOnClickListener(new View.OnClickListener() {
                                String songName = songs.get(position).getmName();




                                @Override
                                public void onClick(View v) {
                                    if (mediaPlayer == null || pos != position ) {
                                        releaseAudio();
                                        play();
                                        isPlaying = true;
                                        changePlayIcon(listView,position,pauseIcon,R.id.image_view);
                                        Log.v("Position","pos = "+pos+" position = "+position);
                                        pos = position;
                                        Toast.makeText(SongsActivity.this, songName+" Is playing " , Toast.LENGTH_SHORT).show();
                                    }else {
                                        releaseAudio();
                                        changePlayIcon(listView,position,playIcon,R.id.image_view);
                                        play.setVisibility(View.INVISIBLE);
                                        stop.setVisibility(View.INVISIBLE);
                                        Toast.makeText(SongsActivity.this, songName+" has    Paused " , Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });



                            stop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    if (mediaPlayer!= null) {
                                        stop();
                                        releaseAudio();
                                        isPlaying = false;
                                        play.setVisibility(View.INVISIBLE);
                                        stop.setVisibility(View.INVISIBLE);
                                        changePlayIcon(listView,position,playIcon,R.id.image_view);
                                        Toast.makeText(SongsActivity.this, "Song has Stopped " , Toast.LENGTH_SHORT).show();

                                    }else {
                                        changePlayIcon(listView,position,playIcon,R.id.image_view);
                                        Toast.makeText(SongsActivity.this,"Song is already stopped",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });


                            //storing the current position in the pos integer
                            pos = position;

                    }

            }

        });



    }

    /**
     * This listener gets triggered when the  MediaPlayer has completed
     * playing the audio file.
     */
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseAudio();


        }
    };

    //method to release the audio to free up the memory
    private void releaseAudio() {

        if (mediaPlayer != null) {

                mediaPlayer.release();

                 mediaPlayer = null;


                manager.abandonAudioFocus(changeListener);
            }
        }

/*
    When the activity is stopped or paused, release the media player resources

 */
    @Override
    protected void onPause() {
        super.onPause();
        releaseAudio();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseAudio();
    }

    /*
    setting the AudioFocusChangeListener to manage the audio focus states
     */
    private AudioManager.OnAudioFocusChangeListener changeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            // if we lost focus for a little amount of time pause until we gain
            //focus again
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN_TRANSIENT) {
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            // this case we gain the focus back again
            else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
            // in this case we loss the audioFocus for long time until we
            //get the focus again
            else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) ;
            releaseAudio();
        }
    };

    /*
      additional method to change the play icon to pause icon
     so that we can manage playing and pausing mediaPlayer
     (i just thought it would make a better user experience)

     @param listView is the current list of songs
     @param position is the current position of selected item on the list
     @param pic  is the picture will be displayed on the current position

     */
    private void changePlayIcon(ListView listView, int position, int pic,int id) {

        ImageView imageView;
        View v;

        v = listView.getChildAt(position);
        imageView = (ImageView) v.findViewById(id);
        imageView.setImageResource(pic);

    }




    private void play(){
        mediaPlayer = MediaPlayer.create(SongsActivity.this, locationInfo.getmRaw());
        mediaPlayer.start();
        // setting the onCompletionListener to call method when the audio is finished playing
        mediaPlayer.setOnCompletionListener(onCompletionListener);



    }
    private void stop(){
        if (isPlaying = true){
            mediaPlayer.stop();
        }
    }

}
