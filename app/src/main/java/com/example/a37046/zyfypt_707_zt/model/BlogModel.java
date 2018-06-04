package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.BlogListener;
import com.example.a37046.zyfypt_707_zt.iface.Blogiface;
import com.example.a37046.zyfypt_707_zt.service.BlogService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BlogModel implements Blogiface{
    private Retrofit retrofit;

    //构造函数
    public BlogModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final BlogListener listener) {
        //使用Retrofit----2
        BlogService service
                =retrofit.create(BlogService.class);
        Call<List<ArticleBean>> call
                =service.getArticleList(mod,page,sessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<ArticleBean>>() {
            @Override
            public void onResponse(Call<List<ArticleBean>> call, Response<List<ArticleBean>> response) {
                if(response.isSuccessful() && response!=null)
                {  listener.onResponse(response.body());
                }
                else   {
                    listener.onFail("on response fail");
                }
            }
            @Override
            public void onFailure(Call<List<ArticleBean>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });
    }
}
