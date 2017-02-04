package com.example.android.popularmovies.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lucas on 03/02/2017.
 */

public class MovieAdapter extends ArrayAdapter<PopularMovie> {

    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMG_SIZE = "w342/";
    private static final String LOG_TAG = "MovieAdapter";

    public MovieAdapter(Context context, List<PopularMovie> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Gets the AndroidFlavor object from the ArrayAdapter at the appropriate position
        PopularMovie movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_item, parent, false);
        }

        ImageView poster = (ImageView) convertView.findViewById(R.id.movie_image);
        String pathImage = BASE_URL + IMG_SIZE + movie.poster_path;
        Log.v(LOG_TAG, pathImage);
        Picasso.with(getContext()).load(pathImage).into(poster);
        //iconView.setImageResource(androidFlavor.image);

        //TextView titleView = (TextView) convertView.findViewById(R.id.movie_text);
        //titleView.setText(movie.title);

        return convertView;
    }
}
