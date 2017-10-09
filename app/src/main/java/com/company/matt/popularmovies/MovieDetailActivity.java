package com.company.matt.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
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
                movie = (Movie)intent.getParcelableExtra("Movie");
                ((TextView) rootView.findViewById(R.id.detail_text))
                        .setText(movie.original_title);
                ((TextView) rootView.findViewById(R.id.detail_release_date))
                        .setText("Released " + movie.release_date);
                ((TextView) rootView.findViewById(R.id.detail_vote_average))
                        .setText("Vote average " + movie.vote_average);
                ((TextView) rootView.findViewById(R.id.detail_overview))
                        .setText(movie.overview);

                ImageView iconView = (ImageView) rootView.findViewById(R.id.list_item_icon);
                Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/"+movie.poster_path).into(iconView);
            }

            return rootView;
        }
    }
}