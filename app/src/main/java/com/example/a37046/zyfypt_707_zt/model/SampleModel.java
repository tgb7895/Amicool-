package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.bean.SampleBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.GetListener;
import com.example.a37046.zyfypt_707_zt.iface.Getiface;
import com.example.a37046.zyfypt_707_zt.service.SampleService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SampleModel implements Getiface<GetListener<SampleBean>> {
    private Retrofit retrofit;


    public SampleModel() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final GetListener<SampleBean> listener) {
        SampleService sampleService = retrofit.create(SampleService.class);

        Call<List<SampleBean>> call = sampleService.getArticleList(mod, page, sessionID);

        call.enqueue(new Callback<List<SampleBean>>() {
            @Override
            public void onResponse(Call<List<SampleBean>> call, Response<List<SampleBean>> response) {
                if(response.isSuccessful() && response!=null)
                {  listener.onResponse(response.body());
                }
                else   {
                    listener.onFail("on response fail");
                }
            }

            @Override
            public void onFailure(Call<List<SampleBean>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });
    }

}
