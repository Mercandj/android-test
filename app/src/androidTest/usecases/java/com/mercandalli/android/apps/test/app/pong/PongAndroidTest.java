package com.mercandalli.android.apps.test.app.pong;

import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.openApp;

/**
 * Comment the @Suppress to use this class.
 */
@LargeTest
@Suppress
@RunWith(AndroidJUnit4.class)
public final class PongAndroidTest {

    @Test
    public void testPong() throws UiObjectNotFoundException {

        // /!\ /!\ /!\
        // Remove the @Suppress annotation of this class
        // in order to use this method.

        getDevice();
        openApp(AppSupported.PONG_FADER);
    }
}
