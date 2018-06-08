package com.example.a37046.zyfypt_707_zt.iface;

public interface Getiface<T> {
    void getResultList(String mod,
                       int page,
                       String sessionID,
                       T listener
    );
}
