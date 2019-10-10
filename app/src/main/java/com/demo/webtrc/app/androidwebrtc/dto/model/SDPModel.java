package com.demo.webtrc.app.androidwebrtc.dto.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by manpreet.gugga on 6/12/18.
 */

public class SDPModel {

    public SDPModel(String type, String description) {
        this.type = type;
        this.description = description;
    }

    @SerializedName("type")
    String type;

    @SerializedName("description")
    String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }
}
