package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.app.AppSupported;
import com.mercandalli.android.apps.test.uiautomator.UiAutomator;
import com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.R.attr.id;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.openApp;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.sleep;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.click;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.clickContainsText;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectById;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorSettings.openAppSetting;

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

            // Skip tutorial
            sleep(2_000);
            if(findObjectById("com.edjing.edjingdjturntable:id/tv_welcome").exists()) {
                click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
            }

            // Wait the ad
            sleep(5_000);

            openAppSetting(AppSupported.EDJING_FREE);
            sleep(200);
            clickContainsText("force stop", "Forcer l'arrêt", "stop", "arrêt");
            click("android:id/button1");

            if((i+1)%3==0) {
                clickContainsText("Clear data");
                click("android:id/button1");
            }
        }
    }
}
