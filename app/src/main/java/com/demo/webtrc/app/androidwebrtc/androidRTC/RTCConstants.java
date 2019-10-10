package com.demo.webtrc.app.androidwebrtc.androidRTC;

/**
 * Created by manpreet.gugga on 11/12/18.
 */

public class RTCConstants {

    public static final String STUN_SERVER = "stun:34.200.111.109:3478";

    public static final String TURN_SERVER = "turn:34.200.111.109:3478";
    public static final String TURN_USER_NAME = "ubuntu";
    public static final String TURN_CREDENTIAL = "CVR456vb9jk";

    /**
     * Offer Media Constraints
     */
    public static final String DTLS_SRTP_KEY_AGREEMENT = "DtlsSrtpKeyAgreement";
    public static final String OFFER_TO_RECEIVE_AUDIO = "OfferToReceiveAudio";
    public static final String OFFER_TO_RECEIVE_VIDEO = "OfferToReceiveVideo";

    /**
     * device video media constraints
     */

    public static final String MAX_WIDTH = "1280";
    public static final String MAX_HEIGHT = "720";
    public static final String MIN_WIDTH = "640";
    public static final String MIN_HEIGHT = "480";

}
