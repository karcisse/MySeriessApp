package com.karol.myseriesapp.model;

import java.io.Serializable;

/**
 * Created by Karol on 20/02/2017.
 */

public class Series implements Serializable {
    private long id; // is also position in List
    private String seriesTitle;
    private int seasonNumber;
    private int episodeNumber;

    public Series(long id, String seriesTitle, int seasonNumber, int episodeNumber) {
        this.id = id;
        this.seriesTitle = seriesTitle;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public Series(String seriesTitle, int seasonNumber, int episodeNumber) {
        this.id = -1;
        this.seriesTitle = seriesTitle;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public Series() {
        this.id = -1;
        this.seriesTitle = "";
        this.seasonNumber = 1;
        this.episodeNumber = 1;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
