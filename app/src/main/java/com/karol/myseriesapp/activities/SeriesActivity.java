package com.karol.myseriesapp.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.karol.myseriesapp.R;
import com.karol.myseriesapp.SeriesListView;
import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.controller.SeriesCursorAdapter;
import com.karol.myseriesapp.database.DataBaseHandler;
import com.karol.myseriesapp.model.Series;

public class SeriesActivity extends AppCompatActivity {
    SeriesListView seriesListView;
    SeriesCursorAdapter adapter;
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series_list);

        seriesListView = (SeriesListView) findViewById(R.id.series_listview);
        dataBaseHandler = new DataBaseHandler(this);

        adapter = new SeriesCursorAdapter(this, dataBaseHandler.getSeriesData());
        seriesListView.setAdapter(adapter);
        registerForContextMenu(seriesListView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstants.RequestCode.ADD_SERIES) {
            if (resultCode == RESULT_OK) {
                Series series = (Series) data.getSerializableExtra(AppConstants.SERIES_DATA_TAG);

                dataBaseHandler.addSeries(series);
                adapter.changeCursor(dataBaseHandler.getSeriesData());
            }
        }
        if (requestCode == AppConstants.RequestCode.EDIT_SERIES) {
            if (resultCode == RESULT_OK) {
                Series series = (Series) data.getSerializableExtra(AppConstants.SERIES_DATA_TAG);

                dataBaseHandler.updateSeries(series);
                adapter.changeCursor(dataBaseHandler.getSeriesData());
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_series_context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Series series = getSeriesFromCursor((SQLiteCursor) seriesListView.getItemAtPosition(info.position));
        switch (item.getItemId()) {
            case R.id.next_episode_menu_item:
                incrementEpisode(series);
                return true;
            case R.id.next_season_menu_item:
                incrementSeason(series);
                return true;
            case R.id.delete_series_menu_item:
                deleteSeries(series);
                return true;
            case R.id.edit_series_menu_item:
                startEditSeriesActivity(series);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void startEditSeriesActivity(Series series) {
        Intent intent = new Intent(this, EditSeriesActivity.class);
        SeriesActivity activity = this;
        intent.putExtra(AppConstants.SERIES_DATA_TAG, series);
        activity.startActivityForResult(intent, AppConstants.RequestCode.EDIT_SERIES);
    }

    private void incrementSeason(Series series) {
        series.incrementSeason();
        dataBaseHandler.updateSeries(series);
        adapter.changeCursor(dataBaseHandler.getSeriesData());
    }

    private void incrementEpisode(Series series) {
        series.incrementEpisde();
        dataBaseHandler.updateSeries(series);
        adapter.changeCursor(dataBaseHandler.getSeriesData());
    }

    private void deleteSeries(Series series) {
        dataBaseHandler.deleteSeries(series);
        adapter.changeCursor(dataBaseHandler.getSeriesData());
    }

    private Series getSeriesFromCursor(Cursor cursor) {
        try
        {
            int idIndex = cursor.getColumnIndexOrThrow(AppConstants.DataBase.KEY_ID);
            int titleIndex = cursor.getColumnIndexOrThrow(AppConstants.DataBase.TITLE);
            int seasonIndex = cursor.getColumnIndexOrThrow(AppConstants.DataBase.SEASON);
            int episodeIndex = cursor.getColumnIndexOrThrow(AppConstants.DataBase.EPISODE);

            long id = cursor.getLong(idIndex);
            String title = cursor.getString(titleIndex);
            int season = cursor.getInt(seasonIndex);
            int episode = cursor.getInt(episodeIndex);

            return new Series(id, title, season, episode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
