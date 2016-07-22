package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.UiAutomator.dragBottomList;
import static android.support.test.uiautomator.UiAutomator.dragTopList;
import static android.support.test.uiautomator.UiAutomator.getCurrentDateString;
import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.openApp;
import static android.support.test.uiautomator.UiAutomator.pressBack;
import static android.support.test.uiautomator.UiAutomator.pressHome;
import static android.support.test.uiautomator.UiAutomator.sleep;
import static android.support.test.uiautomator.UiAutomator.takeScreenShot;
import static android.support.test.uiautomator.UiAutomatorClick.click;
import static android.support.test.uiautomator.UiAutomatorClick.clickContainsText;
import static android.support.test.uiautomator.UiAutomatorClick.clickTopRight;
import static android.support.test.uiautomator.UiAutomatorClick.clickWaitNewWindowContainsText;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectById;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectContainsText;

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

        if (findObjectContainsText("commencer", "starting").exists()) {
            click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
        } else {
            //Waiting for ads display
            sleep(5_500);
            takeScreenShot(AppSupported.EDJING_FREE, "Starting " + getCurrentDateString());
            //Go back to principal UI
            pressBack();

            if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
                click("android:id/button2");
            }
        }

        //Go to library
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");

        //Go to Local

        if (findObjectContainsText("Local").exists()) {
            findObjectContainsText("Titres", "Tracks").click();
            takeScreenShot(AppSupported.EDJING_FREE, "Tracks " + getCurrentDateString());
        }
        else {
            findObjectById("com.edjing.edjingdjturntable:id/library_frame").clickTopLeft();
            clickContainsText("Local");
        }

        //Go to queue and delete items
        click("com.edjing.edjingdjturntable:id/queue_fab");
        takeScreenShot(AppSupported.EDJING_FREE, "Queue " + getCurrentDateString());
        while (findObjectById("com.edjing.edjingdjturntable:id/row_current_list_delete_button").exists()) {
            click("com.edjing.edjingdjturntable:id/row_current_list_delete_button");
        }
        pressBack();

        //Go to playlist and delete items
        clickWaitNewWindowContainsText("Playlists");
        takeScreenShot(AppSupported.EDJING_FREE, "Playlist " + getCurrentDateString());
        while (findObjectById("com.edjing.edjingdjturntable:id/row_playlist_library").exists()) {
            click("com.edjing.edjingdjturntable:id/row_playlist_library_cover");
            clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
            clickWaitNewWindowContainsText("Delete","Supprimer");
            click("android:id/button1");
        }

        //Go to mixes and delete items
        clickWaitNewWindowContainsText("Mixes");
        takeScreenShot(AppSupported.EDJING_FREE, "Mixes " + getCurrentDateString());
        while (findObjectById("com.edjing.edjingdjturntable:id/row_mix_library").exists()) {
            findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
            clickWaitNewWindowContainsText("Edition");
            click("com.edjing.edjingdjturntable:id/deleteButton");
            click("android:id/button1");
        }


        //Go to tracks

        UiObject list;
        UiObject child;

        findObjectContainsText("Titres", "Tracks").click();
        //Add a track to the queue by clicking on a cover
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        takeScreenShot(AppSupported.EDJING_FREE, "Add to queue " + getCurrentDateString());
        sleep(1_500);
        if (findObjectContainsText("attente", "queue").exists()) {
            click("android:id/button1");
        }
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        sleep(1_500);
        pressBack();
        sleep(1_500);

        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        //Add a track to the queue by clicking on the multiselection tick
        click("com.edjing.edjingdjturntable:id/row_track_library_overflow_button");
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");


        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        sleep(1_500);
        pressBack();
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        //Add a track to the queue by clicking on the contextual menu
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        sleep(1_500);
        findObjectContainsText("Ajouter à la file d'attente", "Add to the queue").click();
        if (findObjectContainsText("attente", "queue").exists()) {
            click("android:id/button2");
        }
        sleep(1_500);

        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        //Add a track to a playlist by clicking on the contextual menu
        child = list.getChild(new UiSelector().index(list.getChildCount() - 3));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        findObjectContainsText("Ajouter à une playlist", "Add to a playlist").click();
        //Create playlist
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("riri " + getCurrentDateString());
        click("android:id/button1");

        //Go to artist tab
        findObjectContainsText("Artistes", "Artists").click();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        //Add an artist to the queue
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_artist_library_cover")).click();
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_artist_clipping_header"));
        findObjectContainsText("Ajouter toutes les tracks apparentes à la file d'attente", "Add all apparent tracks to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        //Add an artist to a playlist
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_artist_clipping_header"));
        findObjectContainsText("Ajouter à une playlist", "Add to playlist").click();
        click("android:id/button1");
        pressBack();

        //Go to albums tab
        clickWaitNewWindowContainsText("Albums");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        //Add an album to the queue by clicking on the contextual menu
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_album_library_cover")).click();
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
        findObjectContainsText("Ajouter toutes les tracks apparentes à la file d'attente", "Add all apparent tracks to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        //Add an album to an existing playlist by clicking on the contextual menu
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
        findObjectContainsText("Ajouter à une playlist", "Add to playlist").click();
        click("android:id/button1");
        //Add an album to a playlist by clicking on the contextual menu
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
        findObjectContainsText("Ajouter à une playlist", "Add to playlist").click();
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("fifi " + getCurrentDateString());
        click("android:id/button1");
        pressBack();

        //Go to playlists tab
        clickWaitNewWindowContainsText("Playlists");
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_playlist_library_cover")).click();
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
        findObjectContainsText("Ajouter toutes les tracks apparentes à la file d'attente", "Add all apparent tracks to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        pressBack();
        click("com.edjing.edjingdjturntable:id/row_playlist_library_cover");
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
        findObjectContainsText("Rennomer", "Edit").click();
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("ririlerelou " + getCurrentDateString());
        click("android:id/button1");
        pressBack();
        click("com.edjing.edjingdjturntable:id/queue_fab");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/view_current_setlist_list_view"));
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/view_current_setlist_list_view"));
        clickTopRight(findObjectById("android:id/content"));
        findObjectContainsText("Sauvegarder comme une playlist", "Save as a playlist").click();
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("loulou " + getCurrentDateString());
        click("android:id/button1");
        //Launch automix
        click("com.edjing.edjingdjturntable:id/automix_fab");
        //Waiting for ads
        sleep(2_500);
        pressBack();
        if (findObjectContainsText("vous", "want").exists()) {
            click("android:id/button2");
        }
        sleep(5_000);
        //Quit automix
        pressBack();
        if (findObjectContainsText("vous", "want").exists()) {
            click("android:id/button1");
        }
        //Start record
        click("com.edjing.edjingdjturntable:id/platine_top_menu_record_flash");
        click("com.edjing.edjingdjturntable:id/cover_deck_b");
        click("com.edjing.edjingdjturntable:id/queue_fab");
        //Launch automix
        click("com.edjing.edjingdjturntable:id/automix_fab");
        sleep(120_000);
        //Go back to the main UI
        pressBack();
        click("android:id/button1");
        //Waiting for ads
        sleep(2_500);
        pressBack();
        if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
            click("android:id/button2");
        }
        //Stop the record
        click("com.edjing.edjingdjturntable:id/platine_top_menu_record_flash");
        sleep(6_000);
        findObjectById("com.edjing.edjingdjturntable:id/mixTitleEditText").setText("ð/ĳµ…}ù_~⌨ " + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/mixArtistNameEditText").setText("ð/ẞĳµ…}ù_~⌨ " + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/mixTagsEditText").setText("ð/♦,☺ĳµ…}ù_~⌨ " + getCurrentDateString());
        click("com.edjing.edjingdjturntable:id/backButton");
        click("com.edjing.edjingdjturntable:id/cover_deck_a");
        //Go to Mixes tab
        findObjectContainsText("Mes Mixes", "My Mixes").click();
        findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
        clickWaitNewWindowContainsText("Share link","Partager le lien");
        if(findObjectContainsText("internet").exists()) {
            click("android:id/button1");
        }
        else {
            clickWaitNewWindowContainsText("Gmail");
            sleep(20_000);

            if (findObjectContainsText("Annuler", "Cancel").exists()) {
                click("com.edjing.edjingdjturntable:id/dialog_cancel");
                pressBack();
                findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
                clickWaitNewWindowContainsText("Share mp3", "Partager le mp3");
                clickWaitNewWindowContainsText("Gmail");
                sleep(20_000);
                if (findObjectContainsText("Annuler", "Cancel").exists()) {
                    click("com.edjing.edjingdjturntable:id/dialog_cancel");
                    pressBack();
                }
                findObjectById("com.google.android.gm:id/to").setText("adrien.larus@djit.fr");
                click("com.google.android.gm:id/send");
            }
            findObjectById("com.google.android.gm:id/to").setText("adrien.larus@djit.fr");
            click("com.google.android.gm:id/send");
        }
        //Go back to the main UI
        findObjectById("com.edjing.edjingdjturntable:id/library_frame").clickTopLeft();
        clickContainsText("Come back to the Deck view");
        sleep(2_500);
        pressBack();
        if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
            click("android:id/button2");
        }

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
