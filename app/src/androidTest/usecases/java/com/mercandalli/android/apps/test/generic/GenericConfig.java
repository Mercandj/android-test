package com.mercandalli.android.apps.test.generic;

import android.support.annotation.NonNull;

import com.mercandalli.android.apps.test.app.AppSupported;

public class GenericConfig {

    @NonNull
    private final static AppSupported APP = AppSupported.PONG_FADER;

    @NonNull
    public final static String PACKAGE_TO_TEST = APP.packageName;

    @NonNull
    public final static String APP_TO_TEST = APP.name;
}
