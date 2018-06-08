package com.example.a37046.zyfypt_707_zt.iface;

public interface Collectiface {
    void collect(String mod,int id,String sessionid,CollectListener listener);
    void uncollect(String mod,int id,String sessionid,CollectListener listener);
    void exist(String mod,int id,String sessionid,CollectListener listener);

}
