package com.company.matt.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    String id;
    String original_title;
    String release_date;
    String poster_path;
    String vote_average;
    String overview;

    public Movie(String id,
                 String original_title,
                 String release_date,
                 String poster_path,
                 String vote_average,
                 String overview){
        this.id = id;
        this.original_title = original_title;
        this.release_date = release_date;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.overview = overview;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(id);
        out.writeString(original_title);
        out.writeString(release_date);
        out.writeString(poster_path);
        out.writeString(vote_average);
        out.writeString(overview);
    }

    public static final Parcelable.Creator<Movie> CREATOR
            = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    private Movie(Parcel in) {
        id = in.readString();
        original_title = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        vote_average = in.readString();
        overview = in.readString();
    }
}