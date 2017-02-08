package com.example.android.popularmovies.utilities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.popularmovies.AsyncAux;
import com.example.android.popularmovies.DetailActivity;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.data.MovieAdapter;
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
    protected void onPostExecute(List<PopularMovie> result) {
        super.onPostExecute(result);
        if (result!=null) {
            final MovieAdapter movieAdapter = new MovieAdapter(context, result);
            // Get a reference to the ListView, and attach this adapter to it.
            AppCompatActivity appCompatActivity = (AppCompatActivity) context;
            GridView gridView = (GridView) appCompatActivity.findViewById(R.id.movies_grid);
            gridView.setAdapter(movieAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    PopularMovie popularMovie = (PopularMovie) adapterView.getItemAtPosition(position);
                    Class destinationClass = DetailActivity.class;
                    Intent movieDetailIntent = new Intent(context, destinationClass);
                    movieDetailIntent.putExtra("movieDetail", popularMovie);
                    context.startActivity(movieDetailIntent);
                }
            });
        }
        listener.onTaskComplete(result);
    }
}