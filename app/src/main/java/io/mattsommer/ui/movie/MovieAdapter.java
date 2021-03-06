package io.mattsommer.ui.movie;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.squareup.picasso.Picasso;
import io.mattsommer.data.model.Movie;
import io.mattsommer.udacity.android.example.R;
import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {

  private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

  /**
   * This is our own custom constructor (it doesn't mirror a superclass constructor). The context is
   * used to inflate the layout file, and the List is the data we want to populate into the lists
   *
   * @param context The current context. Used to inflate the layout file.
   * @param movies A List of movie objects to display in a list
   */
  public MovieAdapter(Activity context, List<Movie> movies) {
    super(context, 0, movies);
  }

  /**
   * Provides a view for an AdapterView (ListView, GridView, etc.)
   *
   * @param position The AdapterView position that is requesting a view
   * @param convertView The recycled view to populate. (search online for "android view recycling"
   * to learn more)
   * @param parent The parent ViewGroup that is used for inflation.
   * @return The View for the position in the AdapterView.
   */
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Movie movie = getItem(position);

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext())
          .inflate(R.layout.list_item_movie, parent, false);
    }

    GridViewHolder gridViewHolder = new GridViewHolder(convertView);
    Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w500/" + movie.getPoster_path())
        .into(gridViewHolder.iconView);

    return convertView;
  }
}