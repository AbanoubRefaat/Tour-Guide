package com.example.android.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView east,west,middle,songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        east = (TextView)findViewById(R.id.eastern);
        west = (TextView)findViewById(R.id.western);
        middle = (TextView)findViewById(R.id.middle);
        songs = (TextView)findViewById(R.id.songs);


        east.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent east = new Intent(MainActivity.this,East.class);
                startActivity(east);
            }
        });

        west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent west = new Intent(MainActivity.this,West.class);
                startActivity(west);
            }
        });

        middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent middle = new Intent(MainActivity.this,Middle.class);
                startActivity(middle);
            }
        });

        songs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent songs = new Intent(MainActivity.this,SongsActivity.class);
                startActivity(songs);
            }
        });
    }
}
