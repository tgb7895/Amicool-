package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.bean.KeyNoteBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.GetListener;
import com.example.a37046.zyfypt_707_zt.iface.Getiface;
import com.example.a37046.zyfypt_707_zt.service.KeyNoteService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class KeyNoteModel implements Getiface<GetListener<KeyNoteBean>>{
    private Retrofit retrofit;


    public KeyNoteModel() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getResultList(String mod, int page, String sessionID, final GetListener<KeyNoteBean> listener) {
        KeyNoteService keyNoteService = retrofit.create(KeyNoteService.class);
        Call<List<KeyNoteBean>> call = keyNoteService.getArticleList(mod, page, sessionID);

        call.enqueue(new Callback<List<KeyNoteBean>>() {
            @Override
            public void onResponse(Call<List<KeyNoteBean>> call, Response<List<KeyNoteBean>> response) {
                if(response.isSuccessful() && response!=null)
                {  listener.onResponse(response.body());
                }
                else   {
                    listener.onFail("on response fail");
                }
            }

            @Override
            public void onFailure(Call<List<KeyNoteBean>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });


    }
}
