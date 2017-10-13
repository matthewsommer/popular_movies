package io.mattsommer.data.contract;

import android.net.Uri;

/**
 * Created by matt on 10/12/17.
 */

public class MovieContract {

  public static final String SCHEME = "http://";
  public static final String CONTENT_AUTHORITY = "api.themoviedb.org";
  public static final String APP_ID_PARAM = "api_key";
  public static final String MDB_BASE_URL = SCHEME + CONTENT_AUTHORITY + "/3/movie";
  public static final String MDB_POPULAR = "popular";
  public static final String MDB_TOP_RATED = "top_rated";
  public static final String MDB_POPULARITY= "popularity";
  public static final String MDB_RATING = "rating";

}
