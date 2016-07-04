package com.mercandalli.android.apps.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Until;

import static com.mercandalli.android.apps.test.UiAutomatorLib.getDevice;

public class UiAutomatorUtils {

    public static void openApp(@NonNull final String packageName) {
        final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        final Context context = instrumentation.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            throw new IllegalStateException("App not found in UiAutomatorUtils openApp.");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        getDevice().wait(Until.hasObject(By.pkg(packageName).depth(0)), 6_000);
        instrumentation.waitForIdleSync();
    }
}
