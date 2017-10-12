package io.mattsommer.networking;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Created by matt on 10/12/17.
 */
public class FetchMoviesTest {
  @Test
  public void fetch() throws Exception {
    assertNotNull(FetchMovies.Fetch("popularity"));
  }
}