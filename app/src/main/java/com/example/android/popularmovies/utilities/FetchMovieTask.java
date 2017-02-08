package com.example.android.popularmovies.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.android.popularmovies.AsyncAux;
import com.example.android.popularmovies.data.PopularMovie;

import java.net.URL;
import java.util.List;

/**
 * Created by lucas on 08/02/2017.
 */

public class FetchMovieTask extends AsyncTask<String, Void, List<PopularMovie>> {
    private static final String TAG = "FetchMovieTask";
    private ProgressDialog dialog;

    private Context context;
    private AsyncAux<List<PopularMovie>> listener;

    public FetchMovieTask(Context ctx, AsyncAux<List<PopularMovie>> listener) {
        this.context = ctx;
        this.listener = listener;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<PopularMovie> doInBackground(String... params) {
        String order = null;
        if (params.length==1){
            order = params[0];
        }
        URL weatherRequestUrl = NetworkUtils.buildUrl(order);

        try {
            String strMovies = NetworkUtils.getResponseFromHttpUrl(weatherRequestUrl);
            List<PopularMovie> listMovies = JsonUtils.getJsonValues(strMovies);

            return listMovies;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<PopularMovie> myPojo) {
        super.onPostExecute(myPojo);
        listener.onTaskComplete(myPojo);
    }
}