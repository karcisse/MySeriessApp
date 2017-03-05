package com.karol.myseriesapp.controller;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.karol.myseriesapp.R;
import com.karol.myseriesapp.constants.AppConstants;

/**
 * Created by Karol on 05/03/2017.
 */

public class SeriesCursorAdapter extends CursorAdapter {
    private LayoutInflater cursorInflater;

    public SeriesCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
        cursorInflater = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return cursorInflater.inflate(R.layout.series_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
// Find fields to populate in inflated template
        TextView seriesTitle = (TextView) view.findViewById(R.id.series_title_field);
        TextView seasonNumber = (TextView) view.findViewById(R.id.season_number_field);
        TextView episodeNumber = (TextView) view.findViewById(R.id.episode_number_field);
        // Extract properties from cursor
        String title = cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.SERIES_TITLE));
        int season = cursor.getInt(cursor.getColumnIndexOrThrow(AppConstants.SEASON_NUMBER));
        int episode = cursor.getInt(cursor.getColumnIndexOrThrow(AppConstants.EPISODE_NUMBER));
        // Populate fields with extracted properties
        seriesTitle.setText(title);
        seasonNumber.setText(String.valueOf(season));
        episodeNumber.setText(String.valueOf(episode));
    }
}
