package com.mercandalli.android.apps.test.app.testo;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.R;
import com.mercandalli.android.apps.test.LauncherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.TestApp.resetApp;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.openApp;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.sleep;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.takeScreenShotSpoon;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.click;

/**
 * Comment the @Suppress to use this class.
 */
@LargeTest
@Suppress
@RunWith(AndroidJUnit4.class)
public final class TestoAndroidTest {

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
    public void testThisApp() throws UiObjectNotFoundException {

        // /!\ /!\ /!\
        // Remove the @Suppress annotation of this class
        // in order to use this method.

        getDevice();

        sleep(500);

        takeScreenShotSpoon("main_actions");

        click(R.id.launcher_activity_main_button);
    }
}
