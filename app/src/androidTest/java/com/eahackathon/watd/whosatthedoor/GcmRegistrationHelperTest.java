package com.eahackathon.watd.whosatthedoor;

import android.support.test.rule.ActivityTestRule;

import com.eahackathon.watd.whosatthedoor.helpers.GcmRegistrationHelper;
import com.eahackathon.watd.whosatthedoor.models.CreateGcmKeyRequest;
import com.eahackathon.watd.whosatthedoor.models.ResponseModel;
import com.eahackathon.watd.whosatthedoor.network.APIService;
import com.eahackathon.watd.whosatthedoor.network.WaTDAPI;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by KienDu on 1/10/2016.
 */
public class GcmRegistrationHelperTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void testGcmRegistrationHelper_test() throws IOException, InterruptedException {
        GcmRegistrationHelper.getInstance().setActivity(mActivityTestRule.getActivity());
        WaTDAPI mockWaTDAPI = mock(WaTDAPI.class);
        APIService.setInstance(mockWaTDAPI);
        Call<ResponseModel> mockCall = mock(Call.class);
        ArgumentCaptor<Callback> callbackCaptor = ArgumentCaptor.forClass(Callback.class);
        when(mockWaTDAPI.createGcmKey(any(CreateGcmKeyRequest.class))).thenReturn(mockCall);
        GcmRegistrationHelper.getInstance().register();
        Thread.sleep(3000);
        verify(mockCall).enqueue(callbackCaptor.capture());
        callbackCaptor.getValue().onFailure(null);
        callbackCaptor.getValue().onResponse(null, null);
    }
}

