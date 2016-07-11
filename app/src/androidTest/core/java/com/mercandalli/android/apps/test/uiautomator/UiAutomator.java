package com.mercandalli.android.apps.test.uiautomator;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.mercandalli.android.apps.test.app.AppSupported;
import com.mercandalli.android.apps.test.generic.GenericConfig;
import com.squareup.spoon.Spoon;

import junit.framework.Assert;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectById;
import static org.hamcrest.CoreMatchers.instanceOf;

/**
 * An abstract test that launch the app and provide useful test methods.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class UiAutomator {

    /**
     * A simple thread sleep.
     *
     * @param timeMillis The time in millis.
     */
    public static void sleep(final int timeMillis) {
        SystemClock.sleep(timeMillis);
    }

    public static String getCurrentDateString() {
        return DateFormat.getDateTimeInstance().format(new Date())
                .replaceAll("\\s", "").replaceAll(":", "").replaceAll(",", "").trim();
    }

    public static Activity getActivity() {
        final Activity[] activity = new Activity[1];
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                activity[0] = Iterables.getOnlyElement(ActivityLifecycleMonitorRegistry.getInstance()
                        .getActivitiesInStage(Stage.RESUMED));
            }
        });
        return activity[0];
    }

    public static Context getContext() {
        return InstrumentationRegistry.getTargetContext();
    }

    public static Resources getResources() {
        return InstrumentationRegistry.getTargetContext().getResources();
    }

    //region UI AUTOMATOR base
    public static UiDevice getDevice() {
        return UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    public static void pressBack() {
        getDevice().pressBack();
    }

    public static void pressHome() {
        getDevice().pressHome();
    }
    //endregion UI AUTOMATOR base

    /**
     * Is this {@link UiObject} clickable.
     *
     * @param uiObject A {@link UiObject}.
     * @return True if clickable.
     * @throws UiObjectNotFoundException Throw an exception if not found.
     */
    public static boolean isObjectClickable(final UiObject uiObject) throws UiObjectNotFoundException {
        return uiObject != null && uiObject.exists() && uiObject.isClickable();
    }

    //region - click & swipe

    public static boolean swipeUpById(
            @IdRes final int id,
            final int speedSteps) throws UiObjectNotFoundException {
        return findObjectById(id).swipeUp(speedSteps);
    }

    public static boolean swipeDownById(
            @IdRes final int id,
            final int speedSteps) throws UiObjectNotFoundException {
        return findObjectById(id).swipeDown(speedSteps);
    }

    public static boolean swipeLeftById(
            @IdRes final int id,
            final int speedSteps) throws UiObjectNotFoundException {
        return findObjectById(id).swipeLeft(speedSteps);
    }

    public static boolean swipeRightById(
            @IdRes final int id,
            final int speedSteps) throws UiObjectNotFoundException {
        return findObjectById(id).swipeRight(speedSteps);
    }
    //endregion - click & swipe

    //region - setText
    public static void setText(
            final String id,
            final String text) throws UiObjectNotFoundException {
        final UiObject objectById = findObjectById(id);
        objectById.clickAndWaitForNewWindow(100);
        objectById.setText(text);
    }

    public static void setText(
            @IdRes final int id,
            final String text) throws UiObjectNotFoundException {
        setText(getResources().getResourceName(id), text);
    }
    //endregion - setText

    public static void dragBottomList(final UiObject list) throws UiObjectNotFoundException {
        final UiDevice device = getDevice();
        final int childCount = list.getChildCount();
        final int startX = device.getDisplayWidth() / 2;
        for (int i = 0; i < (childCount)+1; i++) {
            device.drag(startX, device.getDisplayHeight() - 40, startX, 40, 170);
        }
    }

    public static void dragTopList(final UiObject list) throws UiObjectNotFoundException {
        final UiDevice device = getDevice();
        final int childCount = list.getChildCount();
        final int startX = device.getDisplayWidth() / 2;
        for (int i = 0; i < (childCount / 4) + 1; i++) {
            device.drag(startX, 40, startX, device.getDisplayHeight() - 40, 100);
        }
    }

    //region - assert
    public static void assertExists(@IdRes final int id) throws UiObjectNotFoundException {
        Assert.assertTrue(isObjectClickable(id));
    }

    public static void assertExistsContains(final String textContains) {
        Assert.assertTrue(isObjectExistsContains(textContains));
    }

    public static boolean isObjectClickable(@IdRes final int id) throws UiObjectNotFoundException {
        return findObjectById(id).exists();
    }

    public static boolean isObjectExistsContains(final String textContains) {
        return getDevice().findObject(new UiSelector()
                .textContains(textContains))
                .exists();
    }
    //endregion - assert

    //region - device actions
    public static void orientationLeft() {
        try {
            getDevice().setOrientationRight();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        sleep(100);
    }

    public static void orientationRight() {
        try {
            getDevice().setOrientationLeft();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        sleep(100);
    }

    public static void orientationPortrait() {
        try {
            getDevice().setOrientationNatural();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        sleep(100);
    }
    //endregion - device actions


    public static void takeScreenShot(final String title) {
        getInstrumentation().waitForIdleSync();
        sleep(500);
        final File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/app_spoon-screenshots/" + GenericConfig.APP_TO_TEST + "/");
        getDevice().takeScreenshot(new File(getScreenFolder().getAbsolutePath(), title + ".png"), 1.0f, 100);
        getInstrumentation().waitForIdleSync();
    }

    public static void takeScreenShotSpoon(final String title) {
        takeScreenShotSpoon(getActivity(), title);
    }

    public static void takeScreenShotSpoon(final String title, String testClassName, String testMethodName) {
        takeScreenShotSpoon(getActivity(), title, testClassName, testMethodName);
    }

    public static void takeScreenShotSpoon(final Activity activity, final String title) {
        getInstrumentation().waitForIdleSync();
        Spoon.screenshot(activity, title);
        getInstrumentation().waitForIdleSync();
    }

    public static void takeScreenShotSpoon(final Activity activity, final String title, final String testClassName, final String testMethodName) {
        getInstrumentation().waitForIdleSync();
        Spoon.screenshot(activity, title);
        getInstrumentation().waitForIdleSync();
    }


    @NonNull
    private static File getScreenFolder() {
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/app_spoon-screenshots/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                "/app_spoon-screenshots/" + GenericConfig.APP_TO_TEST + "/");
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    /**
     * Assert that the current activity is an instance of a given class and finish it.
     *
     * @param activityClass the {@link Class}
     * @throws Throwable
     */
    public static void finish(Class<? extends Activity> activityClass) {
        sleep(800);
        final Activity currentActivity = getActivity();
        assertThat(currentActivity, instanceOf(activityClass));
        currentActivity.finish();
        sleep(800);
    }

    public static void openApp(@NonNull final AppSupported appSupported) {
        openApp(appSupported.packageName);
    }

    public static void openApp(@NonNull final String packageName) {
        final Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        final Context context = instrumentation.getContext();
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            throw new IllegalStateException("App not found in UiAutomatorUtils openApp.");
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        getDevice().wait(Until.hasObject(By.pkg(packageName).depth(0)), 6_000);
        instrumentation.waitForIdleSync();
    }
}