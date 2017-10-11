package io.mattsommer.popularmovies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * MainActivity.java Initial activity used by intent 'android.intent.action.MAIN' specified in
 * AndroidManifest.xml
 * @author Matt Sommer
 */
public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getSimpleName();

    /**
     * Called when activity is starting
     * https://developer.android.com/reference/android/app/Activity.html#onCreate(android.os.Bundle)
     *
     * @param savedInstanceState Bundle: If the activity is being re-initialized after previously
     *                           being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // When overriding onCreate you must call through to superclass implementation
        super.onCreate(savedInstanceState);

        // Set the UI layout resource for this activity
        setContentView(R.layout.activity_main);

        // If no saved state create a new fragment and add/commit to fragment manager
        if (savedInstanceState == null) {
            Log.i(LOG_TAG,"savedInstanceState null");
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MovieFragment())
                    .commit();
        } else {
            Log.i(LOG_TAG,"savedInstanceState not null");
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
            setTitle(getString(R.string.title_recent_popular));
        } else {
            setTitle(getString(R.string.title_all_time_popular));
        }
    }

    /**
     * Initializes the contents of the Activity's standard options menu.
     * Menu items are in the resources menu directory.
     * This is only called once, the first time the options menu is displayed.
     * To update the menu every time it is displayed, use onPrepareOptionsMenu(Menu).
     * Returns false by default
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Called when menu item selected
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
