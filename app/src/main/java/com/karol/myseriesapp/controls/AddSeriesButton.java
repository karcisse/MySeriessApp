package com.karol.myseriesapp.controls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karol.myseriesapp.activities.AddSeriesActivity;
import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.model.Series;

import java.util.HashMap;

/**
 * Created by Karol on 20/02/2017.
 */

public class AddSeriesButton extends Button implements View.OnClickListener {
    public AddSeriesButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public AddSeriesButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public AddSeriesButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        AddSeriesActivity activity = (AddSeriesActivity) getContext();

        Series series = prepareSeries(activity);

        intent.putExtra(AppConstants.SERIES_DATA_TAG, series);

        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    private Series prepareSeries(AddSeriesActivity activity) {
        // TODO: 05/03/2017 exception to stop adding series
        HashMap<String, EditText> seriesHashMap = activity.getFieldsMap();
        if (!activity.validateEpisodeNubmer()) {
            return new Series();
        }
        if (!activity.validateSeriesTitle()) {
            return new Series();
        }
        if (!activity.validateSeasonNumber()){
            return new Series();
        } else {
            return new Series(seriesHashMap.get(AppConstants.SERIES_TITLE).getText().toString(),
                    Integer.parseInt(seriesHashMap.get(AppConstants.SEASON_NUMBER).getText().toString()),
                    Integer.parseInt(seriesHashMap.get(AppConstants.EPISODE_NUMBER).getText().toString())
            );
        }
    }
}
