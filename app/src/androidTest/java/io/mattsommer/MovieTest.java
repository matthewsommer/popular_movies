package io.mattsommer;

import android.os.Parcel;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import io.mattsommer.data.model.Movie;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Matt Sommer on 10/9/17.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest

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
        movie = new Movie(id, original_title, release_date, poster_path, vote_average, overview);
    }

    @Test
    public void describeContents() throws Exception {

    }

    @Test
    public void writeToParcel() throws Exception {

        Parcel parcel = Parcel.obtain();
        movie.writeToParcel(parcel, movie.describeContents());

        parcel.setDataPosition(0);

        Movie createdFromParcel = Movie.CREATOR.createFromParcel(parcel);

        assertThat(createdFromParcel.getId(), is(id));
        assertThat(createdFromParcel.getOriginal_title(), is(original_title));
        assertThat(createdFromParcel.getVote_average(), is(vote_average));
        assertThat(createdFromParcel.getRelease_date(), is(release_date));
        assertThat(createdFromParcel.getOverview(), is(overview));
        assertThat(createdFromParcel.getPoster_path(), is(poster_path));
    }

}