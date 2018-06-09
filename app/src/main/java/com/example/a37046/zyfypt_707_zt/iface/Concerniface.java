package com.example.a37046.zyfypt_707_zt.iface;

public interface Concerniface {
    void concern(String mod,int id,String sessionid,ConcernListener listener);
    void unconcern(String mod,int id,String sessionid,ConcernListener listener);
    void exist(String mod,int id,String sessionid,ConcernListener listener);
}
