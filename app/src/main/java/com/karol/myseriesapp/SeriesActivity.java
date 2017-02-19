package com.karol.myseriesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

public class SeriesActivity extends AppCompatActivity {
    SeriesListView seriesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);

        seriesListView = (SeriesListView) findViewById(R.id.series_listview);

        String[] exampleData = {"Sd","sdsd","sds","sdsd","sds","sdsd","sds","sdsd","sds","sdsd","sds","sdsd","sds","sdsd","sds","sdsd","sds","sdsd","sds","sdsd","sds"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, exampleData);
        seriesListView.setAdapter(adapter);

        FloatingActionButton floatingAddSeriesButton = (FloatingActionButton) findViewById(R.id.floating_add_series_button);
        floatingAddSeriesButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddSeriesActivity.class);
                startActivity(intent);
            }
        });
    }
}
