package io.mattsommer.data.contract;

import android.net.Uri;

/**
 * Created by matt on 10/12/17.
 */

public class MovieContract {

  public static final String SCHEME = "http";
  public static final String CONTENT_AUTHORITY = "api.themoviedb.org";
  public static final String APP_ID_PARAM = "api_key";
  public static final String MDB_API_VERSION = "3";
  public static final String MDB_PATH = "movie";

  public enum SORT {
    POPULARITY(0, "popular"),
    RATING(1, "top_rated");

    private final int code;
    private final String description;

    SORT(int code, String description) {
      this.code = code;
      this.description = description;
    }

    public String getDescription() {
      return description;
    }

    public int getCode() {
      return code;
    }

    public static SORT fromInteger(int x) {
      switch(x) {
        case 0:
          return POPULARITY;
        case 1:
          return RATING;
      }
      return null;
    }

    @Override
    public String toString() {
      return code + ": " + description;
    }
  }

}
