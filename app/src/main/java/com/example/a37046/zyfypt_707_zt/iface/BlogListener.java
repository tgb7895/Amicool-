package com.example.a37046.zyfypt_707_zt.iface;

import com.example.a37046.zyfypt_707_zt.bean.ArticleBean;

import java.util.List;

public interface BlogListener {
    //成功返回登录信息
    void onResponse(List<ArticleBean> beanlist);
    //失败返回错误字符串
    void onFail(String msg);
}
