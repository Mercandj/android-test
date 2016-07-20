package com.mercandalli.android.apps.test;

import android.support.multidex.MultiDexApplication;

import com.mercandalli.android.library.base.main.BaseManager;

public class TestoApp extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        BaseManager.getInstance().initialize(this, "33987297118");
    }
}
