package com.example.islam.cars.availablecarslist.view;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.islam.cars.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by islam on 29/05/17.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AvailableCarsListUITest {
    @Rule
    public IntentsTestRule<AvailableCarsListActivity> activityRule =
            new IntentsTestRule<>(AvailableCarsListActivity.class);

    /**
     * Prepare your test fixture for this test. In this case we register an IdlingResources with
     * Espresso. IdlingResource resource is a great way to tell Espresso when your app is in an
     * idle state. This helps Espresso to synchronize your test actions, which makes tests significantly
     * more reliable.
     */
    @Before
    public void registerIdlingResource() {
        Espresso.registerIdlingResources(
                activityRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void checkAvailableCarsListISDisplayed() {
        onView(withId(R.id.recyclerview_available_cars))
                .check(matches(isDisplayed()));
    }

    @Test
    public void checkWhenAvailableCarsListLoaded_LoadingBarIsHidden() {
        onView(withId(R.id.progressBar_loading))
                .check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }

    @After
    public void unregisterIdlingResource() {
        Espresso.unregisterIdlingResources(
                activityRule.getActivity().getCountingIdlingResource());
    }
}