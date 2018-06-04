package com.example.a37046.zyfypt_707_zt.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RegisterService {
    /**
     *
     * @param username
     * @param password
     * @param tel
     * @param roleid
     * @param email
     * @return
     */

    @GET("api.php/reg")
    Call<String> reg(@Query("username") String username,
                     @Query("password") String password,
                     @Query("tel") String tel,
                     @Query("roleid") String roleid,
                     @Query("email") String email);
}
