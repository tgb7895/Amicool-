package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.bean.FocusResult;
import com.example.a37046.zyfypt_707_zt.bean.UserBean;
import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.fragments.videofragment.PagerSlidingTabStrip;
import com.example.a37046.zyfypt_707_zt.iface.ListMyFocusListener;
import com.example.a37046.zyfypt_707_zt.iface.ListMyFocusiface;
import com.example.a37046.zyfypt_707_zt.service.ListMyFocusService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyFocusListModel implements ListMyFocusiface{


    private Retrofit retrofit;

    public MyFocusListModel() {
        retrofit=new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void getMyFocusList(String mod, int page, String sessionid, final ListMyFocusListener listener) {
        ListMyFocusService listMyFocusService = retrofit.create(ListMyFocusService.class);
        Call<List<FocusResult<UserBean>>> call = listMyFocusService.getFocusUserList(mod, page, sessionid);

        call.enqueue(new Callback<List<FocusResult<UserBean>>>() {
            @Override
            public void onResponse(Call<List<FocusResult<UserBean>>> call, Response<List<FocusResult<UserBean>>> response) {
                if(response.isSuccessful() && response!=null)
                {
                    listener.onResponse(response.body());
                }
                else   {
                    listener.onFail("on response fail");
                }
            }

            @Override
            public void onFailure(Call<List<FocusResult<UserBean>>> call, Throwable t) {
                listener.onFail(t.toString());
            }
        });
    }
}
