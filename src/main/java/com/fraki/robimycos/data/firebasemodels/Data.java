package com.fraki.robimycos.data.firebasemodels;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by bambo on 10.10.2017.
 */
public class Data {

    @JsonProperty("firebase_action_type")
    private String actionType;

    @JsonProperty("firebase_action_value")
    private String value;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
