package com.example.a37046.zyfypt_707_zt.iface;

public interface ConcernListiface<T> {
    void getResultList(String mod,//模块
                       int page,//页数
                       String sessionID,
                       int userid,
                       ConcerListListener<T> listener
    );
}
