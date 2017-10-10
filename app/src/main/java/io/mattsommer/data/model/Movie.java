package io.mattsommer.data.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Movie data type
 */
public final class Movie implements Parcelable {

    @NonNull
    String id;
    String original_title;
    String release_date;
    String poster_path;
    String vote_average;

    @Nullable
    String overview;

    /**
     * Constructor for Movie object
     * @param id                id of Movie
     * @param original_title    Original title of Movie
     * @param release_date      Release date of Movie
     * @param poster_path       URL path to Movie poster
     * @param vote_average      Average vote of Movie
     * @param overview          Movie overview
     */
    public Movie(@NonNull String id,
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

    /**
     * Constructor for parcelable object
     * @param in
     */
    private Movie(Parcel in) {
        id = in.readString();
        original_title = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        vote_average = in.readString();
        overview = in.readString();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}