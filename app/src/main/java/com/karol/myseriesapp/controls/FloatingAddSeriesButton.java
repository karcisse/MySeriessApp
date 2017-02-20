package com.karol.myseriesapp.controls;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.View;

import com.karol.myseriesapp.activities.AddSeriesActivity;
import com.karol.myseriesapp.activities.SeriesActivity;

/**
 * Created by Karol on 20/02/2017.
 */

public class FloatingAddSeriesButton extends FloatingActionButton implements View.OnClickListener {
    public FloatingAddSeriesButton(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public FloatingAddSeriesButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public FloatingAddSeriesButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(v.getContext(), AddSeriesActivity.class);
        SeriesActivity activity = (SeriesActivity) getContext();
        activity.startActivityForResult(intent, 1);
    }
}
