package com.example.android.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lucas on 02/02/2017.
 */

public class PopularMovie implements Parcelable{

    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String IMG_SIZE = "w342/";

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
        this.poster_path = BASE_URL + IMG_SIZE + poster_path;
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

    protected PopularMovie(Parcel in) {
        poster_path = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        genre_ids = in.createIntArray();
        id = in.readInt();
        original_title = in.readString();
        original_language = in.readString();
        title = in.readString();
        backdrop_path = in.readString();
        popularity = in.readDouble();
        vote_count = in.readInt();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
    }

    public static final Creator<PopularMovie> CREATOR = new Creator<PopularMovie>() {
        @Override
        public PopularMovie createFromParcel(Parcel in) {
            return new PopularMovie(in);
        }

        @Override
        public PopularMovie[] newArray(int size) {
            return new PopularMovie[size];
        }
    };

    public String getOriginal_title(){
        return original_title;
    }

    public String getPoster_path(){
        return poster_path;
    }

    public Date getRelease_date(){
        return release_date;
    }

    public String getRelease_date_string(){
        String dateString = null;
        if (release_date!=null){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            dateString = formatter.format(release_date);
        }
        return dateString;
    }

    public double getVote_average(){
        return vote_average;
    }

    public String getOverview(){
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(poster_path);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(overview);
        parcel.writeIntArray(genre_ids);
        parcel.writeInt(id);
        parcel.writeString(original_title);
        parcel.writeString(original_language);
        parcel.writeString(title);
        parcel.writeString(backdrop_path);
        parcel.writeDouble(popularity);
        parcel.writeInt(vote_count);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeDouble(vote_average);
    }
}
