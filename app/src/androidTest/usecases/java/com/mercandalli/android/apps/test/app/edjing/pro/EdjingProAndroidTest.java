package com.mercandalli.android.apps.test.app.edjing.pro;

import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.openApp;

/**
 * Comment the @Suppress to use this class.
 */
@LargeTest
@Suppress
@RunWith(AndroidJUnit4.class)
public final class EdjingProAndroidTest {

    @Test
    public void testEdjingFreeAds() throws UiObjectNotFoundException {

        // /!\ /!\ /!\
        // Remove the @Suppress annotation of this class
        // in order to use this method.

        getDevice();
        openApp(AppSupported.EDJING_PRO);
    }
}
