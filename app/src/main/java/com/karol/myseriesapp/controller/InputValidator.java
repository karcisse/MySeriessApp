package com.karol.myseriesapp.controller;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.karol.myseriesapp.constants.AppConstants;

import java.util.HashMap;

public class InputValidator {
    public static boolean validateInputs(HashMap<String, EditText> fieldsMap) {
        if (!validateEpisodeNumber(fieldsMap)) {
            return false;
        }
        if (!validateSeriesTitle(fieldsMap)) {
            return false;
        }
        if (!validateSeasonNumber(fieldsMap)){
            return false;
        } else {
            return true;
        }
    }

    private static boolean validateSeriesTitle(HashMap<String, EditText> fieldsMap) {
        if (fieldsMap.get(AppConstants.SERIES_TITLE).getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean validateEpisodeNumber(HashMap<String, EditText> fieldsMap) {
        if (fieldsMap.get(AppConstants.EPISODE_NUMBER).getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    private static boolean validateSeasonNumber(HashMap<String, EditText> fieldsMap) {
        if (fieldsMap.get(AppConstants.SEASON_NUMBER).getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    public static void displayErrorMessage(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
