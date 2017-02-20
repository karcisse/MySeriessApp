package com.karol.myseriesapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.R;
import com.karol.myseriesapp.controls.AddSeriesButton;

import java.util.HashMap;

public class AddSeriesActivity extends AppCompatActivity {
    private AddSeriesButton addSeriesButton;
    HashMap<String, EditText> fieldsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        setUpDataFields();
        setUpControls();
    }

    private void setUpControls() {
        addSeriesButton = (AddSeriesButton) findViewById(R.id.add_button);
    }

    private void setUpDataFields() {
        fieldsMap = new HashMap<String, EditText>();
        fieldsMap.put(AppConstants.SERIES_TITLE, (EditText) findViewById(R.id.series_name));
        fieldsMap.put(AppConstants.SEASON_NUMBER, (EditText) findViewById(R.id.season_number));
        fieldsMap.put(AppConstants.EPISODE_NUMBER, (EditText) findViewById(R.id.episode_number));
    }

    public HashMap<String, EditText> getFieldsMap() {
        return fieldsMap;
    }
}
