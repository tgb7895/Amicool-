package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.bean.CollectBean;
import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.CollectListListener;
import com.example.a37046.zyfypt_707_zt.iface.CollectListiface;
import com.example.a37046.zyfypt_707_zt.iface.ConcerListListener;
import com.example.a37046.zyfypt_707_zt.iface.ConcernListiface;
import com.example.a37046.zyfypt_707_zt.service.CollectListService;
import com.example.a37046.zyfypt_707_zt.service.ConcernListsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FKeyNoteModel implements ConcernListiface<KeyNoteBean> {

    private Retrofit retrofit;
    //构造函数
    public FKeyNoteModel()
    {   //使用Retrofit----1
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, int userid, final ConcerListListener<KeyNoteBean> listener) {
        ConcernListsService concernListsService = retrofit.create(ConcernListsService.class);
        Call<List<KeyNoteBean>> call = concernListsService.getKeyNoteList(mod, page, sessionID, userid);
        call.enqueue(new Callback<List<KeyNoteBean>>() {
            @Override
            public void onResponse(Call<List<KeyNoteBean>> call, Response<List<KeyNoteBean>> response) {
                if(response.isSuccessful()) {
                    listener.onResponse(response.body());
                }else{
                    listener.onFail("第一错误");
                }
            }

            @Override
            public void onFailure(Call<List<KeyNoteBean>> call, Throwable t) {
                listener.onFail("第二错误");
            }
        });
    }
}


