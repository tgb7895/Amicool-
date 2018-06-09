package com.example.a37046.zyfypt_707_zt.service;

import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.bean.CollectBean;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.bean.VideoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CollectListService {
    //收藏文章列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<ArticleBean>>> getCArticleList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏案例列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<SampleBean>>> getCTcaseList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏课件列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<KeyNoteBean>>> getCTwareList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏项目列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<SampleBean>>> getCPojectList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);
    //收藏视频列表
    @GET("api.php/listmycollect/mod/collect{mod}")
    Call<List<CollectBean<VideoBean>>> getCVideoList(
            @Path("mod") String mode,
            @Query("page") int page,
            @Header("SessionID") String SessionID);

}
