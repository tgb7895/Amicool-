package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.bean.VideoBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.GetListener;
import com.example.a37046.zyfypt_707_zt.iface.Getiface;
import com.example.a37046.zyfypt_707_zt.service.VideoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetModel implements Getiface<GetListener<VideoBean>>{
    private Retrofit retrofit;


    public GetModel() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final GetListener<VideoBean> listener) {
        VideoService videoService = retrofit.create(VideoService.class);
        Call<List<VideoBean>> call = videoService.getArticleList(mod, page, sessionID);

        call.enqueue(new Callback<List<VideoBean>>() {
            @Override
            public void onResponse(Call<List<VideoBean>> call, Response<List<VideoBean>> response) {
                if(response.isSuccessful() && response!=null)
                {  listener.onResponse(response.body());
                }
                else   {
                    listener.onFail("on response fail");
                }
            }

            @Override
            public void onFailure(Call<List<VideoBean>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });


    }
}
