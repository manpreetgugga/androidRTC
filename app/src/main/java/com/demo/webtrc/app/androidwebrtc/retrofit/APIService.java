package com.demo.webtrc.app.androidwebrtc.retrofit;

import com.demo.webtrc.app.androidwebrtc.dto.model.IceCandidateModel;
import com.demo.webtrc.app.androidwebrtc.dto.model.SDPModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    // Getters

    @POST("setPeerOneSdp") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Void> setPeerOneSdp(@Body SDPModel peerOneSdp);

    @POST("setPeerTwoSdp") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Void> setPeerTwoSdp(@Body SDPModel peerTwoSdp);

    @POST("setPeerOneIceCandidate") //i.e https://api.test.com/Search?
    @Headers({"Content-Type: application/json;charset=UTF-8"})
    Call<Void> setPeerOneIceCandidate(@Body IceCandidateModel peerOneIceCandidate);

    @POST("setPeerTwoIceCandidate") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Void> setPeerTwoIceCandidate(@Body IceCandidateModel peerTwoIceCandidate);


    // Setters

    @GET("getPeerOneSdp") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<SDPModel> getPeerOneSdp();

    @GET("reset") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<Void> resetData();

    @GET("getPeerTwoSdp") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<SDPModel> getPeerTwoSdp();

    @GET("getPeerOneIceCandidate") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ArrayList<IceCandidateModel>> getPeerOneIceCandidate();

    @GET("getPeerTwoIceCandidate") //i.e https://api.test.com/Search?
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Call<ArrayList<IceCandidateModel>> getPeerTwoIceCandidate();

}
