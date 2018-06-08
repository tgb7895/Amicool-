package com.example.a37046.zyfypt_707_zt.iface;


import java.util.List;

public interface GetListener<T> {
    /**
     * //成功返回登陆信息
     * @param beanList
     */
    void onResponse(List<T> beanList);

    /**
     * 失败返回错误字符串
     * @param msg
     */
    void onFail(String msg);
}
