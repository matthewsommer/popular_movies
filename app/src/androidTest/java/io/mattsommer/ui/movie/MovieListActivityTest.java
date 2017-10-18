package io.mattsommer.ui.movie;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertEquals;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toolbar;
import io.mattsommer.popularmovies.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented tests for the default activity
 * Created by matt on 10/15/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MovieListActivityTest {

  @Rule
  public ActivityTestRule<MovieListActivity> mActivityRule = new ActivityTestRule<>(MovieListActivity.class);

  @Test
  public void views() {

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(withText("Recently Popular")).perform(click());

    String title = mActivityRule.getActivity().getTitle().toString();

    assertEquals("Title incorrect", "Recently Popular", title);
  }

  @Test
  public void testTitle() {

    openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());

    onView(withText("All-Time Popular")).perform(click());

    String title = mActivityRule.getActivity().getTitle().toString();

    assertEquals("Title incorrect", "All-Time Popular", title);
  }
}