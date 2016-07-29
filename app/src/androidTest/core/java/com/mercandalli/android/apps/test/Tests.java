package com.mercandalli.android.apps.test;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Static methods for dealing with tests.
 */
public final class Tests {

    /**
     * Get the current {@link Activity} displayed.
     *
     * @return The current {@link Activity}.
     */
    @Nullable
    public static Activity getCurrentActivity() {
        final Activity[] activity = new Activity[1];
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                java.util.Collection<Activity> activities = ActivityLifecycleMonitorRegistry
                        .getInstance().getActivitiesInStage(Stage.RESUMED);
                if (activities.size() == 0) {
                    activity[0] = null;
                } else {
                    activity[0] = Iterables.getOnlyElement(activities);
                }

            }
        });
        return activity[0];
    }



    private Tests() {

    }
}
