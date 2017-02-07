package com.example.android.popularmovies.utilities;

import android.util.Log;

import com.example.android.popularmovies.data.PopularMovie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by lucas on 02/02/2017.
 */

public class JsonUtils {

    private static String RESULTS = "results";
    private static final String TAG = "JsonUtils";

    public static List<PopularMovie> getJsonValues(String movies) throws JSONException {
        List<PopularMovie> listMovies = new ArrayList<PopularMovie>();
        String[] parsedMovieData = null;

        JSONObject forecastJson = new JSONObject(movies);
        JSONArray movieArray = forecastJson.getJSONArray(RESULTS);
        parsedMovieData = new String[movieArray.length()];

        for (int i = 0; i < movieArray.length(); i++) {

            JSONObject movieJson = movieArray.getJSONObject(i);

            PopularMovie movie= new PopularMovie(movieJson.getString(JsonMovies.POSTER_PATH_STRING),
                    movieJson.getBoolean(JsonMovies.ADULT_BOOLEAN),
                    movieJson.getString(JsonMovies.OVERVIEW_STRING),
                    convertStringToDate(movieJson.getString(JsonMovies.RELEASE_DATE)),
                    getJsonArrayInt(movieJson.getJSONArray(JsonMovies.GENRE_IDS_ARRAY_INT)),
                    movieJson.getInt(JsonMovies.ID_INT),
                    movieJson.getString(JsonMovies.ORIGINAL_TITLE_STRING),
                    movieJson.getString(JsonMovies.ORIGINAL_LANGUAGE_STRING),
                    movieJson.getString(JsonMovies.TITLE_STRING),
                    movieJson.getString(JsonMovies.BACKDROP_STRING),
                    movieJson.getDouble(JsonMovies.POPULARITY_DOUBLE),
                    movieJson.getInt(JsonMovies.VOTE_COUNT_INT),
                    movieJson.getBoolean(JsonMovies.ADULT_BOOLEAN),
                    movieJson.getDouble(JsonMovies.VOTE_AVERAGE_DOUBLE)
                    );
            Log.v(TAG, movie.toString());
            listMovies.add(movie);
        }


        return listMovies;
    }

    private static int[] getJsonArrayInt(JSONArray jsonArray) throws JSONException {
        int[] intArray = new int[jsonArray.length()];
        for (int i=0; i < jsonArray.length(); i++){
            intArray[i] = jsonArray.getInt(i);
        }
        return intArray;
    }

    private static Date convertStringToDate(String dateString)  {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            Log.e("DateFormat", "Exception in parse date" + e.getMessage());
        }
        return date;
    }

    public class JsonMovies{
        public static final String POSTER_PATH_STRING = "poster_path";
        public static final String ADULT_BOOLEAN = "adult";
        public static final String OVERVIEW_STRING = "overview";
        public static final String RELEASE_DATE = "release_date"; //2016-09-14
        public static final String GENRE_IDS_ARRAY_INT = "genre_ids";
        public static final String ID_INT = "id";
        public static final String ORIGINAL_TITLE_STRING = "original_title";
        public static final String ORIGINAL_LANGUAGE_STRING = "original_language";
        public static final String TITLE_STRING = "title";
        public static final String BACKDROP_STRING = "backdrop_path";
        public static final String POPULARITY_DOUBLE = "popularity";
        public static final String VOTE_COUNT_INT = "vote_count";
        public static final String VIDEO_BOOLEAN = "video";
        public static final String VOTE_AVERAGE_DOUBLE = "vote_average";
    }
}
