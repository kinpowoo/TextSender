package com.szsszwl.textsender;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //第一：默认初始化
        Bmob.initialize(this, "186daf0d50dacb1cc66d00e24ab99729");
    }


}
