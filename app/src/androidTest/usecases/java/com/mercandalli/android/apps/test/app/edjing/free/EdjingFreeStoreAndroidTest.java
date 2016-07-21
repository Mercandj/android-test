package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;


import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.UiAutomator.dragBottomList;
import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.pressBack;
import static android.support.test.uiautomator.UiAutomator.pressHome;
import static android.support.test.uiautomator.UiAutomator.sleep;
import static android.support.test.uiautomator.UiAutomatorClick.click;
import static android.support.test.uiautomator.UiAutomatorClick.clickWaitNewWindowContainsText;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectById;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectContainsText;

@LargeTest
//@Suppress
@RunWith(AndroidJUnit4.class)
public final class EdjingFreeStoreAndroidTest {

    @Test
    public void testEdjingFreeStore() throws UiObjectNotFoundException {
        getDevice();
        sleep(500);

        pressHome();

        //click("com.ape.launcher:id/app_icon_title");
        clickWaitNewWindowContainsText("edjing");
        sleep(5_500);

        if (findObjectContainsText("ommencer", "starting").exists()) {
            click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
        } else {
            //Waiting for ads display
            sleep(5_500);

            //Go back to principal UI
            pressBack();

            if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
                click("android:id/button2");
            }
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
    }

    @Test
    public void testEdjingFreeNoAds() throws UiObjectNotFoundException {
        getDevice();
        sleep(500);

        pressHome();

        //click("com.ape.launcher:id/app_icon_title");
        clickWaitNewWindowContainsText("edjing");
        sleep(5_500);

        if (findObjectContainsText("ommencer", "starting").exists()) {
            click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
        } else {
            //Waiting for ads display
            sleep(5_500);

            //Go back to principal UI
            pressBack();

            if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
                click("android:id/button2");
            }
        }

        //Click on "no ads"
        click("com.edjing.edjingdjturntable:id/platine_no_ads_btn");
        quitEdjingStore();

        //Click on Pre Cueing
        pressBack();
        pressBack();

        //Go to library
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");
        findObjectContainsText("Titres", "Tracks").click();
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();

        findObjectContainsText("Artistes", "Artists").click();
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();

        clickWaitNewWindowContainsText("Albums");
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();

        clickWaitNewWindowContainsText("Playlists");
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();

        findObjectContainsText("Mes Mixes", "My Mixes").click();
        click("com.edjing.edjingdjturntable:id/header_fullpack_get_it");
        quitEdjingStore();


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
