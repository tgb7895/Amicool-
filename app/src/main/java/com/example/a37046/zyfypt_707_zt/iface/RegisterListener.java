package com.example.a37046.zyfypt_707_zt.iface;

public interface RegisterListener {

    /**
     * 登陆成功返回字符串
     * @param msg
     */
    void onResponse(String msg);

    /**
     * 失败返回错误字符串
     * @param msg
     */
    void onFail(String msg);
}
