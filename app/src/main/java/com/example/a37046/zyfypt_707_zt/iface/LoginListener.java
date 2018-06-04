package com.example.a37046.zyfypt_707_zt.iface;

import com.example.a37046.zyfypt_707_zt.bean.LoginBean;

public interface LoginListener {

    /**
     * //成功返回登陆信息
     * @param loginBean
     */
    void onResponse(LoginBean loginBean);

    /**
     * 失败返回错误字符串
     * @param msg
     */
    void onFail(String msg);
}
