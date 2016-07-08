package com.mercandalli.android.apps.test.app.youtube;

import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.app.AppEnum;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.openApp;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.sleep;

@LargeTest
@Suppress
@RunWith(AndroidJUnit4.class)
public final class YoutubeAndroidTest {

    @Test
    public void testYoutube() throws UiObjectNotFoundException {

        // /!\ /!\ /!\
        // Remove the @Suppress annotation of this class
        // in order to use this method.

        getDevice();

        sleep(500);

        openApp(AppEnum.YOUTUBE);
    }
}
