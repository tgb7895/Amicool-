package com.example.a37046.zyfypt_707_zt.bean;

public class FocusResult<T> {

    /**
     * id : 7
     * idolid : 7
     * fwtime : 2016-12-26 22:11:32
     * listorder : null
     * userid : 207
     * vstate : 1
     * bean : {"id":"7","username":"zhengjunsheng","realname":"郑俊生","rolename":"教师","sex":"男"}
     */

    private String id;
    private String idolid;
    private String fwtime;
    private Object listorder;
    private String userid;
    private String vstate;
    private T bean;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdolid() {
        return idolid;
    }

    public void setIdolid(String idolid) {
        this.idolid = idolid;
    }

    public String getFwtime() {
        return fwtime;
    }

    public void setFwtime(String fwtime) {
        this.fwtime = fwtime;
    }

    public Object getListorder() {
        return listorder;
    }

    public void setListorder(Object listorder) {
        this.listorder = listorder;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getVstate() {
        return vstate;
    }

    public void setVstate(String vstate) {
        this.vstate = vstate;
    }

    public T getBean() {
        return bean;
    }

    public void setBean(T bean) {
        this.bean = bean;
    }
}
