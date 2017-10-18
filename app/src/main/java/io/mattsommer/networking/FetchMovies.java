package io.mattsommer.networking;

import static io.mattsommer.data.contract.MovieContract.SORT.POPULARITY;
import static io.mattsommer.data.contract.MovieContract.SORT.RATING;

import android.net.Uri;
import android.util.Log;
import io.mattsommer.data.contract.MovieContract;
import io.mattsommer.data.contract.MovieContract.SORT;
import io.mattsommer.popularmovies.BuildConfig;
import io.mattsommer.ui.movie.MovieFragment.FetchMoviesTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Network class for fetching movies
 * Created by matt on 10/12/17.
 */

public class FetchMovies {

  private static final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

  public static String Fetch(SORT sortValueEnum) {
    HttpURLConnection urlConnection = null;
    BufferedReader reader = null;
    String JsonResponseStr = null;

    Uri.Builder builder = new Uri.Builder();
    builder.scheme(MovieContract.SCHEME);
    builder.authority(MovieContract.CONTENT_AUTHORITY);
    builder.appendPath(MovieContract.MDB_API_VERSION);
    builder.appendPath(MovieContract.MDB_PATH);

    try {
      switch (sortValueEnum) {
        case RATING:
          Log.i(LOG_TAG, "Sorting by all-time rating.");
          builder.appendPath(RATING.getDescription());
          break;
        case POPULARITY:
          Log.i(LOG_TAG, "Sorting by popularity.");
          builder.appendPath(POPULARITY.getDescription());
          break;
        default:
          Log.e(LOG_TAG, "SortPreference preference not set. Defaulting to popularity.");
          builder.appendPath(POPULARITY.getDescription());
          break;
      }

      builder.appendQueryParameter(MovieContract.APP_ID_PARAM, BuildConfig.MOVIE_DB_API_KEY);

      String urlStr = builder.build().toString();
      Log.i(LOG_TAG, urlStr);
      URL url = new URL(urlStr);

      urlConnection = (HttpURLConnection) url.openConnection();
      urlConnection.setRequestMethod("GET");
      urlConnection.connect();

      InputStream inputStream = urlConnection.getInputStream();
      StringBuilder stringBuilder = new StringBuilder();
      if (inputStream == null) {
        return null;
      }
      reader = new BufferedReader(new InputStreamReader(inputStream));

      String line;
      while ((line = reader.readLine()) != null) {
        stringBuilder.append(line).append("\n");
      }

      if (stringBuilder.length() == 0) {
        return null;
      }
      JsonResponseStr = stringBuilder.toString();
    } catch (IOException e) {
      Log.e(LOG_TAG, "Error ", e);
      return null;
    } finally {
      if (urlConnection != null) {
        urlConnection.disconnect();
      }
      if (reader != null) {
        try {
          reader.close();
        } catch (final IOException e) {
          Log.e(LOG_TAG, Error.STREAM.toString(), e);
        }
      }
    }
    return JsonResponseStr;
  }
}