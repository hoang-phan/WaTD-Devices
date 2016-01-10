package com.eahackathon.watd.whosatthedoor.network;

import com.eahackathon.watd.whosatthedoor.models.CreateGcmKeyRequest;
import com.eahackathon.watd.whosatthedoor.models.ResponseModel;
import com.eahackathon.watd.whosatthedoor.models.UpdatePersonNameRequest;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by KienDu on 1/9/2016.
 */
public interface WaTDAPI {
    @POST("/gcms")
    Call<ResponseModel> createGcmKey(@Body CreateGcmKeyRequest request);

    @PUT("/images/{id}")
    Call<ResponseModel> updatePersonName(@Path("id") String id, @Body UpdatePersonNameRequest request);

    @GET("/images/{id}")
    Call<ResponseBody> getImage(@Path("id") String id);
}
