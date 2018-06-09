package com.example.a37046.zyfypt_707_zt.model;

import com.example.a37046.zyfypt_707_zt.common.Common;
import com.example.a37046.zyfypt_707_zt.iface.ConcernListener;
import com.example.a37046.zyfypt_707_zt.iface.Concerniface;
import com.example.a37046.zyfypt_707_zt.service.ConcernService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ConcernModel implements Concerniface{
    private Retrofit retrofit;

    public ConcernModel() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Common.BASEURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    @Override
    public void concern(String mod, int id, String sessionid, final ConcernListener listener) {
        //使用Retrofit----2
        ConcernService concernService = retrofit.create(ConcernService.class);
        Call<String> call = concernService.focus(mod,id,sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response!= null) {
                    if (response.body().trim().toString().equals("0"))
                        listener.onResponse("1");//关注失败
                    else if (!response.body().trim().toString().contains("error")) {
                        listener.onResponse("2");//关注成功

                    } else {
                        listener.onResponse("关注：" + response.body());
                    }

                } else {
                    listener.onFail("collect onresponse1 fail");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("collect onresponse0 fail");
            }
        });
    }

    @Override
    public void unconcern(String mod, int id, String sessionid, final ConcernListener listener) {
        //使用Retrofit----2
        ConcernService concernService = retrofit.create(ConcernService.class);
        Call<String> call = concernService.unfocus(mod, id, sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response != null) {
                    if (response.body().trim().toString().equals("0"))
                        listener.onResponse("3");//取消关注失败
                    else if (response.body().trim().toString().equals("1"))
                        listener.onResponse("4");//取消关注成功
                    else {
                        listener.onResponse("取消关注：" + response.body());
                    }

                } else {
                    listener.onFail("unconcern onresponse fail");//uncollect onresponse fail
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("取消关注失败：" + t.toString());
            }
        });
    }

    @Override
    public void exist(String mod, int id, String sessionid, final ConcernListener listener) {
        //使用Retrofit----2
        ConcernService concernService = retrofit.create(ConcernService.class);
        Call<String> call = concernService.exists(mod, id, sessionid);
        //使用Retrofit----3
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response != null) {
                    if (response.body().trim().toString().equals("0")) {//已关注
                        listener.onResponse("5");
                    } else if (response.body().trim().toString().equals("1")) {//未关注
                        listener.onResponse("6");
                    } else {
                        listener.onResponse("判断关注：" + response.body());
                    }

                } else {
                    listener.onFail("unconcern exist onresponse fail");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                listener.onFail("判断关注失败：" + t.toString());
            }
        });
    }
}
