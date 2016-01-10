package com.eahackathon.watd.whosatthedoor.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by KienDu on 1/9/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdatePersonNameRequest {
    @JsonProperty("name")
    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public UpdatePersonNameRequest(String personName) {
        this.personName = personName;
    }
}
