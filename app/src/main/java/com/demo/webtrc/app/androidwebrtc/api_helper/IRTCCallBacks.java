package com.demo.webtrc.app.androidwebrtc.api_helper;

import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;

import java.util.ArrayList;

/**
 * Created by manpreet.gugga on 11/12/18.
 */

public interface IRTCCallBacks {
    void onPeerOneSDPOffer(SessionDescription sessionDescription);

    void onPeerTwoSDPOffer(SessionDescription sessionDescription);

    void onPeerOneIceCandidateReceived(ArrayList<IceCandidate> iceCandidates);

    void onPeerTwoIceCandidateReceived(ArrayList<IceCandidate> iceCandidates);
}
