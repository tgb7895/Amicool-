package com.example.a37046.zyfypt_707_zt.iface;

import com.example.a37046.zyfypt_707_zt.bean.CollectBean;

import java.util.List;

public interface CollectListListener<T> {
    void onResponse(List<CollectBean<T>> beanlist);
    void onFail(String msg);
}
