package com.demo.webtrc.app.androidwebrtc.androidRTC;

import android.content.Context;

import com.demo.webtrc.app.androidwebrtc.api_helper.IRTCCallBacks;
import com.demo.webtrc.app.androidwebrtc.api_helper.PeerConnectionHelper;

import org.webrtc.IceCandidate;
import org.webrtc.SessionDescription;
import org.webrtc.VideoRenderer;

import java.util.ArrayList;

/**
 * Created by manpreet.gugga on 12/12/18.
 */

public class AndroidRTCManager implements IRTCCallBacks {

    private Context context;

    private PeerConnectionCallInitiator peerConnectionCallInitiator;
    private PeerConnectionCallReceiver peerConnectionCallReceiver;

    public static final String PEER_ONE = "peerOne";
    public static final String PEER_TWO = "peerTwo";

    public VideoRenderer.Callbacks remoteVideoRenderer;

    private static AndroidRTCManager androidRTCManager;

    public static IRTCCallBacks irtcCallBacks;

    private AndroidRTCManager(Context context, VideoRenderer.Callbacks remoteVideoRenderer) {
        this.context = context;
        this.remoteVideoRenderer = remoteVideoRenderer;
        this.irtcCallBacks = this;
    }

    public static AndroidRTCManager getInstance(Context context, VideoRenderer.Callbacks remoteVideoRenderer) {
        if (androidRTCManager == null) {
            androidRTCManager = new AndroidRTCManager(context, remoteVideoRenderer);
        }
        return androidRTCManager;
    }

    public PeerConnectionCallInitiator getPeerConnectionCallInitiator() {
        peerConnectionCallInitiator = new PeerConnectionCallInitiator(context, remoteVideoRenderer);
        return peerConnectionCallInitiator;
    }

    public PeerConnectionCallReceiver getPeerConnectionCallReceiver() {
        peerConnectionCallReceiver = new PeerConnectionCallReceiver(context, remoteVideoRenderer);
        return peerConnectionCallReceiver;
    }

    public void disconnectCall() {
        PeerConnectionHelper.resetPeersData();
        if (peerConnectionCallReceiver != null) {
            peerConnectionCallReceiver.peerConnection.close();
        }
        if (peerConnectionCallInitiator != null) {
            peerConnectionCallInitiator.peerConnection.close();
        }
    }

    @Override
    public void onPeerOneSDPOffer(SessionDescription sessionDescription) {
        peerConnectionCallReceiver = getPeerConnectionCallReceiver();
        peerConnectionCallReceiver.setRemoteSDPOffer(sessionDescription);
        peerConnectionCallReceiver.createAnswer();
    }

    @Override
    public void onPeerTwoSDPOffer(SessionDescription sessionDescription) {
        peerConnectionCallInitiator.remoteSDPSet(true);
        peerConnectionCallInitiator.setRemoteSDPOffer(sessionDescription);
    }

    @Override
    public void onPeerOneIceCandidateReceived(ArrayList<IceCandidate> iceCandidates) {
        peerConnectionCallReceiver.setRemoteIceCandidates(iceCandidates);
        peerConnectionCallReceiver.sendPeerTwoIceCandidates();
    }

    @Override
    public void onPeerTwoIceCandidateReceived(ArrayList<IceCandidate> iceCandidates) {
        peerConnectionCallInitiator.setRemoteIceCandidates(iceCandidates);
    }


    public void fetchPeerTwoSdp() {
        PeerConnectionHelper.getPeerSdp(PEER_TWO);
    }

    public void fetchPeerOneSdp() {
        PeerConnectionHelper.getPeerSdp(PEER_ONE);
    }

    public void initPeerOne() {
        peerConnectionCallInitiator.createOffer();
    }

    public void fetchPeerOneCandidates() {
        PeerConnectionHelper.gendIceCandidate(PEER_ONE);
    }

    public void fetchPeerTwoCandidates() {
        PeerConnectionHelper.gendIceCandidate(PEER_TWO);
    }

}
