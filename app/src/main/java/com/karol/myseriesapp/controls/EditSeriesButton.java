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
        EditSeriesActivity activity = (EditSeriesActivity) getContext();
        activity.sendSeriesData();
    }
}
