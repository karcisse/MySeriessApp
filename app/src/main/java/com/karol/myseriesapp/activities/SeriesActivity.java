package com.karol.myseriesapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.R;
import com.karol.myseriesapp.SeriesListView;
import com.karol.myseriesapp.controller.SeriesAdapter;
import com.karol.myseriesapp.controls.FloatingAddSeriesButton;
import com.karol.myseriesapp.database.DataBaseHandler;
import com.karol.myseriesapp.model.Series;

import java.util.ArrayList;
import java.util.HashMap;

public class SeriesActivity extends AppCompatActivity {
    SeriesListView seriesListView;
    HashMap<String, EditText> fieldsMap;
    SeriesAdapter adapter;
    ArrayList<Series> items;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);

        seriesListView = (SeriesListView) findViewById(R.id.series_listview);
        items = new ArrayList<>();
        dataBaseHandler = new DataBaseHandler(this);
        loadSeriesItems();

        adapter = new SeriesAdapter(getApplicationContext(), items);
        seriesListView.setAdapter(adapter);
        registerForContextMenu(seriesListView);

        FloatingAddSeriesButton floatingAddSeriesButton = (FloatingAddSeriesButton) findViewById(R.id.floating_add_series_button);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Series series = (Series) data.getSerializableExtra(AppConstants.SERIES_DATA_TAG);

                items.add(series);
                dataBaseHandler.addSeries(series);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void loadSeriesItems() {
        items = dataBaseHandler.getSeriesList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.increment_series_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.next_episode_menu_item:
                incrementEpisode((Series) seriesListView.getItemAtPosition(info.position));
                return true;
            case R.id.next_season_menu_item:
                incrementSeason((Series) seriesListView.getItemAtPosition(info.position));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void incrementSeason(Series series) {
        series.incrementSeason();
        dataBaseHandler.updateSeries(series);
        adapter.notifyDataSetChanged();
    }

    private void incrementEpisode(Series series) {
        series.incrementEpisde();
        dataBaseHandler.updateSeries(series);
        adapter.notifyDataSetChanged();
    }
}
