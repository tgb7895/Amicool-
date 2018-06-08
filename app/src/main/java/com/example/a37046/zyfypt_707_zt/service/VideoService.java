package com.example.a37046.zyfypt_707_zt.service;

import com.example.a37046.zyfypt_707_zt.bean.VideoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService {
    @GET("api.php/lists/mod/{mod}")
    Call<List<VideoBean>> getArticleList(
            @Path("mod") String mod,
            @Query("page") int page,
            @Header("SessionID") String sessionID);
}
