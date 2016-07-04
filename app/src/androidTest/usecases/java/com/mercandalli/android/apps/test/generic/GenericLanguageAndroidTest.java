package com.mercandalli.android.apps.test.generic;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.UiAutomatorLanguageEnum;
import com.mercandalli.android.apps.test.launcher.LauncherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.TestApp.resetApp;
import static com.mercandalli.android.apps.test.UiAutomatorLib.getDevice;
import static com.mercandalli.android.apps.test.UiAutomatorLib.takeScreenShot;
import static com.mercandalli.android.apps.test.UiAutomatorSettingsUtils.changeLanguage;
import static com.mercandalli.android.apps.test.UiAutomatorUtils.openApp;

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
        takeScreenShot("test");
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
        takeScreenShot(title);
    }
}
