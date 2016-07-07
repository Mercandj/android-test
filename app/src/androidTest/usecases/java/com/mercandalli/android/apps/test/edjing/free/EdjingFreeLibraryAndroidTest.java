package com.mercandalli.android.apps.test.edjing.free;

import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;

import com.mercandalli.android.apps.test.launcher.LauncherActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.TestApp.resetApp;
import static com.mercandalli.android.apps.test.UiAutomatorLib.click;
import static com.mercandalli.android.apps.test.UiAutomatorLib.clickWaitNewWindowContainsText;
import static com.mercandalli.android.apps.test.UiAutomatorLib.dragBottomList;
import static com.mercandalli.android.apps.test.UiAutomatorLib.findObjectById;
import static com.mercandalli.android.apps.test.UiAutomatorLib.findObjectContainsText;
import static com.mercandalli.android.apps.test.UiAutomatorLib.getDevice;
import static com.mercandalli.android.apps.test.UiAutomatorLib.pressBack;
import static com.mercandalli.android.apps.test.UiAutomatorLib.pressHome;
import static com.mercandalli.android.apps.test.UiAutomatorLib.sleep;

@LargeTest
@RunWith(AndroidJUnit4.class)
public final class EdjingFreeLibraryAndroidTest {

    /**
     * Has to be public.
     */
    @Rule
    @NonNull
    public final ActivityTestRule<LauncherActivity> activityRule =
            new ActivityTestRule<LauncherActivity>(LauncherActivity.class) {
                @Override
                protected void beforeActivityLaunched() {
                    super.beforeActivityLaunched();
                    resetApp(InstrumentationRegistry.getTargetContext());
                }
            };

    @Test
    public void testEdjingFreeNoAds() throws UiObjectNotFoundException {
        getDevice();
        sleep(500);

        pressHome();

        //click("com.ape.launcher:id/app_icon_title");
        clickWaitNewWindowContainsText("edjing");
        //Waiting for ads display
        sleep(5_500);
        //Go back to principal UI
        pressBack();

        if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?").exists()) {
            click("android:id/button2");
        }

        //Go to library
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");

        //Go to queue and delete item
        click("com.edjing.edjingdjturntable:id/queue_fab");

        //Go to titles
        findObjectContainsText("Titres", "Titles").click();
        click("com.edjing.edjingdjturntable:id/row_track_library_overflow_button");

        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        findObjectContainsText("Artistes", "Artists").click();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        clickWaitNewWindowContainsText("Albums");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        clickWaitNewWindowContainsText("Playlists");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        findObjectContainsText("Mes Mixes", "My Mixes").click();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));


        //dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));

        //findObjectContainsText("News", "Actualités", "actu").click();


        //findObjectContainsText("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);


    }

    private void quitEdjingStore() {
        sleep(5_500);
        if (findObjectContainsText("CONTINUER", "CONTINUE", "OK").exists()) {
            pressBack();
            sleep(4_500);
        }
        pressBack();
        sleep(2_500);
        pressBack();
    }

}
