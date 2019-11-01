package com.szsszwl.textsender.bean;

import cn.bmob.v3.BmobObject;

public class Chat extends BmobObject {

    public Chat() {
    }

    public Chat(String msg) {
        this.msg = msg;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}