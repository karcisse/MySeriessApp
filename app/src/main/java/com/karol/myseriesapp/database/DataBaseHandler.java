package com.karol.myseriesapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.karol.myseriesapp.constants.AppConstants;
import com.karol.myseriesapp.model.Series;

import java.util.ArrayList;

public class DataBaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public DataBaseHandler(Context context) {
        super(context, AppConstants.DataBase.DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + AppConstants.DataBase.TABLE_NAME + "(" + AppConstants.DataBase.KEY_ID + " INTEGER PRIMARY KEY, " 
                + AppConstants.DataBase.TITLE + " TEXT, " + AppConstants.DataBase.SEASON + " INTEGER, "
                + AppConstants.DataBase.EPISODE + " INTEGER " + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO: 26/02/2017  make upgrade
    }

    public void addSeries(Series series) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(AppConstants.DataBase.TITLE, series.getSeriesTitle());
        values.put(AppConstants.DataBase.SEASON, series.getSeasonNumber());
        values.put(AppConstants.DataBase.EPISODE, series.getEpisodeNumber());

        db.insert(AppConstants.DataBase.TABLE_NAME, null, values);
        db.close();
    }

    public Series getSeries(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(AppConstants.DataBase.TABLE_NAME, new String[] { AppConstants.DataBase.KEY_ID,
                        AppConstants.DataBase.TITLE, AppConstants.DataBase.SEASON, AppConstants.DataBase.EPISODE}, AppConstants.DataBase.KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        // TODO: 26/02/2017 Make better colum indexes
        // return contact
        Integer keyId = cursor.getInt(0);
        String title = cursor.getString(1);
        Integer season = cursor.getInt(2);
        Integer episode = cursor.getInt(3);

        Series series = new Series(keyId, title, season, episode);
        return series;
    }

    public Cursor getSeriesData() {

        String selectQuery = "SELECT  * FROM " + AppConstants.DataBase.TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }

    public ArrayList<Series> getSeriesList() {
        ArrayList<Series> seriesList = new ArrayList<Series>();

        String selectQuery = "SELECT  * FROM " + AppConstants.DataBase.TABLE_NAME;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {
            Integer keyId = cursor.getInt(0);
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
        values.put(AppConstants.DataBase.KEY_ID, series.getId());
        values.put(AppConstants.DataBase.TITLE, series.getSeriesTitle());
        values.put(AppConstants.DataBase.SEASON, series.getSeasonNumber());
        values.put(AppConstants.DataBase.EPISODE, series.getEpisodeNumber());

        // updating row
        return db.update(AppConstants.DataBase.TABLE_NAME, values, AppConstants.DataBase.KEY_ID + " = ?",
                new String[] { String.valueOf(series.getId()) });
    }

    public void deleteSeries (Series series) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(AppConstants.DataBase.TABLE_NAME, AppConstants.DataBase.KEY_ID + " = ?",
                new String[] { String.valueOf(series.getId()) });
    }

    public int countSeries() {
        String countQuery = "SELECT  * FROM " + AppConstants.DataBase.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        return cursor.getCount();
    }
}
