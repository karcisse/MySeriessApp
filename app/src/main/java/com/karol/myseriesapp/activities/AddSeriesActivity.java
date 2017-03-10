package com.karol.myseriesapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.R;
import com.karol.myseriesapp.controller.InputValidator;
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

    public void sendSeriesData() {
        Intent intent = new Intent();

        if (!InputValidator.validateInputs(fieldsMap)) {
            InputValidator.displayErrorMessage(getApplicationContext(), "Please fill all fields");
            return;
        }

        Series series = prepareSeries();

        intent.putExtra(AppConstants.SERIES_DATA_TAG, series);

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private Series prepareSeries() {
        return new Series(fieldsMap.get(AppConstants.SERIES_TITLE).getText().toString(),
                Integer.parseInt(fieldsMap.get(AppConstants.SEASON_NUMBER).getText().toString()),
                Integer.parseInt(fieldsMap.get(AppConstants.EPISODE_NUMBER).getText().toString())
        );
    }
}
