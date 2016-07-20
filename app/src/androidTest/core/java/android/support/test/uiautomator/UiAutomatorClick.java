package android.support.test.uiautomator;

import android.graphics.Rect;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.accessibility.AccessibilityNodeInfo;

import junit.framework.Assert;

import static android.support.test.uiautomator.UiAutomator.getDevice;

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
        return UiAutomatorFind.findObjectById(id).click();
    }

    public static boolean click(
            final String id) throws UiObjectNotFoundException {
        return UiAutomatorFind.findObjectById(id).click();
    }

    public static boolean clickContainsText(
            final String text) throws UiObjectNotFoundException {
        return UiAutomatorFind.findObjectContainsText(text).click();
    }

    public static boolean clickContainsText(
            final String... texts) throws UiObjectNotFoundException {
        return UiAutomatorFind.findObjectContainsText(texts).click();
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
        return UiAutomatorFind.findObjectById(id).clickAndWaitForNewWindow(timeout);
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
        return UiAutomatorFind.findObjectById(id).clickAndWaitForNewWindow(5_500);
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
        final UiObject uiObject = UiAutomatorFind.findObjectContainsText(containsText);
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
        final UiObject uiObject = UiAutomatorFind.findObjectContainsText(containsText);
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
        final UiObject uiObject = UiAutomatorFind.findObjectContainsText(containsTextId);
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

    public static boolean clickTopRight(@NonNull final UiObject uiObject) throws UiObjectNotFoundException {
        Tracer.trace();
        final AccessibilityNodeInfo node = uiObject.findAccessibilityNodeInfo(10 * 1000);
        if (node == null) {
            throw new UiObjectNotFoundException(uiObject.getSelector().toString());
        }
        final Rect rect = getVisibleBounds(uiObject, node);
        return uiObject.getInteractionController().clickAndSync(rect.right - 5, rect.top + 5,
                3 * 1000);
    }

    /**
     * Finds the visible bounds of a partially visible UI element
     *
     * @param node
     * @return null if node is null, else a Rect containing visible bounds
     */
    private static Rect getVisibleBounds(@NonNull final UiObject uiObject, final AccessibilityNodeInfo node) {
        if (node == null) {
            return null;
        }

        // targeted node's bounds
        final int w = getDevice().getDisplayWidth();
        final int h = getDevice().getDisplayHeight();
        final Rect nodeRect = AccessibilityNodeInfoHelper.getVisibleBoundsInScreen(node, w, h);

        // is the targeted node within a scrollable container?
        AccessibilityNodeInfo scrollableParentNode = getScrollableParent(node);
        if (scrollableParentNode == null) {
            // nothing to adjust for so return the node's Rect as is
            return nodeRect;
        }

        // Scrollable parent's visible bounds
        Rect parentRect = AccessibilityNodeInfoHelper
                .getVisibleBoundsInScreen(scrollableParentNode, w, h);
        // adjust for partial clipping of targeted by parent node if required
        nodeRect.intersect(parentRect);
        return nodeRect;
    }

    /**
     * Walks up the layout hierarchy to find a scrollable parent. A scrollable parent
     * indicates that this node might be in a container where it is partially
     * visible due to scrolling. In this case, its clickable center might not be visible and
     * the click coordinates should be adjusted.
     *
     * @param node
     * @return The accessibility node info.
     */
    private static AccessibilityNodeInfo getScrollableParent(final AccessibilityNodeInfo node) {
        AccessibilityNodeInfo parent = node;
        while (parent != null) {
            parent = parent.getParent();
            if (parent != null && parent.isScrollable()) {
                return parent;
            }
        }
        return null;
    }
}