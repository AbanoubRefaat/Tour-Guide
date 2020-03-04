package com.example.android.tourguide;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class East extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);

        ArrayList<LocationInfo> list = new ArrayList<>();
        list.add(new LocationInfo("Stanly bridge","stanly",R.drawable.stanely_bridge));
        list.add(new LocationInfo("the sunken city of heracleion","Abo Qir",R.drawable.heraklion));
        list.add(new LocationInfo("Montaza Palace","Montaza",R.drawable.montaza));

          Adapter adapter = new Adapter(getApplicationContext(),list);


        ListView listView = (ListView)findViewById(R.id.list_layout);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                DescriptionFragment descriptionFragment = new DescriptionFragment();

                if (position==0){

                    descriptionFragment.description.setText(R.string.stanly);

                }else if (position==1){

                    descriptionFragment.description.setText(R.string.heracleion);

                }else  {

                    descriptionFragment.description.setText(R.string.montaza);

                }

            }
        });
    }


}
