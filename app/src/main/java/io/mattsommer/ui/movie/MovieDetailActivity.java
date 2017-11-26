package io.mattsommer.ui.movie;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import io.mattsommer.data.model.Movie;
import io.mattsommer.popularmovies.R;

public class MovieDetailActivity extends AppCompatActivity {

  //TODO: use different image sizes "poster_sizes": [
    //  "w92",
    //      "w154",
    //      "w185",
    //      "w342",
    //      "w500",
    //      "w780",
    //      "original"
    //      ],

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    if (savedInstanceState == null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new DetailFragment())
          .commit();
    }
  }

  public static class DetailFragment extends Fragment {

    private static final String LOG_TAG = DetailFragment.class.getSimpleName();
    private Movie movie;

    public DetailFragment() {
      setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {

      View rootView = inflater.inflate(R.layout.movie_detail, container, false);

      Intent intent = getActivity().getIntent();
      if (intent != null && intent.hasExtra("Movie")) {
        movie = intent.getParcelableExtra("Movie");
        Resources res = getResources();
        String releaseDate = res.getString(R.string.movie_release_date, movie.getRelease_date());
        String voteAverage = res.getString(R.string.movie_vote_average, movie.getVote_average());
        ((TextView) rootView.findViewById(R.id.detail_text))
            .setText(movie.getOriginal_title());
        ((TextView) rootView.findViewById(R.id.detail_release_date))
            .setText(releaseDate);
        ((TextView) rootView.findViewById(R.id.detail_vote_average))
            .setText(voteAverage);
        ((TextView) rootView.findViewById(R.id.detail_overview))
            .setText(movie.getOverview());

        ImageView iconView = (ImageView) rootView.findViewById(R.id.list_item_icon);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/" + movie.getPoster_path()).into(iconView);
      }

      return rootView;
    }
  }
}