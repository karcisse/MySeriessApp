package com.karol.myseriesapp.controls;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.karol.myseriesapp.activities.EditSeriesActivity;
import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.model.Series;

import java.util.HashMap;

/**
 * Created by Karol on 05/03/2017.
 */

public class EditSeriesButton extends Button implements View.OnClickListener {

    public EditSeriesButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public EditSeriesButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public EditSeriesButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
        EditSeriesActivity activity = (EditSeriesActivity) getContext();

        Series series = prepareSeries(activity);

        intent.putExtra(AppConstants.SERIES_DATA_TAG, series);

        activity.setResult(Activity.RESULT_OK, intent);
        activity.finish();
    }

    private Series prepareSeries(EditSeriesActivity activity) {
        HashMap<String, EditText> seriesHashMap = activity.getFieldsMap();
        return new Series(activity.getSeriesId(), seriesHashMap.get(AppConstants.SERIES_TITLE).getText().toString(),
                Integer.parseInt(seriesHashMap.get(AppConstants.SEASON_NUMBER).getText().toString()),
                Integer.parseInt(seriesHashMap.get(AppConstants.EPISODE_NUMBER).getText().toString())
        );
    }
}
