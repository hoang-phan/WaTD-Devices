package com.eahackathon.watd.whosatthedoor.helpers;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;

import com.eahackathon.watd.whosatthedoor.models.CreateGcmKeyRequest;
import com.eahackathon.watd.whosatthedoor.models.ResponseModel;
import com.eahackathon.watd.whosatthedoor.network.APIService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by KienDu on 1/9/2016.
 */
public class GcmRegistrationHelper {
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static GcmRegistrationHelper instance;

    private Activity mActivity;

    public static GcmRegistrationHelper getInstance() {
        if (instance == null) {
            instance = new GcmRegistrationHelper();
        }
        return instance;
    }

    public static void setInstance(GcmRegistrationHelper instance) {
        GcmRegistrationHelper.instance = instance;
    }

    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void register() throws IOException {
        if (checkPlayService()) {
            String registerId = GoogleCloudMessaging.getInstance(mActivity).register("120607825117");
            Call<ResponseModel> call = APIService.getInstance().createGcmKey(new CreateGcmKeyRequest(getDeviceId(), registerId));
            call.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Response<ResponseModel> response, Retrofit retrofit) {
                    Log.e("register GCM", "success");
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("register GCM", "failed");

                }
            });
        }
    }

    private boolean checkPlayService() {
        final int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(mActivity);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        GooglePlayServicesUtil.getErrorDialog(resultCode, mActivity, PLAY_SERVICES_RESOLUTION_REQUEST).show();
                    }
                });
            } else {
                Log.i("aaa", "This device is not supported.");
            }
            return false;
        }
        return true;
    }

    private String getDeviceId() {
        return Settings.Secure.getString(mActivity.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
