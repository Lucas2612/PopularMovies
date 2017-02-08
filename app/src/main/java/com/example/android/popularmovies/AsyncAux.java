package com.example.android.popularmovies;

import com.example.android.popularmovies.data.PopularMovie;

import java.util.List;

/**
 * Created by lucas on 08/02/2017.
 */

public interface AsyncAux<T> {
    public void onTaskComplete(List<PopularMovie> result);
}
