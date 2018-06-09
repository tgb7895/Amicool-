package com.example.a37046.zyfypt_707_zt.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface LogoutService {
    @GET("api.php/logout")
    Call<String> logout(@Header("SessionID") String sessionID);

}
