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
import static com.mercandalli.android.apps.test.UiAutomatorLib.dragBottomList;
import static com.mercandalli.android.apps.test.UiAutomatorLib.findObjectById;
import static com.mercandalli.android.apps.test.UiAutomatorLib.findObjectContainsText;
import static com.mercandalli.android.apps.test.UiAutomatorLib.getDevice;
import static com.mercandalli.android.apps.test.UiAutomatorLib.pressBack;
import static com.mercandalli.android.apps.test.UiAutomatorLib.pressHome;
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
    public void testEdjingFreeStore() throws UiObjectNotFoundException {
        getDevice();
        sleep(500);


        takeScreenShotSpoon("launcher");

        click(R.id.launcher_activity_main_button);

        sleep(1_500);

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

        //Click on FX
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_fx_button_deckA");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/fx_sliding_panel"));
        sleep(1_500);
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_fx_button_deckA");

        //Go to settings
        click("com.edjing.edjingdjturntable:id/platine_menu_top_settings");

        //Go to skins
        clickWaitNewWindowContainsText("skins");
        click("com.edjing.edjingdjturntable:id/btn_skin_2");
        click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        click("com.edjing.edjingdjturntable:id/btn_skin_4");
        click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        click("com.edjing.edjingdjturntable:id/btn_skin_5");
        click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        click("com.edjing.edjingdjturntable:id/btn_skin_6");
        click("com.edjing.edjingdjturntable:id/btn_select_skin");
        quitEdjingStore();
        pressBack();

        //Go to store
        clickWaitNewWindowContainsText("Store");
        click("com.edjing.edjingdjturntable:id/price");
        quitEdjingStore();

        //Waiting for ads display
        sleep(5_500);
        //Go back to principal UI
        pressBack();

        if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?").exists()) {
            click("android:id/button2");
        }

        //Click on "no ads"
        click("com.edjing.edjingdjturntable:id/platine_no_ads_btn");
        quitEdjingStore();

        //Click on Pre Cueing
        pressBack();
        pressBack();

        //Go to library
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");
        findObjectContainsText("Titres", "Titles").click();
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        findObjectContainsText("Artistes", "Artists").click();
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        clickWaitNewWindowContainsText("Albums");
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        clickWaitNewWindowContainsText("Playlists");
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        findObjectContainsText("Mes Mixes", "My Mixes").click();
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();
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
