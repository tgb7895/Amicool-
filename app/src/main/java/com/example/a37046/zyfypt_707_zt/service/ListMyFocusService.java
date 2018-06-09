package com.example.a37046.zyfypt_707_zt.service;

import com.example.a37046.zyfypt_707_zt.bean.FocusResult;
import com.example.a37046.zyfypt_707_zt.bean.UserBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ListMyFocusService {

    @GET("api.php/listmyfocus/mod/{mod}focus")
    Call<List<FocusResult<UserBean>>> getFocusUserList(@Path("mod") String mod,
                                                       @Query("page") int page,
                                                       @Header("SessionID") String sessionID);

}
