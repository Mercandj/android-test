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
import static android.support.test.uiautomator.UiAutomatorClick.click;
import static android.support.test.uiautomator.UiAutomatorClick.clickContainsText;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectById;
import static android.support.test.uiautomator.UiAutomatorSettings.openAppSetting;

/**
 * Comment the @Suppress to use this class.
 */
@LargeTest
//@Suppress
@RunWith(AndroidJUnit4.class)
public final class EdjingFreeAdsAndroidTest {

    private static final int TIME_TO_TEST_ADS = 50;

    @Test
    public void testEdjingFreeAds() throws UiObjectNotFoundException {

        // /!\ /!\ /!\
        // Remove the @Suppress annotation of this class
        // in order to use this method.

        getDevice();

        for (int i = 0; i < TIME_TO_TEST_ADS; i++) {
            openApp(AppSupported.EDJING_FREE);

            // Skip tutorial.
            sleep(2_000);
            if (findObjectById("com.edjing.edjingdjturntable:id/tv_welcome").exists()) {
                click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
            }

            // Wait the ad.
            sleep(5_000);

            // Force stop the app.
            openAppSetting(AppSupported.EDJING_FREE);
            sleep(200);
            clickContainsText("force stop", "Forcer l'arrêt", "stop", "arrêt");
            click("android:id/button1");

            // Clear data.
            if ((i + 1) % 3 == 0) {
                clickContainsText("Clear data");
                click("android:id/button1");
            }
        }
    }
}
