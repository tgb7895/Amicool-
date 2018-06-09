package com.example.a37046.zyfypt_707_zt.iface;

import com.example.a37046.zyfypt_707_zt.bean.FocusResult;
import com.example.a37046.zyfypt_707_zt.bean.UserBean;

import java.util.List;

public interface ListMyFocusListener {
    void onResponse(List<FocusResult<UserBean>> beanlist);
    void onFail(String msg);
}
