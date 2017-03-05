package com.karol.myseriesapp.model;

import java.io.Serializable;

/**
 * Created by Karol on 20/02/2017.
 */

public class Series implements Serializable {
    private String seriesTitle;
    private int seasonNumber;
    private int episodeNumber;

    public Series(String seriesTitle, int seasonNumber, int episodeNumber) {
        this.seriesTitle = seriesTitle;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getSeasonNumberString() {
        return String.valueOf(seasonNumber);
    }

    public String getEpisodeNuberString() {
        return String.valueOf(episodeNumber);
    }

    public void incrementSeason() {
        episodeNumber = 1;
        seasonNumber++;
    }

    public void incrementEpisde() {
        episodeNumber++;
    }
}
