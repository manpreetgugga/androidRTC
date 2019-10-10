package com.demo.webtrc.app.androidwebrtc.api_helper;


import com.demo.webtrc.app.androidwebrtc.dto.model.IceCandidateModel;
import com.demo.webtrc.app.androidwebrtc.dto.model.SDPModel;
import com.demo.webtrc.app.androidwebrtc.retrofit.ApiUtilities;

import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.demo.webtrc.app.androidwebrtc.androidRTC.AndroidRTCManager.irtcCallBacks;

/**
 * Created by manpreet.gugga on 6/12/18.
 */

public class PeerOneFunctions {

    public static void setPeerOneSdpOffer(SDPModel sdpOffer) {
        ApiUtilities.getApiServices().setPeerOneSdp(sdpOffer).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public static void getPeerOneSdpOffer() {
        ApiUtilities.getApiServices().getPeerOneSdp().enqueue(new Callback<SDPModel>() {
            @Override
            public void onResponse(Call<SDPModel> call, Response<SDPModel> response) {
                SessionDescription peerOneSessionDescription = new SessionDescription(SessionDescription.Type.OFFER, response.body().getDescription());
                irtcCallBacks.onPeerOneSDPOffer(peerOneSessionDescription);
            }

            @Override
            public void onFailure(Call<SDPModel> call, Throwable t) {

            }
        });
    }

    public static void setPeerOneIceCandidate(IceCandidateModel iceCandidate) {
        ApiUtilities.getApiServices().setPeerOneIceCandidate(iceCandidate).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public static void getPeerOneIceCandidates() {
        ApiUtilities.getApiServices().getPeerOneIceCandidate().enqueue(new Callback<ArrayList<IceCandidateModel>>() {
            @Override
            public void onResponse(Call<ArrayList<IceCandidateModel>> call, Response<ArrayList<IceCandidateModel>> response) {
                ArrayList<IceCandidate> peerOneIceCandidates = new ArrayList<>();
                for (IceCandidateModel iceCandidateModel : response.body()) {
                    IceCandidate iceCandidate = new IceCandidate(iceCandidateModel.getSdpMid(), iceCandidateModel.getSdpMLineIndex(), iceCandidateModel.getSdp());
                    peerOneIceCandidates.add(iceCandidate);
                }
                irtcCallBacks.onPeerOneIceCandidateReceived(peerOneIceCandidates);
            }

            @Override
            public void onFailure(Call<ArrayList<IceCandidateModel>> call, Throwable t) {

            }
        });
    }
}
