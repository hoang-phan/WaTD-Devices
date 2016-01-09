package com.eahackathon.watd.whosatthedoor.network;

import android.util.Log;

import com.eahackathon.watd.whosatthedoor.Constants;

import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by KienDu on 1/9/2016.
 */
public class APIService {
    private static WaTDAPI instance;

    public static WaTDAPI getInstance() {
        if (instance == null) {
            Retrofit restAdapter = new Retrofit.Builder().baseUrl(Constants.API_ENDPOINT).addConverterFactory(JacksonConverterFactory.create()).build();
            instance = restAdapter.create(WaTDAPI.class);
        }
        return instance;
    }

    public static void setInstance(WaTDAPI apiInstance) {
        Log.e("instance: ",String.valueOf(apiInstance));
        instance = apiInstance;
    }
}
