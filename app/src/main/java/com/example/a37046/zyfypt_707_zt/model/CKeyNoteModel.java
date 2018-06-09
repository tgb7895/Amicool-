package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.bean.CollectBean;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.iface.CollectListListener;
import com.example.a37046.zyfypt_707_zt.iface.CollectListiface;
import com.example.a37046.zyfypt_707_zt.service.CollectListService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CKeyNoteModel implements CollectListiface<KeyNoteBean> {

    private Retrofit retrofit;
    private String BASEURL
            ="http://amicool.neusoft.edu.cn/";
    //构造函数
    public CKeyNoteModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final CollectListListener<KeyNoteBean> listener) {
        //使用Retrofit----2
        CollectListService service
                =retrofit.create(CollectListService.class);
        Call<List<CollectBean<KeyNoteBean>>> call =
                service.getCTwareList(mod, page, sessionID);
        //使用Retrofit----3
        call.enqueue(new Callback<List<CollectBean<KeyNoteBean>>>() {
            @Override
            public void onResponse(Call<List<CollectBean<KeyNoteBean>>> call, Response<List<CollectBean<KeyNoteBean>>> response) {
                if(response.isSuccessful() && response!=null)
                {  listener.onResponse(response.body());
                }
                else   listener.onFail("onresponse fail");
            }
            @Override
            public void onFailure(Call<List<CollectBean<KeyNoteBean>>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });
    }
}


