package com.mercandalli.android.apps.test.generic;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import android.support.test.uiautomator.UiAutomatorLanguageEnum;
import com.mercandalli.android.apps.test.LauncherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.UiAutomator.takeScreenShotGeneric;
import static com.mercandalli.android.apps.test.TestApp.resetApp;
import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.openApp;
import static android.support.test.uiautomator.UiAutomator.takeScreenShot;
import static android.support.test.uiautomator.UiAutomatorSettings.changeLanguage;

@LargeTest
@Suppress
@RunWith(AndroidJUnit4.class)
public final class GenericLanguageAndroidTest {

    /**
     * Has to be public.
     */
    @Rule
    @NonNull
    public final ActivityTestRule<LauncherActivity> activityRule = new ActivityTestRule<LauncherActivity>(LauncherActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            resetApp(InstrumentationRegistry.getTargetContext());
        }
    };

    @Test
    public void testLanguage() throws UiObjectNotFoundException {
        final UiDevice device = getDevice();
        takeScreenShotGeneric("test");
        for (final UiAutomatorLanguageEnum uiAutomatorLanguageEnum : UiAutomatorLanguageEnum.values()) {
            launchInLanguage(
                    uiAutomatorLanguageEnum,
                    GenericConfig.APP_TO_TEST + "_launcher_" + uiAutomatorLanguageEnum.iso);
            device.pressBack();
        }
    }

    private void launchInLanguage(
            final UiAutomatorLanguageEnum uiAutomatorLanguageEnum,
            final String title) throws UiObjectNotFoundException {
        changeLanguage(uiAutomatorLanguageEnum);
        openApp(GenericConfig.PACKAGE_TO_TEST);
        takeScreenShotGeneric(title);
    }
}
