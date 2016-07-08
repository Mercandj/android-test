package com.mercandalli.android.apps.test;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.launcher.LauncherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.TestApp.resetApp;
import static com.mercandalli.android.apps.test.UiAutomatorLib.click;
import static com.mercandalli.android.apps.test.UiAutomatorLib.getDevice;
import static com.mercandalli.android.apps.test.UiAutomatorLib.sleep;
import static com.mercandalli.android.apps.test.UiAutomatorLib.takeScreenShotSpoon;

@LargeTest
@RunWith(AndroidJUnit4.class)
public final class LauncherAndroidTest {

    /**
     * Has to be public.
     */
    @Rule
    @NonNull
    public final ActivityTestRule<LauncherActivity> activityRule =
            new ActivityTestRule<LauncherActivity>(LauncherActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    resetApp(InstrumentationRegistry.getTargetContext());
                }
            };

    @Test
    public void testPlaySimpleGame() throws UiObjectNotFoundException {
        getDevice();
        sleep(500);

        takeScreenShotSpoon("launcher");

        click(R.id.launcher_activity_main_button);

        sleep(1_500);

        UiAutomatorLib.pressHome();

        click("com.mercandalli.android.apps.launcher:id/activity_main_app_button");

        click("edjing");

        UiAutomatorLib.pressBack();
    }
}
