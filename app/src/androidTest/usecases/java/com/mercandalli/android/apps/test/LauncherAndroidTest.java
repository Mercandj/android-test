package com.mercandalli.android.apps.test;

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
import static com.mercandalli.android.apps.test.UiAutomatorLib.findObjectById;
import static com.mercandalli.android.apps.test.UiAutomatorLib.findObjectContainsText;
import static com.mercandalli.android.apps.test.UiAutomatorLib.getDevice;
import static com.mercandalli.android.apps.test.UiAutomatorLib.sleep;
import static com.mercandalli.android.apps.test.UiAutomatorLib.takeScreenShotSpoon;

@LargeTest
@RunWith(AndroidJUnit4.class)
public final class LauncherAndroidTest {

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
    public void testPlaySimpleGame() throws UiObjectNotFoundException {
        UiAutomatorLib.getDevice();
        UiAutomatorLib.sleep(500);

        UiAutomatorLib.takeScreenShotSpoon("launcher");

        UiAutomatorLib.click(R.id.launcher_activity_main_button);

        UiAutomatorLib.sleep(1_500);

        UiAutomatorLib.pressHome();

        //UiAutomatorLib.click("com.ape.launcher:id/app_icon_title");
        UiAutomatorLib.clickWaitNewWindowContainsText("edjing");
        //Waiting for ads display
        UiAutomatorLib.sleep(5_500);
        //Go back to principal UI
        UiAutomatorLib.pressBack();

        if (UiAutomatorLib.findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?").exists()) {
            UiAutomatorLib.click("android:id/button2");
        }

        //Go to settings
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/platine_menu_top_settings");

        //Go to skins
        UiAutomatorLib.clickWaitNewWindowContainsText("skins");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_skin_2");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_skin_4");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_skin_5");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_skin_6");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        UiAutomatorLib.pressBack();

        //Go to store
        UiAutomatorLib.clickWaitNewWindowContainsText("Store");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/price");
        quitEdjingStore();

        //Go to library
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");
        UiAutomatorLib.findObjectContainsText("Titres", "Titles").click();
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);
        findObjectById("com.edjing.edjingdjturntable:id/row_track_library").swipeDown(100);
        UiAutomatorLib.findObjectContainsText("Artistes", "Artists").click();
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll").swipeDown(100);
        UiAutomatorLib.clickWaitNewWindowContainsText("Albums");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll").swipeDown(100);
        UiAutomatorLib.clickWaitNewWindowContainsText("Playlists");
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);
        findObjectById("ccom.edjing.edjingdjturntable:id/list_fast_scroll").swipeDown(100);
        UiAutomatorLib.findObjectContainsText("Mes Mixes", "My Mixes").click();
        UiAutomatorLib.click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);
        findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll").swipeDown(100);




        //UiAutomatorLib.findObjectContainsText("News", "Actualités", "actu").click();


        //findObjectContainsText("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);


    }

    private void quitEdjingStore() {
        UiAutomatorLib.sleep(5_500);
        UiAutomatorLib.pressBack();
        UiAutomatorLib.sleep(4_500);
        UiAutomatorLib.pressBack();
        UiAutomatorLib.sleep(2_500);
        UiAutomatorLib.pressBack();
    }
}
