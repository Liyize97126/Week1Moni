package com.bawei.week1moni.bean;

import java.io.Serializable;

/**
 * ResultBean
 */
public class ResultBean implements Serializable {
    private String desc;
    private String url;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
