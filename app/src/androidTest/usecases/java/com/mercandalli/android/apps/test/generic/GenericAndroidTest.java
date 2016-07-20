package com.mercandalli.android.apps.test.generic;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.filters.Suppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import com.mercandalli.android.apps.test.LauncherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static com.mercandalli.android.apps.test.TestApp.resetApp;
import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.openApp;
import static android.support.test.uiautomator.UiAutomator.sleep;
import static android.support.test.uiautomator.UiAutomator.takeScreenShot;
import static android.support.test.uiautomator.UiAutomatorSettings.setWifi;

@LargeTest
@Suppress
@RunWith(AndroidJUnit4.class)
public final class GenericAndroidTest {

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
    public void testWifiImpactOnApp() throws UiObjectNotFoundException {
        setWifi(true);
        openApp(GenericConfig.PACKAGE_TO_TEST);

        sleep(1400);

        setWifi(false);
        openApp(GenericConfig.PACKAGE_TO_TEST);

        sleep(1400);

        setWifi(true);
        openApp(GenericConfig.PACKAGE_TO_TEST);

        sleep(1400);
    }

    /**
     * This method tests all the clicks possibility with a recursion of 3 clicks.
     * <p>
     * Modify the package {@link GenericConfig#PACKAGE_TO_TEST}. Install app-debug.apk
     * and app-debug-androidTest-unaligned.apk on the device. Then call :
     * <p>
     * <code>adb shell</code>
     * <p>
     * and then:
     * <p>
     * <code>nohup am instrument -w -r   -e debug false -e class com.mercandalli.android.apps.test.generic.GenericAndroidTest#testGeneric3Clicks com.mercandalli.android.apps.test.test/android.support.test.runner.AndroidJUnitRunner > /sdcard/test.txt &</code>
     * <p>
     * on a rooted device to launch this test without cable.
     *
     * @throws UiObjectNotFoundException
     */
    @Test
    public void testGeneric3Clicks() throws UiObjectNotFoundException {
        final UiDevice device = getDevice();

        openApp(GenericConfig.PACKAGE_TO_TEST);
        sleep(3_500);

        int currentItem1 = 0;
        int currentItem2 = 0;
        int currentItem3 = 0;

        int currentSize1 = 99999;
        int currentSize2 = 99999;
        int currentSize3 = 99999;

        while (currentItem1 < currentSize1) {

            final List<UiObject2> obj1 = getObj();
            currentSize1 = obj1.size();
            if (currentSize1 == 0) {
                return;
            }
            obj1.get(currentItem1).click();
            sleep(1_600);

            takeScreenShot(GenericConfig.PACKAGE_TO_TEST + "_" + currentItem1 + "_" + currentItem2 + "_" + currentItem3 + "_1");

            final List<UiObject2> obj2 = getObj();
            currentSize2 = obj2.size();
            if (currentItem2 < currentSize2) {
                obj2.get(currentItem2).click();
                sleep(1_600);
            }

            takeScreenShot(GenericConfig.PACKAGE_TO_TEST + "_" + currentItem1 + "_" + currentItem2 + "_" + currentItem3 + "_2");

            final List<UiObject2> obj3 = getObj();
            currentSize3 = obj3.size();
            if (currentItem3 < currentSize3) {
                obj3.get(currentItem3).click();
                sleep(1_600);
            }

            takeScreenShot(GenericConfig.PACKAGE_TO_TEST + "_" + currentItem1 + "_" + currentItem2 + "_" + currentItem3 + "_3");

            if (currentItem3 - 1 < currentSize3) {
                currentItem3++;
            } else {
                currentItem3 = 0;
                if (currentItem2 - 1 < currentSize2) {
                    currentItem2++;
                } else {
                    currentItem2 = 0;
                    if (currentItem1 - 1 < currentSize1) {
                        currentItem1++;
                    } else {
                        return;
                    }
                }
            }

            final String logStr = "currentItem1 = " + currentItem1 + " | currentItem2 = " + currentItem2 + " | currentItem3 = " + currentItem3;
            System.out.println(logStr);
            Log.d("UiAutomator", logStr);

            device.pressHome();
            sleep(1_600);
            openApp(GenericConfig.PACKAGE_TO_TEST);
            sleep(3_500);
        }
    }

    @NonNull
    private List<UiObject2> getObj() {
        final UiDevice device = getDevice();
        final List<UiObject2> objects1 = new ArrayList<>();
        objects1.addAll(device.findObjects(By.clazz(GenericConfig.PACKAGE_TO_TEST, "android.widget.Button")));
        objects1.addAll(device.findObjects(By.clazz(GenericConfig.PACKAGE_TO_TEST, "android.widget.ToggleButton")));
        objects1.addAll(device.findObjects(By.clazz(GenericConfig.PACKAGE_TO_TEST, "android.widget.ImageView")));
        objects1.addAll(device.findObjects(By.clazz(GenericConfig.PACKAGE_TO_TEST, "android.widget.Switch")));

        if (objects1.isEmpty()) {
            final List<UiObject2> objects = device.findObjects(By.clickable(true));
            for (final UiObject2 o : objects) {
                if (GenericConfig.PACKAGE_TO_TEST.equals(o.getApplicationPackage())) {
                    objects1.add(o);
                }
            }
        }
        return objects1;
    }
}
