package com.karol.myseriesapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.karol.myseriesapp.R;
import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.model.Series;

import java.util.HashMap;

/**
 * Created by Karol on 05/03/2017.
 */

public class EditSeriesActivity extends AppCompatActivity {
    HashMap<String, EditText> fieldsMap;
    private Series series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_series);
        setUpDataFields();
        fillFieldsMap((Series) getIntent().getSerializableExtra(AppConstants.SERIES_DATA_TAG));
        this.series = (Series) getIntent().getSerializableExtra(AppConstants.SERIES_DATA_TAG);
    }

    private void fillFieldsMap(Series series) {
        fieldsMap.get(AppConstants.SERIES_TITLE).setText(series.getSeriesTitle());
        fieldsMap.get(AppConstants.SEASON_NUMBER).setText(series.getSeasonNumberString());
        fieldsMap.get(AppConstants.EPISODE_NUMBER).setText(series.getEpisodeNuberString());
    }

    private void setUpDataFields() {
        fieldsMap = new HashMap<String, EditText>();
        fieldsMap.put(AppConstants.SERIES_TITLE, (EditText) findViewById(R.id.series_name));
        fieldsMap.put(AppConstants.SEASON_NUMBER, (EditText) findViewById(R.id.season_number));
        fieldsMap.put(AppConstants.EPISODE_NUMBER, (EditText) findViewById(R.id.episode_number));
    }

    public long getSeriesId() {
        return series.getId();
    }

    public HashMap<String, EditText> getFieldsMap() {
        return fieldsMap;
    }
}
