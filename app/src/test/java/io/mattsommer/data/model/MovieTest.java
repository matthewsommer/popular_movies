package io.mattsommer.data.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Matt Sommer on 10/9/17.
 */
public class MovieTest {

    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie("0","Test","Test","Test","0","Overview");
    }

    @Test
    public void describeContents() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {

    }

    @Test
    public void getId() throws Exception {
        assertNotNull(movie.getId());
    }

    @Test
    public void getOriginal_title() throws Exception {
        assertNotNull(movie.getOriginal_title());
    }

    @Test
    public void setOriginal_title() throws Exception {

    }

    @Test
    public void getRelease_date() throws Exception {

    }

    @Test
    public void setRelease_date() throws Exception {

    }

    @Test
    public void getPoster_path() throws Exception {

    }

    @Test
    public void setPoster_path() throws Exception {

    }

    @Test
    public void getVote_average() throws Exception {

    }

    @Test
    public void setVote_average() throws Exception {

    }

    @Test
    public void getOverview() throws Exception {

    }

    @Test
    public void setOverview() throws Exception {

    }

}