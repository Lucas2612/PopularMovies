package com.example.android.popularmovies.data;

import java.util.Date;

/**
 * Created by lucas on 02/02/2017.
 */

public class PopularMovie {

    String poster_path;
    boolean adult;
    String overview;
    Date release_date; //2016-09-14
    int[] genre_ids;
    int id;
    String original_title;
    String original_language;
    String title;
    String backdrop_path;
    double popularity;
    int vote_count;
    boolean video;
    double vote_average;

    public PopularMovie(){

    }

    public PopularMovie(String poster_path,
                        boolean adult,
                        String overview,
                        Date release_date,
                        int[] genre_ids,
                        int id,
                        String original_title,
                        String original_language,
                        String title,
                        String backdrop_path,
                        double popularity,
                        int vote_count,
                        boolean video,
                        double vote_average){
        this.poster_path = poster_path;
        this.adult = adult;
        this.overview = overview;
        this.release_date = release_date;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_title = original_title;
        this.original_language = original_language;
        this.title = title;
        this.backdrop_path = backdrop_path;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.video = video;
        this.vote_average = vote_average;
    }
}
