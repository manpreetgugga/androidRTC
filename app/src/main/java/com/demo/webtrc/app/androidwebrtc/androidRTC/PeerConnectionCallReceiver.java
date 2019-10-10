package com.demo.webtrc.app.androidwebrtc.androidRTC;

import android.content.Context;

import com.demo.webtrc.app.androidwebrtc.api_helper.PeerConnectionHelper;
import com.demo.webtrc.app.androidwebrtc.dto.model.IceCandidateModel;
import com.demo.webtrc.app.androidwebrtc.dto.model.SDPModel;

import org.webrtc.DataChannel;
import org.webrtc.IceCandidate;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.SessionDescription;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoRendererGui;

import java.util.ArrayList;

/**
 * Created by manpreet.gugga on 11/12/18.
 */

public class PeerConnectionCallReceiver extends PeerConnectionBase {

    private ArrayList<IceCandidateModel> iceCandidateModels = new ArrayList<>();

    public PeerConnectionCallReceiver(Context context, VideoRenderer.Callbacks remotePeerStreamRenderer) {
        super(context, remotePeerStreamRenderer);
    }

    public void answerCall(SessionDescription remoteSDPOffer) {
        setRemoteSDPOffer(remoteSDPOffer);
    }

    @Override
    public void onSignalingChange(PeerConnection.SignalingState signalingState) {

    }

    @Override
    public void onIceConnectionChange(PeerConnection.IceConnectionState iceConnectionState) {

    }

    @Override
    public void onIceConnectionReceivingChange(boolean b) {

    }

    @Override
    public void onIceGatheringChange(PeerConnection.IceGatheringState iceGatheringState) {

    }

    @Override
    public void onIceCandidate(IceCandidate iceCandidate) {
        String iceCandidateString = "{ sdp : " + iceCandidate.sdp + ", sdpMid : " + iceCandidate.sdpMid + ",sdp :" + iceCandidate.sdp + "}";
        IceCandidateModel iceCandidateModel = new IceCandidateModel(iceCandidate.sdp, iceCandidate.sdpMid, iceCandidate.sdpMLineIndex);
        iceCandidateModels.add(iceCandidateModel);
    }

    @Override
    public void onAddStream(MediaStream stream) {
        if (stream.audioTracks.size() > 1 || stream.videoTracks.size() > 1) {
            return;
        }

        if (stream.videoTracks.size() == 1) {
            stream.videoTracks.get(0).addRenderer(new VideoRenderer(remotePeerStreamRenderer));
            VideoRendererGui.update(remotePeerStreamRenderer, 0, 0, 400, 400, VideoRendererGui.ScalingType.SCALE_ASPECT_FIT, true);
        }
    }

    @Override
    public void onRemoveStream(MediaStream mediaStream) {

    }

    @Override
    public void onDataChannel(DataChannel dataChannel) {

    }

    @Override
    public void onRenegotiationNeeded() {

    }

    @Override
    public void onCreateSuccess(SessionDescription sessionDescription) {
        peerConnection.setLocalDescription(this, sessionDescription);
        SDPModel sdpModel = new SDPModel(sessionDescription.type.canonicalForm(), sessionDescription.description);
        PeerConnectionHelper.sendSdp("peerTwo", sdpModel);
    }

    @Override
    public void onSetSuccess() {

    }

    @Override
    public void onCreateFailure(String s) {

    }

    @Override
    public void onSetFailure(String s) {

    }

    public void sendPeerTwoIceCandidates(){
       for(IceCandidateModel iceCandidateModel : iceCandidateModels){
           PeerConnectionHelper.sendIceCandidate("peerTwo", iceCandidateModel);
       }
    }
}
