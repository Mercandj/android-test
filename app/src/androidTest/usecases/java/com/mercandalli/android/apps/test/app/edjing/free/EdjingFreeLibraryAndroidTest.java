package com.mercandalli.android.apps.test.app.edjing.free;

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
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.dragBottomList;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.dragTopList;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.pressBack;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.pressHome;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.sleep;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.click;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.clickWaitNewWindowContainsText;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectById;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectContainsText;

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
        sleep(5_500);

        if (findObjectContainsText("ommencer","starting").exists()){
            click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
        } else {
            //Waiting for ads display
            sleep(5_500);

            //Go back to principal UI
            pressBack();

            if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?","Do you really want to shut down the app?").exists()) {
                click("android:id/button2");
            }
        }

        //Go to library
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");

        //Go to queue and delete item
        click("com.edjing.edjingdjturntable:id/queue_fab");
        while (findObjectById("com.edjing.edjingdjturntable:id/row_current_list_delete_button").exists()) {
            click("com.edjing.edjingdjturntable:id/row_current_list_delete_button");
        }
        pressBack();

        //Go to tracks
        findObjectContainsText("Titres", "Tracks").click();
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        click("android:id/button1");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        click("android:id/button2");
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));

        findObjectContainsText("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);
        click("com.edjing.edjingdjturntable:id/row_track_library_overflow_button");
        findObjectContainsText("Add to the queue", "Ajouter à la file d'attente");

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
