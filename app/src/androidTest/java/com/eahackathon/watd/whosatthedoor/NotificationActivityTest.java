package com.eahackathon.watd.whosatthedoor;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.eahackathon.watd.whosatthedoor.models.ResponseModel;
import com.eahackathon.watd.whosatthedoor.models.UpdatePersonNameRequest;
import com.eahackathon.watd.whosatthedoor.network.APIService;
import com.eahackathon.watd.whosatthedoor.network.WaTDAPI;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Hoang Phan on 1/9/2016.
 */
public class NotificationActivityTest {

    private NotificationActivity mActivity;
    private WaTDAPI mMockWaTDAPI;
    private ArgumentCaptor<Callback> mCallCaptor;
    private Call<ResponseModel> mMockCall;

    @Rule
    public ActivityTestRule<NotificationActivity> mActivityTestRule = new ActivityTestRule<NotificationActivity>(NotificationActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation()
                    .getTargetContext();
            Intent result = new Intent(targetContext, NotificationActivity.class);
            result.putExtra(Constants.PERSON_NAME, "My name");
            result.putExtra(Constants.PERSON_ID, "1");
            AssetManager manager = targetContext.getAssets();
            try {
                InputStream is = manager.open("test.jpg");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                result.putExtra(Constants.PERSON_DATA, buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }
    };

    @Before
    public void setUp() {
        mActivity = mActivityTestRule.getActivity();
    }

    @After
    public void tearDown() {
        APIService.setInstance(null);
    }

    @Test
    public void testNotification_show_name_and_image() throws InterruptedException {
        onView(withId(R.id.et_person)).check(matches(withText("My name")));
        onView(withId(R.id.tv_person)).check(matches(withText("My name")));
        onView(withId(R.id.et_person)).check(matches(not(isDisplayed())));
        onView(withId(R.id.tv_person)).check(matches(isDisplayed()));
        onView(withId(R.id.iv_person)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_ok)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_another)).check(matches(isDisplayed()));
    }

    @Test
    public void testNotification_click_another_btn() throws InterruptedException {
        onView(withId(R.id.btn_another)).perform(click());

        onView(withId(R.id.et_person)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_person)).check(matches(not(isDisplayed())));
        onView(withId(R.id.btn_another)).check(matches(not(isDisplayed())));
    }

    @Test
    public void testNotification_click_ok_success() throws InterruptedException {
        Response<ResponseModel> fakeSuccessResponse = Response.success(new ResponseModel());
        setUpMockCall();

        onView(withId(R.id.btn_ok)).perform(click());
        Thread.sleep(1000);

        verify(mMockCall).enqueue(mCallCaptor.capture());
        mCallCaptor.getValue().onResponse(fakeSuccessResponse, null);

        assertTrue(mActivity.isFinishing());
    }

    @Test
    public void testNotification_click_ok_failure() throws InterruptedException {
        setUpMockCall();

        onView(withId(R.id.btn_ok)).perform(click());
        Thread.sleep(1000);

        verify(mMockCall).enqueue(mCallCaptor.capture());
        mCallCaptor.getValue().onFailure(null);

        assertFalse(mActivity.isFinishing());
    }

    private void setUpMockCall() {
        mMockWaTDAPI = Mockito.mock(WaTDAPI.class);
        mCallCaptor = ArgumentCaptor.forClass(Callback.class);
        mMockCall = Mockito.mock(Call.class);

        when(mMockWaTDAPI.updatePersonName(any(String.class), any(UpdatePersonNameRequest.class))).thenReturn(mMockCall);
        APIService.setInstance(mMockWaTDAPI);
    }
}
