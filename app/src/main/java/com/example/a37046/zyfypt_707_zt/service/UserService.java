package com.example.a37046.zyfypt_707_zt.service;

import com.example.a37046.zyfypt_707_zt.bean.LoginBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserService {
    @GET("api.php/login/username/{username}/password/{password}")
    Call<LoginBean> login(
            @Path("username") String username,
            @Path("password") String password
    );
}
