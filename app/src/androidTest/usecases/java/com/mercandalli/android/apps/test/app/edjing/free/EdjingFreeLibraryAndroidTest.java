package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.mercandalli.android.apps.test.app.AppSupported;
import com.mercandalli.android.apps.test.uiautomator.UiAutomator;

import org.junit.Test;
import org.junit.runner.RunWith;

import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.dragBottomList;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.dragTopList;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.getDevice;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.openApp;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.pressBack;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.pressHome;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomator.sleep;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.click;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorClick.clickWaitNewWindowContainsText;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectById;
import static com.mercandalli.android.apps.test.uiautomator.UiAutomatorFind.findObjectContainsText;

@LargeTest
//@Suppress
@RunWith(AndroidJUnit4.class)
public final class EdjingFreeLibraryAndroidTest {

    @Test
    public void testEdjingFreeNoAds() throws UiObjectNotFoundException {
        getDevice();
        sleep(500);

        pressHome();

        //click("com.ape.launcher:id/app_icon_title");
        openApp(AppSupported.EDJING_FREE);
        sleep(5_500);

        if (findObjectContainsText("commencer","starting").exists()){
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

        //Go to queue and delete items
        click("com.edjing.edjingdjturntable:id/queue_fab");
        while (findObjectById("com.edjing.edjingdjturntable:id/row_current_list_delete_button").exists()) {
            click("com.edjing.edjingdjturntable:id/row_current_list_delete_button");
        }
        pressBack();

        //Go to playlist and delete items
        clickWaitNewWindowContainsText("Playlists");
        while (findObjectById("com.edjing.edjingdjturntable:id/row_playlist_library").exists()) {
            click("com.edjing.edjingdjturntable:id/row_playlist_library_cover");
            findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header").clickTopLeft();

        }


        //Go to tracks
        findObjectContainsText("Titres", "Tracks").click();
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        sleep(1_500);
        if (findObjectContainsText("attente","queue").exists()){
            click("android:id/button1");
        }
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        sleep(1_500);
        pressBack();
        sleep(1_500);
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        sleep(1_500);
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        sleep(1_500);
        pressBack();
        UiObject list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        UiObject child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        sleep(1_500);
        findObjectContainsText("Ajouter à la file d'attente", "Add to the queue").click();
        if (findObjectContainsText("attente","queue").exists()){
            click("android:id/button2");
        }
        sleep(1_500);
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        UiObject list0 = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        UiObject child0 = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        findObjectContainsText("Ajouter à une playlist", "Add to a playlist").click();
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("toto " + UiAutomator.getCurrentDateString());
        click("android:id/button1");


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
