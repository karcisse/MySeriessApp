package com.karol.myseriesapp.constants;

/**
 * Created by Karol on 20/02/2017.
 */

public class AppConstants {
    public static String SERIES_TITLE = "series_title";
    public static String SEASON_NUMBER = "season_number";
    public static String EPISODE_NUMBER = "episode_number";

    public static String SERIES_DATA_TAG = "series_data";

    public static class RequestCode {
        public static int ADD_SERIES = 1;
        public static int EDIT_SERIES = 2;
    }
    
    public static class DataBase {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "seriesDataBase";
        public static final String TABLE_NAME = "series";
        public static final String KEY_ID = "_id";
        public static final String TITLE = AppConstants.SERIES_TITLE;
        public static final String SEASON = AppConstants.SEASON_NUMBER;
        public static final String EPISODE = AppConstants.EPISODE_NUMBER;
    }
}
