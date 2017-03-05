package com.karol.myseriesapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.R;
import com.karol.myseriesapp.model.Series;

import java.util.HashMap;

// TODO: 05/03/2017 handle empty data input
public class AddSeriesActivity extends AppCompatActivity {
    HashMap<String, EditText> fieldsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_series);
        setUpDataFields();
    }

    private void setUpDataFields() {
        fieldsMap = new HashMap<String, EditText>();
        fieldsMap.put(AppConstants.SERIES_TITLE, (EditText) findViewById(R.id.series_name));
        fieldsMap.put(AppConstants.SEASON_NUMBER, (EditText) findViewById(R.id.season_number));
        fieldsMap.put(AppConstants.EPISODE_NUMBER, (EditText) findViewById(R.id.episode_number));


        fieldsMap.get(AppConstants.EPISODE_NUMBER).setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    findViewById(R.id.add_button).performClick();
                    return true;
                }
                return false;
            }
        });
    }

    public HashMap<String, EditText> getFieldsMap() {
        return fieldsMap;
    }

    public boolean validateSeriesTitle() {
        if (fieldsMap.get(AppConstants.SERIES_TITLE).getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateEpisodeNubmer() {
        if (fieldsMap.get(AppConstants.EPISODE_NUMBER).getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean validateSeasonNumber() {
        if (fieldsMap.get(AppConstants.SEASON_NUMBER).getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }
}
