package com.demo.webtrc.app.androidwebrtc.dto.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by manpreet.gugga on 6/12/18.
 */

public class IceCandidateModel {

    public IceCandidateModel(String sdp, String sdpMid, int sdpMLineIndex) {
        this.sdp = sdp;
        this.sdpMid = sdpMid;
        this.sdpMLineIndex = sdpMLineIndex;
    }

    public String getSdp() {
        return sdp;
    }

    public void setSdp(String sdp) {
        this.sdp = sdp;
    }

    public String getSdpMid() {
        return sdpMid;
    }

    public void setSdpMid(String sdpMid) {
        this.sdpMid = sdpMid;
    }

    public int getSdpMLineIndex() {
        return sdpMLineIndex;
    }

    public void setSdpMLineIndex(int sdpMLineIndex) {
        this.sdpMLineIndex = sdpMLineIndex;
    }

    @SerializedName("sdp")

    String sdp;

    @SerializedName("sdpMid")
    String sdpMid;

    @SerializedName("sdpMLineIndex")
    int sdpMLineIndex;

}
