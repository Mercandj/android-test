package com.mercandalli.android.apps.test.app;

public enum AppEnum {

    YOUTUBE("youtube", "com.google.android.youtube"),
    GMAIL("gmail", "com.google.android.gm"),
    FILESPACE("filespace", "com.mercandalli.android.apps.files"),
    PONG_FADER("pong", "com.mercandalli.android.apps.pong"),
    EDJING_FREE("edjing_free", "com.edjing.edjingdjturntable"),
    STREAM("stream", "com.djit.apps.stream");

    public final String name;
    public final String packageName;

    AppEnum(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }
}
