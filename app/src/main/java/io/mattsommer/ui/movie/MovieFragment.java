package io.mattsommer.ui.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import io.mattsommer.data.contract.MovieContract.SORT;
import io.mattsommer.data.model.Movie;
import io.mattsommer.networking.FetchMovies;
import io.mattsommer.udacity.android.example.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MovieFragment extends Fragment {

  private final String LOG_TAG = MovieFragment.class.getSimpleName();

  private MovieAdapter mMovieAdapter;

  public MovieFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_main, container, false);

    mMovieAdapter = new MovieAdapter(getActivity(), new ArrayList<Movie>());

    GridView gridview = (GridView) rootView.findViewById(R.id.gridview_movie);
    gridview.setAdapter(mMovieAdapter);

    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Movie movie = mMovieAdapter.getItem(position);
        Intent intent = new Intent(getActivity(), MovieDetailActivity.class)
            .putExtra("Movie", movie);
        startActivity(intent);
      }
    });

    return rootView;
  }

  @Override
  public void onStart() {
    super.onStart();
    updateMovies();
  }

  @Override
  public void onResume() {
    super.onResume();
    updateMovies();
  }

  @Override
  public void onPause() {
    super.onPause();
  }

  public void updateMovies() {
    Log.i(LOG_TAG, "Entry updating movies");
    FetchMoviesTask moviesTask = new FetchMoviesTask();
    moviesTask.execute();
  }

  public class FetchMoviesTask extends AsyncTask<Void, Void, List<Movie>> {

    private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

    private List<Movie> getMovieDataFromJson(String moviesJsonStr)
        throws JSONException {

      final String MDB_RESULTS = "results";
      final String MDB_ID = "id";
      final String MDB_ORIGINAL_TITLE = "original_title";
      final String MDB_RELEASE_DATE = "release_date";
      final String MDB_POSTER_PATH = "poster_path";
      final String MDB_VOTE_AVG = "vote_average";
      final String MDB_OVERVIEW = "overview";

      JSONObject moviesJson = new JSONObject(moviesJsonStr);
      JSONArray moviesArray = moviesJson.getJSONArray(MDB_RESULTS);

      List<Movie> movies = new ArrayList<>();

      for (int i = 0; i < moviesArray.length(); i++) {
        JSONObject movieObject = moviesArray.getJSONObject(i);

        Movie movie = new Movie(movieObject.getString(MDB_ID),
            movieObject.getString(MDB_ORIGINAL_TITLE),
            movieObject.getString(MDB_RELEASE_DATE),
            movieObject.getString(MDB_POSTER_PATH),
            movieObject.getString(MDB_VOTE_AVG),
            movieObject.getString(MDB_OVERVIEW));

        movies.add(movie);
      }
      return movies;
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {

      SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
      Resources res = getResources();
      String sortPrefKey = res.getString(R.string.pref_sort_order_key);
      int storedInt = preferences.getInt(sortPrefKey, 0);
      SORT sortValueEnum = SORT.fromInteger(storedInt);

      try {
        return getMovieDataFromJson(FetchMovies.Fetch(sortValueEnum));
      } catch (JSONException e) {
        Log.e(LOG_TAG, e.getMessage(), e);
        e.printStackTrace();
      }
      return null;
    }

    @Override
    protected void onPostExecute(List<Movie> result) {
      if (result != null) {
        mMovieAdapter.clear();
        for (Movie movie : result) {
          mMovieAdapter.add(movie);
        }
      }
    }
  }
}