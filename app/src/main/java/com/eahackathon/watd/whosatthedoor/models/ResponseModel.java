package com.eahackathon.watd.whosatthedoor.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by KienDu on 1/9/2016.
 */
public class ResponseModel {
    @JsonProperty("success")
    private String success;
    public String getSuccess() {
        return success;
    }
}
