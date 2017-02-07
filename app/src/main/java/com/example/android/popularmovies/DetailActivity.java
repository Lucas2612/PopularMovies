package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.data.PopularMovie;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        PopularMovie popularMovie = (PopularMovie) intent.getParcelableExtra("movieDetail");

        Log.v("MovieTitle", popularMovie.getOriginal_title());
        Log.v("PopularMovie", popularMovie.getPoster_path());

        ImageView poster = (ImageView) findViewById(R.id.im_moviePoster);
        String pathImage = popularMovie.getPoster_path();
        Picasso.with(this).load(pathImage).into(poster);

        TextView tv_movieTitle = (TextView) findViewById(R.id.tv_movieTitle);
        tv_movieTitle.setText(popularMovie.getOriginal_title());

        TextView tv_movieReleaseDate = (TextView) findViewById(R.id.tv_movieReleaseDate);
        tv_movieReleaseDate.setText(popularMovie.getRelease_date_string());

        TextView tv_movieVoteAverage = (TextView) findViewById(R.id.tv_movieVoteAverage);
        tv_movieVoteAverage.setText(String.valueOf(popularMovie.getVote_average()));

        TextView tv_moviePlotSynopsis = (TextView) findViewById(R.id.tv_moviePlotSynopsis);
        tv_moviePlotSynopsis.setText(popularMovie.getOverview());

    }
}
