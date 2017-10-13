package io.mattsommer.ui.movie;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import io.mattsommer.popularmovies.R;
import io.mattsommer.ui.settings.SettingsActivity;

/**
 * MovieListActivity.java Initial activity used by intent 'android.intent.action.MAIN' specified in
 * AndroidManifest.xml
 *
 * @author Matt Sommer
 */
public class MovieListActivity extends AppCompatActivity {

  private final String LOG_TAG = MovieListActivity.class.getSimpleName();

  /**
   * Called when activity is starting
   * https://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle)
   *
   * @param savedInstanceState Bundle: If the activity is being re-initialized after previously being shut down then this Bundle contains the data it
   * most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
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
   * Called after onCreate(Bundle) — or after onRestart() when the activity had been
   * stopped, but is now again being displayed to the user. It will be followed by onResume().
   */
  @Override
  public void onStart() {

    // When overriding onStart you must call through to superclass implementation
    super.onStart();
    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
    if (sharedPref.getString("sort", "").matches("popularity")) {
      setTitle(getString(R.string.recent_popular));
    } else {
      setTitle(getString(R.string.all_time_rating));
    }
  }

  /**
   * Initializes the contents of the Activity's standard options menu.
   * Menu items are in the resources menu directory.
   * This is only called once, the first time the options menu is displayed.
   * To update the menu every time it is displayed, use onPrepareOptionsMenu(Menu).
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
    int menuItemId = item.getItemId();
    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

    SharedPreferences.Editor editor = preferences.edit();

    if (menuItemId == R.id.action_sort_popular) {
      editor.putInt("sortId", 0); // value to store
    }

    if (menuItemId == R.id.action_sort_rating) {
      editor.putInt("sortId", 1); // value to store
    }

    editor.commit();

    Intent intent = getIntent();
    finish();
    startActivity(intent);

    return super.onOptionsItemSelected(item);
  }
}
