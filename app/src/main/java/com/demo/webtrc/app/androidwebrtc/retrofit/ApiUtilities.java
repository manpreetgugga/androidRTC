package com.demo.webtrc.app.androidwebrtc.retrofit;

public class ApiUtilities {
    private static APIService apiService;

    public ApiUtilities() {
    }

    public static final String BASE_URL = "http://172.24.10.182:8081/";

    public static APIService getApiServices() {
        if (apiService == null) {
            apiService = RetrofitClient.getRetrofit(BASE_URL).create(APIService.class);
        }
        return apiService;
    }
}
