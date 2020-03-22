package com.bawei.week1moni.bean;

import java.io.Serializable;
import java.util.List;

/**
 * BaseResultBean
 */
public class BaseResultBean<T> implements Serializable {
    private int code;
    private String msg;
    private List<T> result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}
