package com.demo.webtrc.app.androidwebrtc;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.demo.webtrc.app.androidwebrtc.androidRTC.AndroidRTCManager;
import com.demo.webtrc.app.androidwebrtc.androidRTC.PeerConnectionCallInitiator;

import org.webrtc.VideoRenderer;
import org.webrtc.VideoRendererGui;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    PeerConnectionCallInitiator peerConnectionCallInitiator;
    AndroidRTCManager androidRTCManager;
    VideoRenderer.Callbacks localRender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        localRender = initAndGetRemoteViewRenderer();
        androidRTCManager = AndroidRTCManager.getInstance(this, localRender);
        peerConnectionCallInitiator = androidRTCManager.getPeerConnectionCallInitiator();
    }

    private VideoRenderer.Callbacks initAndGetRemoteViewRenderer() {
        GLSurfaceView videoView = (GLSurfaceView) findViewById(R.id.gl_surface);
        // Then we set that view, and pass a Runnable to run once the surface is ready
        VideoRendererGui.setView(videoView, null);
        return VideoRendererGui.create(0, 0, 100, 100, VideoRendererGui.ScalingType.SCALE_ASPECT_FILL, true);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.hide:
                findViewById(R.id.hideControls).setVisibility(View.GONE);
                break;
            case R.id.peerOne:
                androidRTCManager.initPeerOne();
                break;
            case R.id.fetchPeerOneSdp:
                androidRTCManager.fetchPeerOneSdp();
                break;
            case R.id.fetchPeerTwoSdp:
                androidRTCManager.fetchPeerTwoSdp();
                break;
            case R.id.peerOneFetchCandidates:
                androidRTCManager.fetchPeerOneCandidates();
                break;
            case R.id.peerTwoFetchCandidates:
                androidRTCManager.fetchPeerTwoCandidates();
                break;
        }
    }
}
