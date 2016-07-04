package com.mercandalli.android.apps.test;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.test.InstrumentationRegistry;

public class TestApp extends TestoApp {

    public static void resetApp(final Context targetContext) {
        InstrumentationRegistry.getInstrumentation().runOnMainSync(
                new Runnable() {
                    @Override
                    public void run() {
                        PreferenceManager.getDefaultSharedPreferences(targetContext).edit().clear().commit();
                    }
                }
        );
    }
}
