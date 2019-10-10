package com.demo.webtrc.app.androidwebrtc.api_helper;

import com.demo.webtrc.app.androidwebrtc.dto.model.IceCandidateModel;
import com.demo.webtrc.app.androidwebrtc.dto.model.SDPModel;
import com.demo.webtrc.app.androidwebrtc.retrofit.ApiUtilities;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by manpreet.gugga on 6/12/18.
 */

public class PeerConnectionHelper {

    public static void sendIceCandidate(String peer, IceCandidateModel iceCandidate) {
        switch (peer) {
            case "peerOne":
                PeerOneFunctions.setPeerOneIceCandidate(iceCandidate);
                break;
            case "peerTwo":
                PeerTwoFunctions.setPeerTwoIceCandidate(iceCandidate);
                break;
        }
    }

    public static void gendIceCandidate(String peer) {
        switch (peer) {
            case "peerOne":
                PeerOneFunctions.getPeerOneIceCandidates();
                break;
            case "peerTwo":
                PeerTwoFunctions.getPeerTwoIceCandidates();
                break;
        }
    }

    public static void sendSdp(String peer, SDPModel sdpOffer) {
        switch (peer) {
            case "peerOne":
                PeerOneFunctions.setPeerOneSdpOffer(sdpOffer);
                break;
            case "peerTwo":
                PeerTwoFunctions.setPeerTwoSdpOffer(sdpOffer);
                break;
        }
    }

    public static void getPeerSdp(String peer) {
        switch (peer) {
            case "peerOne":
                PeerOneFunctions.getPeerOneSdpOffer();
                break;
            case "peerTwo":
                PeerTwoFunctions.getPeerTwoSdpOffer();
                break;
        }
    }


    public static void resetPeersData(){
        ApiUtilities.getApiServices().resetData().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}
