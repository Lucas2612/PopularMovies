package com.example.android.popularmovies;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.android.popularmovies.data.MovieAdapter;
import com.example.android.popularmovies.data.PopularMovie;
import com.example.android.popularmovies.utilities.JsonUtils;
import com.example.android.popularmovies.utilities.NetworkUtils;

import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String order = "popularity.desc"; // default;

    private TextView mErrorMessageDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message);
        loadMovieData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sort_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.popular_order){
            order = getString(R.string.popular_order_api);
            loadMovieData();
            return true;
        }
        if (id==R.id.rated_order){
            order = getString(R.string.rated_order_api);
            loadMovieData();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadMovieData() {
        showWeatherDataView();
        //String location = SunshinePreferences.getPreferredWeatherLocation(this);
        // TODO check if it is online
        new FetchMovieTask().execute(order);
    }

    private void showWeatherDataView() {
        //mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        //mRecyclerView.setVisibility(View.VISIBLE);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, List<PopularMovie>> {
        private ProgressDialog dialog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = ProgressDialog.show(MainActivity.this, "", "Loading...");
            //mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<PopularMovie> doInBackground(String... params) {
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
        protected void onPostExecute(List<PopularMovie> popularMovies) {
            dialog.dismiss();
            if (popularMovies!=null){
                //showWeatherDataView();
                final MovieAdapter movieAdapter = new MovieAdapter(getBaseContext(), popularMovies);

                // Get a reference to the ListView, and attach this adapter to it.
                GridView gridView = (GridView) findViewById(R.id.movies_grid);
                gridView.setAdapter(movieAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        PopularMovie popularMovie= (PopularMovie)adapterView.getItemAtPosition(position);
                        //Toast.makeText(MainActivity.this, "" + popularMovie.getOriginal_title(), Toast.LENGTH_SHORT).show();
                        Class destinationClass = DetailActivity.class;
                        Intent movieDetailIntent = new Intent(getBaseContext(), destinationClass);
                        movieDetailIntent.putExtra("movieDetail", popularMovie);
                        startActivity(movieDetailIntent);
                    }
                });
            }else{
                mErrorMessageDisplay.setVisibility(View.VISIBLE);
                //GridView gridView = (GridView) findViewById(R.id.movies_grid);

                //showErrorMessage();
            }
        }
    }

}
