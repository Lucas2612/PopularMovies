package com.example.android.popularmovies.utilities;


import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import static android.content.ContentValues.TAG;
import static com.example.android.popularmovies.utilities.SettingUtils.api_key;

/**
 * Created by lucas on 02/02/2017.
 */

public class NetworkUtils {

    public final static String URL_MOVIE_BASE = "https://api.themoviedb.org/3/movie/";
    public final static String API_KEY = api_key;

    public static URL buildUrl(String order) {
        Uri builtUri = Uri.parse(URL_MOVIE_BASE).buildUpon()
                .appendPath(order)
                .appendQueryParameter("api_key",api_key )
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
