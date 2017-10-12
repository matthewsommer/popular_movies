package io.mattsommer.ui.movie;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import io.mattsommer.data.model.Movie;
import io.mattsommer.popularmovies.BuildConfig;
import io.mattsommer.popularmovies.R;

public class MovieFragment extends Fragment {

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

    private void updateMovies() {
        FetchMoviesTask moviesTask = new FetchMoviesTask();
        moviesTask.execute();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        updateMovies();
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

            List<Movie> movies = new ArrayList<Movie>();

            for(int i = 0; i < moviesArray.length(); i++) {
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
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String JsonResponseStr = null;
            Uri builtUri = null;

            final String APPID_PARAM = "api_key";

            try {
                final String MDB_BASE_URL =
                        "http://api.themoviedb.org/3/movie";
                final String MDB_POPULAR = "popular";
                final String MDB_TOP_RATED = "top_rated";

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                String sortValue = preferences.getString("sort", "");

                if(sortValue.equalsIgnoreCase("popularity")) {
                    builtUri = Uri.parse(MDB_BASE_URL).buildUpon()
                            .appendPath(MDB_POPULAR)
                            .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_DB_API_KEY)
                            .build();
                }
                else if (sortValue.equalsIgnoreCase("rating")) {
                    builtUri = Uri.parse(MDB_BASE_URL).buildUpon()
                            .appendPath(MDB_TOP_RATED)
                            .appendQueryParameter(APPID_PARAM, BuildConfig.MOVIE_DB_API_KEY)
                            .build();
                }
                else {
                    Log.e("Error ", "Sort preference not set properly");
                    return null;
                }

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                JsonResponseStr = buffer.toString();
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
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getMovieDataFromJson(JsonResponseStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie>  result) {
            if (result != null) {
                mMovieAdapter.clear();
                for(Movie movie : result) {
                    mMovieAdapter.add(movie);
                }
            }
        }
    }
}