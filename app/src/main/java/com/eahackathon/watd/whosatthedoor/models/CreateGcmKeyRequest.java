package com.eahackathon.watd.whosatthedoor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by KienDu on 1/9/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateGcmKeyRequest {
    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("reg_id")
    private String regId;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public CreateGcmKeyRequest(String deviceId, String regId) {
        this.deviceId = deviceId;
        this.regId = regId;
    }
}
