package com.example.a37046.zyfypt_707_zt.service;

import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.bean.VideoBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ConcernListsService {


    /**
     * 文章
     */
    @GET("api.php/lists/mod/{mod}")
    Call<List<ArticleBean>> getArticleList(@Path("mod")String mod,
                                           @Query("page") int page,
                                           @Header("SessionID") String sessionID,
                                           @Query("userid") int userid);

    /**
     * 视频
     */
    @GET("api.php/lists/mod/{mod}")
    Call<List<VideoBean>> getVideoList(@Path("mod")String mod,
                                         @Query("page") int page,
                                         @Header("SessionID") String sessionID,
                                         @Query("userid") int userid);

    /**
     * 课件
     */
    @GET("api.php/lists/mod/{mod}")
    Call<List<KeyNoteBean>> getKeyNoteList(@Path("mod")String mod,
                                         @Query("page") int page,
                                         @Header("SessionID") String sessionID,
                                         @Query("userid") int userid);

    /**
     * 案例
     */
    @GET("api.php/lists/mod/{mod}")
    Call<List<SampleBean>> getSamoleList(@Path("mod")String mod,
                                          @Query("page") int page,
                                          @Header("SessionID") String sessionID,
                                          @Query("userid") int userid);

    /**
     * 项目
     */
    @GET("api.php/lists/mod/{mod}")
    Call<List<SampleBean>> getItemList(@Path("mod")String mod,
                                           @Query("page") int page,
                                           @Header("SessionID") String sessionID,
                                           @Query("userid") int userid);
}
