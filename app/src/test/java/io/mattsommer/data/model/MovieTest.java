package io.mattsommer.data.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by Matt Sommer on 10/9/17.
 */
public class MovieTest {

    private Movie movie;
    private static String id = "346364";
    private static String original_title = "It";
    private static String vote_average = "7.4";
    private static String release_date = "2017-09-05";
    private static String overview = "In a small town in Maine...";
    private static String poster_path = "/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg";

    @Before
    public void setUp() {
        movie = new Movie(id,original_title,release_date,poster_path,vote_average,overview);
    }

    @Test
    public void describeContents() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertEquals("id incorrect",id,movie.getId());
    }

    @Test
    public void getOriginal_title() throws Exception {
        assertEquals("original title incorrect",original_title,movie.getOriginal_title());
    }

    @Test
    public void getRelease_date() throws Exception {
        assertEquals("release date incorrect",release_date,movie.getRelease_date());
    }

    @Test
    public void getPoster_path() throws Exception {
        assertEquals("poster path incorrect",poster_path,movie.getPoster_path());
    }

    @Test
    public void getVote_average() throws Exception {
        assertEquals("vote average incorrect", vote_average, movie.getVote_average());
    }

    @Test
    public void getOverview() throws Exception {
        assertEquals("overview incorrect", overview, movie.getOverview());
    }

}