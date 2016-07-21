package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.openApp;
import static android.support.test.uiautomator.UiAutomator.sleep;
import static android.support.test.uiautomator.UiAutomator.takeScreenShot;
import static android.support.test.uiautomator.UiAutomatorClick.click;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectById;

/**
 * Comment the @Suppress to use this class.
 */
@LargeTest
//@Suppress
@RunWith(AndroidJUnit4.class)
public final class EdjingFreeScreenShotSampleAndroidTest {

    @Test
    public void testEdjingFreeScreenShots() throws UiObjectNotFoundException {

        // /!\ /!\ /!\
        // Remove the @Suppress annotation of this class
        // in order to use this method.

        getDevice();
        openApp(AppSupported.EDJING_FREE);

        // Skip tutorial.
        sleep(2_000);
        if (findObjectById("com.edjing.edjingdjturntable:id/tv_welcome").exists()) {
            click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
        }

        takeScreenShot(AppSupported.EDJING_FREE, "my_first_screen_shot");
    }
}
