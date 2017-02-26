package com.karol.myseriesapp.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.karol.myseriesapp.R;
import com.karol.myseriesapp.model.Series;

import java.util.ArrayList;

/**
 * Created by Karol on 26/02/2017.
 */

public class SeriesAdapter extends BaseAdapter {
    Context context;
    ArrayList<Series> seriesArrayList;
    private static LayoutInflater inflater = null;

    public SeriesAdapter(Context context, ArrayList<Series> seriesArrayList) {
        this.context = context;
        this.seriesArrayList = seriesArrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return seriesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return seriesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null) {
            vi = inflater.inflate(R.layout.series_row, null);
        }
        TextView seriesTitle = (TextView) vi.findViewById(R.id.series_title_field);
        seriesTitle.setText(seriesArrayList.get(position).getSeriesTitle());

        TextView seasonNumber = (TextView) vi.findViewById(R.id.season_number_field);
        seasonNumber.setText(seriesArrayList.get(position).getSeasonNumberString());

        TextView episodeNumber = (TextView) vi.findViewById(R.id.episode_number_field);
        episodeNumber.setText(seriesArrayList.get(position).getEpisodeNuberString());
        return vi;
    }
}
