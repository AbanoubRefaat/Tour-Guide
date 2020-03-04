package com.example.android.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Middle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        ArrayList<LocationInfo> list = new ArrayList<>();
        list.add(new LocationInfo("Saint Marc College","Shatby",R.drawable.saint_marc));
        list.add(new LocationInfo("Unknown Soldier","Manshya",R.drawable.unknown_soldier));
        list.add(new LocationInfo("Alexandria Bibliotheca ","shatby",R.drawable.bibliotheca));
        list.add(new LocationInfo("Citadel of Qaitbay","bahary",R.drawable.citadel_of_qaitbay));
        list.add(new LocationInfo("Saad Zaghlol Square","Ramel Station",R.drawable.zaghloul1));


        Adapter adapter = new Adapter(this,list);

        ListView listView = (ListView)findViewById(R.id.list_layout);



        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                if (position == 0){
                    descriptionFragment.description.setText(R.string.saint_marc);

                }else if (position == 1){
                    descriptionFragment.description.setText(R.string.unknown_soldier);

                }else if (position == 2){
                    descriptionFragment.description.setText(R.string.bibliotheca);

                }else if (position == 3){
                    descriptionFragment.description.setText(R.string.qaitbay);

                }else if (position == 4){
                    descriptionFragment.description.setText(R.string.saad_zhagloul);

                }
            }
        });
    }
}
