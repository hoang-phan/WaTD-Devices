package com.eahackathon.watd.whosatthedoor.services;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.eahackathon.watd.whosatthedoor.Constants;
import com.eahackathon.watd.whosatthedoor.NotificationActivity;
import com.eahackathon.watd.whosatthedoor.network.APIService;
import com.google.android.gms.gcm.GcmListenerService;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;
import retrofit.Response;

/**
 * Created by Hoang Phan on 11/17/2015.
 */
public class GCMListenerService extends GcmListenerService {
    @Override
    public void onMessageReceived(String from, final Bundle data) {
        super.onMessageReceived(from, data);
        final String id = data.getString(Constants.PERSON_ID);
        Call<ResponseBody> call = APIService.getInstance().getImage(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                Intent intent = new Intent(GCMListenerService.this, NotificationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(Constants.PERSON_NAME, data.getString(Constants.PERSON_NAME));
                intent.putExtra(Constants.PERSON_ID, id);
                try {
                    intent.putExtra(Constants.PERSON_DATA, response.body().bytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
        Log.d("Message Received From", from);
        Log.d("data", data.toString());
    }
}
