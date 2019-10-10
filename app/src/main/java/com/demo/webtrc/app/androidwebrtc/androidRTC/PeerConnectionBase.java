package com.demo.webtrc.app.androidwebrtc.androidRTC;

import android.content.Context;

import org.webrtc.AudioSource;
import org.webrtc.AudioTrack;
import org.webrtc.IceCandidate;
import org.webrtc.MediaConstraints;
import org.webrtc.MediaStream;
import org.webrtc.PeerConnection;
import org.webrtc.PeerConnectionFactory;
import org.webrtc.SdpObserver;
import org.webrtc.SessionDescription;
import org.webrtc.VideoCapturer;
import org.webrtc.VideoCapturerAndroid;
import org.webrtc.VideoRenderer;
import org.webrtc.VideoSource;
import org.webrtc.VideoTrack;

import java.util.ArrayList;
import java.util.List;

import static com.demo.webtrc.app.androidwebrtc.androidRTC.RTCConstants.*;

/**
 * Created by manpreet.gugga on 11/12/18.
 */

public abstract class PeerConnectionBase implements PeerConnection.Observer, SdpObserver {

    public static final String AUDIO_TRACK_ID = "audio";
    public static final String VIDEO_TRACK_ID = "video";
    public static final String PEER_CONNECTION_STREAM = "myMediaStream";


    private static PeerConnectionFactory peerConnectionFactory;

    public PeerConnection peerConnection;
    public VideoRenderer.Callbacks remotePeerStreamRenderer;

    protected PeerConnectionBase(Context context, VideoRenderer.Callbacks remotePeerStreamRenderer) {
        if (peerConnectionFactory == null) {
            PeerConnectionFactory.initializeAndroidGlobals(
                    context,  // Context
                    true,  // Audio Enabled
                    true,  // Video Enabled
                    true,  // Hardware Acceleration Enabled
                    null); // Render EGL Context

            peerConnectionFactory = new PeerConnectionFactory();
        }
        createPeerConnection();
        this.remotePeerStreamRenderer = remotePeerStreamRenderer;
    }

    public void createPeerConnection() {
        peerConnection = peerConnectionFactory.createPeerConnection(getListOfIceServer(), offerConstraints(), this);

        String frontFacingCam = VideoCapturerAndroid.getNameOfFrontFacingDevice();

        // Creates a VideoCapturerAndroid instance for the device name
        VideoCapturer capturer = VideoCapturerAndroid.create(frontFacingCam);

        // First create a Video Source, then we can make a Video Track
        VideoSource localVideoSource = peerConnectionFactory.createVideoSource(capturer, defaultVideoConstraints());
        VideoTrack localVideoTrack = peerConnectionFactory.createVideoTrack(VIDEO_TRACK_ID, localVideoSource);

        // First we create an AudioSource then we can create our AudioTrack
        AudioSource audioSource = peerConnectionFactory.createAudioSource(defaultAudioConstraints());
        AudioTrack localAudioTrack = peerConnectionFactory.createAudioTrack(AUDIO_TRACK_ID, audioSource);

        // Now we can add our tracks which will be sent to remote peer.
        MediaStream mediaStream = peerConnectionFactory.createLocalMediaStream(PEER_CONNECTION_STREAM);
        mediaStream.addTrack(localVideoTrack);
        mediaStream.addTrack(localAudioTrack);

        peerConnection.addStream(mediaStream);
    }

    public MediaConstraints defaultVideoConstraints() {
        MediaConstraints videoConstraints = new MediaConstraints();
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("maxWidth", MAX_WIDTH));
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("maxHeight", MAX_HEIGHT));
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("minWidth", MIN_WIDTH));
        videoConstraints.mandatory.add(new MediaConstraints.KeyValuePair("minHeight", MIN_HEIGHT));
        return videoConstraints;
    }

    public MediaConstraints defaultAudioConstraints() {
        MediaConstraints audioConstraints = new MediaConstraints();
        return audioConstraints;
    }


    public MediaConstraints offerConstraints() {
        MediaConstraints pcConstraints = new MediaConstraints();
        pcConstraints.optional.add(new MediaConstraints.KeyValuePair(DTLS_SRTP_KEY_AGREEMENT, "true"));
        pcConstraints.mandatory.add(new MediaConstraints.KeyValuePair(OFFER_TO_RECEIVE_AUDIO, "true"));
        pcConstraints.mandatory.add(new MediaConstraints.KeyValuePair(OFFER_TO_RECEIVE_VIDEO, "true"));
        return pcConstraints;
    }


    public List<PeerConnection.IceServer> getListOfIceServer() {
        List<PeerConnection.IceServer> listOfIceServer = new ArrayList<>();
        PeerConnection.IceServer iceStunServer = new PeerConnection.IceServer(STUN_SERVER);
        PeerConnection.IceServer iceTurnServer = new PeerConnection.IceServer(TURN_SERVER, TURN_USER_NAME, TURN_CREDENTIAL);
        listOfIceServer.add(iceStunServer);
        listOfIceServer.add(iceTurnServer);
        return listOfIceServer;
    }

    public void createOffer() {
        peerConnection.createOffer(this, offerConstraints());
    }

    public void createAnswer() {
        peerConnection.createAnswer(this, offerConstraints());
    }

    public void setRemoteIceCandidates(ArrayList<IceCandidate> remoteIceCandidates) {
        for (IceCandidate remoteIceCandidate : remoteIceCandidates) {
            peerConnection.addIceCandidate(remoteIceCandidate);
        }
    }

    public void setRemoteSDPOffer(SessionDescription remoteSDPOffer) {
        peerConnection.setRemoteDescription(this, remoteSDPOffer);
    }

    public void setLocalSdpOffer(SessionDescription localSdpOffer) {
        peerConnection.setLocalDescription(this, localSdpOffer);
    }
}
