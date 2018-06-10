package com.example.a37046.zyfypt_707_zt.iface;

import java.util.List;

public interface ConcerListListener<T> {
    void onResponse(List<T> beanlist);
    void onFail(String msg);
}
