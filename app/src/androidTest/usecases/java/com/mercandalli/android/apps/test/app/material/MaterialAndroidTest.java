package com.mercandalli.android.apps.test.app.material;

import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.pressBack;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.pressHome;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.click;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.clickContainsText;

@LargeTest
@Suppress
@RunWith(AndroidJUnit4.class)
public final class MaterialAndroidTest {

    @Test
    public void showMainActions() throws UiObjectNotFoundException {

        // /!\ /!\ /!\
        // Remove the @Suppress annotation of this class
        // in order to use this method.

        getDevice();

        // Go Home: Material is a launcher
        pressHome();

        click("com.mercandalli.android.apps.launcher:id/activity_main_app_button");

        clickContainsText("Actualité", "News", "Actu");

        pressBack();
    }
}
