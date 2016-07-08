package com.mercandalli.android.apps.test.uiautomator;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.Locale;

import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectById;

public final class UiAutomatorSettings {

    public static void openSettings() {
        final Context context = InstrumentationRegistry.getInstrumentation().getContext();
        final Intent intent = new Intent(Settings.ACTION_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static boolean openAppSetting(@NonNull final String packageName) {
        try {
            //Open the specific App Info page:
            Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.parse("package:" + packageName));
            InstrumentationRegistry.getInstrumentation().getContext().startActivity(intent);
        } catch (ActivityNotFoundException ignored) {
            return false;
        }
        return true;
    }

    public static void openWifiSettings() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        InstrumentationRegistry.getInstrumentation().getContext().startActivity(intent);
    }

    public static void setWifi(final boolean enable) {
        openWifiSettings();
        final UiDevice device = getDevice();
        final UiObject checkbox = device.findObject(new UiSelector().className("android.widget.Switch"));
        try {
            if (!checkbox.isChecked() && enable || checkbox.isChecked() && !enable) {
                checkbox.click();
            }
            device.pressBack();
        } catch (UiObjectNotFoundException ignored) {
        }
    }

    public static void changeLanguage(final UiAutomatorLanguageEnum to) throws UiObjectNotFoundException {
        final UiAutomatorLanguageEnum from = UiAutomatorLanguageEnum.getCurrentLanguage();
        if (from == null) {
            return;
        }
        openSettings();
        UiScrollable scrollable;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android N
            scrollable = new UiScrollable(new UiSelector().className(RecyclerView.class));
            scrollable.scrollIntoView(new UiSelector().textContains(from.settingName));
            scrollable.getChild(new UiSelector().textContains(from.settingName)).click();

            scrollable = new UiScrollable(new UiSelector().className(RecyclerView.class));
            scrollable.scrollIntoView(new UiSelector().textContains(from.settingName));
            scrollable.getChild(new UiSelector().textContains(from.settingName)).click();

            scrollable = new UiScrollable(new UiSelector().className(RecyclerView.class));
            scrollable.scrollIntoView(new UiSelector().textContains(to.name));
            final UiObject fran = scrollable.getChild(new UiSelector().textContains(to.name));
            final UiObject drag = fran.getFromParent(new UiSelector().resourceId("com.android.settings:id/dragHandle"));
            drag.dragTo(0, 0, 40);
        } else {
            scrollable = new UiScrollable(new UiSelector().className(ListView.class));
            scrollable.scrollIntoView(new UiSelector().textContains(from.settingName));
            scrollable.getChild(new UiSelector().textContains(from.settingName)).click();

            scrollable = new UiScrollable(new UiSelector().className(ListView.class));
            scrollable.scrollIntoView(new UiSelector().textContains(from.settingName));
            scrollable.getChild(new UiSelector().textContains(from.settingName)).click();

            scrollable = new UiScrollable(new UiSelector().className(ListView.class));
            scrollable.scrollIntoView(new UiSelector().textContains(to.name));
            scrollable.getChild(new UiSelector().textContains(to.name)).click();

            final UiObject objectById = findObjectById(android.R.id.button1);
            if (objectById.exists()) {
                objectById.click();
            }
        }
        Locale.setDefault(new Locale(to.iso));
    }
}
