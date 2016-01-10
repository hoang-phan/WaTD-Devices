package com.eahackathon.watd.whosatthedoor;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.eahackathon.watd.whosatthedoor.helpers.GcmRegistrationHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Hoang Phan on 1/9/2016.
 */
public class MainActivityTest {
    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        GcmRegistrationHelper.setInstance(null);
    }

    @Test
    public void testMain_click_on_start_service() throws Exception {
        GcmRegistrationHelper mockHelper = mock(GcmRegistrationHelper.class);
        GcmRegistrationHelper.setInstance(mockHelper);

        onView(withId(R.id.btn_start)).perform(click());
        Thread.sleep(1000);

        verify(mockHelper).register();
    }
}
