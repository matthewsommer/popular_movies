package io.mattsommer.networking;

import static junit.framework.Assert.assertNotNull;

import io.mattsommer.data.contract.MovieContract.SORT;
import org.junit.Test;

/**
 * Used to test fetching movie data from The Movie DB Created by matt on 10/12/17.
 */
public class FetchMoviesTest {

  @Test
  public void fetch() throws Exception {
    assertNotNull(FetchMovies.Fetch(SORT.POPULARITY));
  }

}