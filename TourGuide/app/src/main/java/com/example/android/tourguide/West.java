package com.example.android.tourguide;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class West extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        ArrayList<LocationInfo> arrayList = new ArrayList<>();

        arrayList.add(new LocationInfo("Greco Roman Museum","Masr Mtation",R.drawable.greco_roman));
        arrayList.add(new LocationInfo("Pompey Piller","Kom Eldekka",R.drawable.pompey_piller));
        arrayList.add(new LocationInfo("Roman Amphitheater","Masr Station",R.drawable.roman_amphitheater));

        ListView listView = (ListView)findViewById(R.id.list_layout);

        Adapter adapter = new Adapter(getApplicationContext(),arrayList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                if (position==0){

                    descriptionFragment.description.setText(R.string.greco_roman);

                }else if (position==1){

                    descriptionFragment.description.setText(R.string.pompey);

                }
                else {
                    descriptionFragment.description.setText(R.string.roman_amphitheatre);

                }
            }
        });
    }
}
