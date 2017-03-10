package com.karol.myseriesapp.controls;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.karol.myseriesapp.activities.AddSeriesActivity;

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
        AddSeriesActivity activity = (AddSeriesActivity) getContext();
        activity.sendSeriesData();
    }
}
