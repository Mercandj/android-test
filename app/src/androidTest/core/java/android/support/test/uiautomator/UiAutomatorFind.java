package android.support.test.uiautomator;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import java.util.List;

import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.getResources;

/**
 * An abstract test that launch the app and provide useful test methods.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class UiAutomatorFind {

    /**
     * Find an {@link UiObject} with the resource id.
     *
     * @param id The resource id (e.g. R.id.toolbar).
     * @return The UiObject.
     */
    @NonNull
    public static UiObject findObjectById(@IdRes final int id) {
        return findObjectById(getResources().getResourceName(id));
    }

    /**
     * Find an {@link UiObject} with the resource id.
     *
     * @param id The resource id (e.g. the_package_name:id/the_id).
     * @return The UiObject.
     */
    @NonNull
    public static UiObject findObjectById(final String id) {
        return getDevice().findObject(new UiSelector().resourceId(id));
    }

    /**
     * Find an {@link UiObject} with a specific {@link String} displayed.
     *
     * @param text A text displayed.
     * @return The {@link UiObject}.
     */
    @NonNull
    public static UiObject findObjectContainsText(final String text) {
        return getDevice().findObject(new UiSelector().textContains(text));
    }

    /**
     * Find an {@link UiObject} with a specific {@link String} displayed. For each {@link String}
     * of the {@link List} given, this method returns the first {@link UiObject} textContains.
     *
     * @param texts A {@link List} of texts.
     * @return The {@link UiObject}.
     */
    @NonNull
    public static UiObject findObjectContainsText(final String... texts) {
        if (texts.length == 0) {
            throw new IllegalStateException("List empty");
        }
        for (final String text : texts) {
            final UiObject objectContainsText = findObjectContainsText(text);
            if (objectContainsText.exists()) {
                return objectContainsText;
            }
        }
        return findObjectContainsText(texts[0]);
    }

    /**
     * Find an {@link UiObject} with a specific {@link String} displayed. For each {@link String}
     * of the {@link List} given, this method returns the first {@link UiObject} textContains.
     *
     * @param texts A {@link List} of texts.
     * @return The {@link UiObject}.
     */
    @NonNull
    public static UiObject findObjectContainsText(final List<String> texts) {
        if (texts.isEmpty()) {
            throw new IllegalStateException("List empty");
        }
        for (final String text : texts) {
            final UiObject objectContainsText = findObjectContainsText(text);
            if (objectContainsText.exists()) {
                return objectContainsText;
            }
        }
        return findObjectContainsText(texts.get(0));
    }

    /**
     * Find an {@link UiObject} with a specific {@link String} displayed.
     *
     * @param containsTextId The string resource id (e.g. R.string.the_id).
     * @return The UiObject.
     */
    @NonNull
    public static UiObject findObjectContainsText(@StringRes final int containsTextId) {
        return getDevice().findObject(new UiSelector().textContains(getResources().getString(containsTextId)));
    }
}