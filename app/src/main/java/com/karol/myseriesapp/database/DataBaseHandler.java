package com.karol.myseriesapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.karol.myseriesapp.model.Series;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "seriesDataBase";
    private static final String TABLE_NAME = "series";
    private static final String KEY_ID = "id";
    private static final String TITLE = "title";
    private static final String SEASON = "season_number";
    private static final String EPISODE = "episode_number";

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + TITLE + " TEXT, " + SEASON + " INTEGER, "
                + EPISODE + " INTEGER " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO: 26/02/2017  make upgrade
    }

    public void addSeries(Series series) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(TITLE, series.getSeriesTitle());
        values.put(SEASON, series.getSeasonNumber());
        values.put(EPISODE, series.getEpisodeNumber());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Series getSeries(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        TITLE, SEASON, EPISODE}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // TODO: 26/02/2017 Make better colum indexes
        // return contact
        String title = cursor.getString(1);
        Integer season = cursor.getInt(2);
        Integer episode = cursor.getInt(3);

        Series series = new Series(title, season, episode);
        return series;
    }

    public ArrayList<Series> getSeriesList() {
        ArrayList<Series> seriesList = new ArrayList<Series>();

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(1);
            Integer season = cursor.getInt(2);
            Integer episode = cursor.getInt(3);

            seriesList.add(new Series(title, season, episode));
        }

        return seriesList;
    }

    public int updateSeries(Series series) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TITLE, series.getSeriesTitle());
        values.put(SEASON, series.getSeasonNumber());
        values.put(EPISODE, series.getEpisodeNumber());

        // updating row
        return db.update(TABLE_NAME, values, TITLE + " = ?",
                new String[] { String.valueOf(series.getSeriesTitle()) });
    }

    public void deleteSeries (Series series) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, TITLE + " = ?",
                new String[] { String.valueOf(series.getSeriesTitle()) });
        db.close();
    }

    public int countSeries() {
        String countQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
