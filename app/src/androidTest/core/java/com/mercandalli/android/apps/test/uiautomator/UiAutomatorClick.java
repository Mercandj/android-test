package com.mercandalli.android.apps.test.uiautomator;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import junit.framework.Assert;

import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectById;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectContainsText;

/**
 * An abstract test that launch the app and provide useful test methods.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class UiAutomatorClick {

    /**
     * Call clickAndWaitForNewWindow.*
     *
     * @param id The view id.
     * @throws UiObjectNotFoundException
     */
    public static boolean click(
            @IdRes final int id) throws UiObjectNotFoundException {
        return findObjectById(id).click();
    }

    public static boolean click(
            final String id) throws UiObjectNotFoundException {
        return findObjectById(id).click();
    }

    public static boolean clickContainsText(
            final String text) throws UiObjectNotFoundException {
        return findObjectContainsText(text).click();
    }

    public static boolean clickContainsText(
            final String... texts) throws UiObjectNotFoundException {
        return findObjectContainsText(texts).click();
    }

    /**
     * Call clickAndWaitForNewWindow.
     *
     * @param id The view id.
     * @throws UiObjectNotFoundException
     */
    public static boolean clickWaitNewWindow(
            @IdRes final int id) throws UiObjectNotFoundException {
        return clickWaitNewWindow(id, 5_500);
    }

    /**
     * Call clickAndWaitForNewWindow.
     *
     * @param id      The view id.
     * @param timeout timeout before giving up on waiting for a new window
     * @throws UiObjectNotFoundException Throw an exception if not found.
     */
    public static boolean clickWaitNewWindow(
            @IdRes final int id,
            final long timeout) throws UiObjectNotFoundException {
        return findObjectById(id).clickAndWaitForNewWindow(timeout);
    }

    /**
     * Call clickAndWaitForNewWindow. You can use this method to click on external app view
     * like a system popup or the launcher screen...
     *
     * @param id The full string id given by sdk/tools/uiautomatorviewer.bat.
     *           (e.g. the_package_name:id/the_id).
     * @return The clickAndWaitForNewWindow result.
     * @throws UiObjectNotFoundException Throw an exception if not found.
     */
    public static boolean clickWaitNewWindow(
            final String id) throws UiObjectNotFoundException {
        return findObjectById(id).clickAndWaitForNewWindow(5_500);
    }

    /**
     * Check if the object exists and click on it (wait new windows).
     *
     * @param containsText The text displayed.
     * @return The clickAndWaitForNewWindow result.
     * @throws UiObjectNotFoundException Throw an exception if not found.
     */
    public static boolean clickWaitNewWindowContainsText(
            final String containsText) throws UiObjectNotFoundException {
        final UiObject uiObject = findObjectContainsText(containsText);
        Assert.assertTrue(uiObject.exists());
        return uiObject.clickAndWaitForNewWindow(5_500);
    }

    /**
     * Check if the object exists and click on it (wait new windows).
     *
     * @param containsText The text displayed.
     * @return The clickAndWaitForNewWindow result.
     * @throws UiObjectNotFoundException Throw an exception if not found.
     */
    public static boolean clickWaitNewWindowContainsText(
            final String... containsText) throws UiObjectNotFoundException {
        final UiObject uiObject = findObjectContainsText(containsText);
        Assert.assertTrue(uiObject.exists());
        return uiObject.clickAndWaitForNewWindow(5_500);
    }

    /**
     * Check if the object exists and click on it (wait new windows).
     *
     * @param containsTextId The text displayed.
     * @return The clickAndWaitForNewWindow result.
     * @throws UiObjectNotFoundException Throw an exception if not found.
     */
    public static boolean clickWaitNewWindowContainsText(
            @StringRes final int containsTextId) throws UiObjectNotFoundException {
        final UiObject uiObject = findObjectContainsText(containsTextId);
        Assert.assertTrue(uiObject.exists());
        return uiObject.clickAndWaitForNewWindow(5_500);
    }

    public static void clickListViewItemContainsText(
            @NonNull final String listId,
            @NonNull final String childContainsText) throws UiObjectNotFoundException {
        final UiScrollable listView = new UiScrollable(new UiSelector().resourceId(listId));
        listView.setMaxSearchSwipes(100);
        listView.scrollTextIntoView(childContainsText);
        listView.waitForExists(5000);
        UiObject listViewItem = listView.getChild(new UiSelector().textContains(childContainsText));
        listViewItem.click();
    }
}