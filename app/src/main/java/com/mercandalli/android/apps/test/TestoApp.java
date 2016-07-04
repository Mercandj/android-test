package com.mercandalli.android.apps.test;

import android.support.annotation.Nullable;
import android.support.multidex.MultiDexApplication;

import com.mercandalli.android.library.base.main.BaseManager;

public class TestoApp extends MultiDexApplication {

    @Nullable
    private static TestoApp sApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;

        BaseManager.getInstance().initialize(
                this,
                "33987297118");
    }

    public static boolean isTablet() {
        return sApplication != null &&
                sApplication.getApplicationContext().getResources().getBoolean(R.bool.is_tablet);
    }
}
