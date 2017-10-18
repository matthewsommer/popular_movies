package io.mattsommer.user.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import io.mattsommer.data.contract.MovieContract.SORT;
import io.mattsommer.popularmovies.R;

/**
 * User preferences class to wrap around SortPreference
 * Created by matt on 10/17/17.
 */

public class SortPreference {

  public static int getPreference(Activity activity) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    return sharedPreferences.getInt("sort",0);
  }

  private static String getKeyFromResources(Resources resources) {
    return resources.getString(R.string.pref_sort_order_key);
  }

  public static void setPreference(Activity activity, int sortCode) {
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt(SortPreference.getKeyFromResources(activity.getResources()), sortCode);
    editor.apply();
  }

  public static String getText(Activity activity) {
    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);

    if (sharedPref.getInt(activity.getResources().getString(R.string.pref_sort_order_key),0) == SORT.POPULARITY.getCode()) {
      return activity.getResources().getString(R.string.recently_popular);
    } else if (sharedPref.getInt(activity.getResources().getString(R.string.pref_sort_order_key),0) == SORT.RATING.getCode()) {
      return activity.getResources().getString(R.string.all_time_rating);
    } else {
      return "";
    }
  }
}
