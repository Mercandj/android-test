package com.mercandalli.android.apps.test.app;

public enum AppEnum {

    // Google
    YOUTUBE("youtube", "com.google.android.youtube"),
    GMAIL("gmail", "com.google.android.gm"),

    // Team Mercan
    FILESPACE("filespace", "com.mercandalli.android.apps.files"),
    MATERIAL("material", "com.mercandalli.android.apps.launcher"),
    PONG_FADER("pong", "com.mercandalli.android.apps.pong"),

    // Djit
    EDJING_FREE("edjing_free", "com.edjing.edjingdjturntable"),
    STREAM("stream", "com.djit.apps.stream");

    public final String name;
    public final String packageName;

    AppEnum(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }
}
