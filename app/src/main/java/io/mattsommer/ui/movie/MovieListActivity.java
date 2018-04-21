package io.mattsommer.ui.movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import io.mattsommer.data.contract.MovieContract.SORT;
import io.mattsommer.udacity.android.example.R;
import io.mattsommer.user.preferences.SortPreference;

/**
 * MovieListActivity.java Initial activity used by intent 'android.intent.action.MAIN' specified in
 * AndroidManifest.xml
 *
 * @author Matt Sommer
 */
public class MovieListActivity extends AppCompatActivity {

  private final String LOG_TAG = MovieListActivity.class.getSimpleName();

  /**
   * Called when activity is starting https://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle)
   *
   * @param savedInstanceState Bundle: If the activity is being re-initialized after previously
   * being shut down then this Bundle contains the data it most recently supplied in
   * onSaveInstanceState(Bundle). Note: Otherwise it is null.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {

    // When overriding onCreate you must call through to superclass implementation
    super.onCreate(savedInstanceState);

    // Set the UI layout resource for this activity
    setContentView(R.layout.activity_main);

    // If no saved state create a new fragment and add/commit to fragment manager
    if (savedInstanceState == null) {
      Log.i(LOG_TAG, "savedInstanceState null");
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new MovieFragment())
          .commit();
    } else {
      Log.i(LOG_TAG, "savedInstanceState not null");
    }
  }

  /**
   * Called after onCreate(Bundle) — or after onRestart() when the activity had been stopped, but is
   * now again being displayed to the user. It will be followed by onResume().
   */
  @Override
  public void onStart() {

    // Must call superclass implementation
    super.onStart();

    setTitle(SortPreference.getText(this));
  }

  /**
   * Initializes the contents of the Activity's standard options menu. Menu items are in the
   * resources menu directory. This is only called once, the first time the options menu is
   * displayed. To update the menu every time it is displayed, use onPrepareOptionsMenu(Menu).
   * Returns false by default
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  /**
   * Called when menu item selected
   */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    // Set sort preference based on menu item selected
    if (item.getItemId() == R.id.action_sort_popular) {
      SortPreference.setPreference(this, SORT.POPULARITY.getCode());
    } else if (item.getItemId() == R.id.action_sort_rating) {
      SortPreference.setPreference(this, SORT.RATING.getCode());
    }

    // Set title in action bar
    setTitle(SortPreference.getText(this));

    // Retrieve fragment to update movies
    MovieFragment movieFragment = (MovieFragment) getSupportFragmentManager()
        .findFragmentById(R.id.container);

    // If fragment isn't null call update movies method
    if (movieFragment != null) {
      movieFragment.updateMovies();
    }

    return super.onOptionsItemSelected(item);
  }
}
