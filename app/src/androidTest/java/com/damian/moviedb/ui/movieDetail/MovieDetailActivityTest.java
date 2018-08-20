package com.damian.moviedb.ui.movieDetail;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.damian.moviedb.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class MovieDetailActivityTest {

    private Intent intent;

    @Rule
    public ActivityTestRule<MovieDetailActivity> activityTestRule = new ActivityTestRule(MovieDetailActivity.class);

    @Before
    public void before() {
        intent = new Intent();
    }

    @Test
    public void test_activitySetUp() {
        activityTestRule.launchActivity(intent);
        Espresso.onView(ViewMatchers.withId(R.id.movie_detail)).check(matches(isDisplayed()));
    }
}
