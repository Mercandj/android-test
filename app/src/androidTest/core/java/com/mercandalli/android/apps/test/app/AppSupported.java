package com.mercandalli.android.apps.test.app;

public enum AppSupported {

    // Google
    YOUTUBE("youtube", "com.google.android.youtube"),
    GMAIL("gmail", "com.google.android.gm"),

    // Team Mercan
    FILESPACE("filespace", "com.mercandalli.android.apps.files"),
    MATERIAL("material", "com.mercandalli.android.apps.launcher"),
    PONG_FADER("pong", "com.mercandalli.android.apps.pong"),

    // Djit
    EDJING_FREE("edjing_free", "com.edjing.edjingdjturntable"),
    EDJING_PRO("edjing_pro", "com.djit.apps.edjing.expert"),
    STREAM("stream", "com.djit.apps.stream");

    public final String name;
    public final String packageName;

    AppSupported(String name, String packageName) {
        this.name = name;
        this.packageName = packageName;
    }
}
