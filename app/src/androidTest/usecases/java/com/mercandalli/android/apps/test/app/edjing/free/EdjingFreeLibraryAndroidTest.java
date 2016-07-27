package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

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


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@LargeTest
//@Suppress
@RunWith(AndroidJUnit4.class)
public final class EdjingFreeLibraryAndroidTest {


    private static final String TAG = "EdjingFreeTest";



    private static boolean mIsEnterToApp=false;

    @Before
    public void returnToMainView() throws UiObjectNotFoundException {
        // TODO: 27/07/2016 voir avec Jo pour l'initialiser autre part
        if (!mIsEnterToApp){
            Log.d(TAG, "returnToMainView: mIsEnterToApp");
            mIsEnterToApp=true;
            getDevice();
            sleep(500);
            pressHome();
            openApp(AppSupported.EDJING_FREE);
            sleep(3_500);
            //// TODO: 27/07/2016 permission for M
            if (findObjectContainsText("commencer", "starting").exists()) {
                click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
            }
        }
        sleep(5_500);
        Log.d(TAG, "returnToMainView: start");
        while (!findObjectById("com.edjing.edjingdjturntable:id/settings_button_automix").exists()){
            Log.d(TAG, "returnToMainView: pressback");
            if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
                Log.d(TAG, "returnToMainView: checkQuit");
                click("android:id/button2");
                break;
            }
            pressBack();
            sleep(3_500);
        }

        manageAds();
    }

    private void manageAds() throws UiObjectNotFoundException {

        Log.d(TAG, "manageAds");
        //Waiting for ads display
        sleep(5_500);
        takeScreenShot(AppSupported.EDJING_FREE, "Starting " + getCurrentDateString());
        //Go back to principal UI
        pressBack();

        if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
            click("android:id/button2");
        }
    }



    @Test
    public void testEdjingFreeNoAds_02_GotoLib() throws UiObjectNotFoundException {

        Log.d(TAG, "gotoLib");
        //Go to library
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");


    }

    @Test
    public void testEdjingFreeNoAds_03_GotoLocal() throws UiObjectNotFoundException {

        Log.d(TAG, "gotoLocal");
        gotoLocal();
        takeScreenShot(AppSupported.EDJING_FREE, "Tracks " + getCurrentDateString());
    }

    private void gotoLocal()throws UiObjectNotFoundException {
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");
        sleep(1_500);
        Log.d(TAG, "clickDeckA");

        //Go to Local

        if (findObjectContainsText("Local").exists()) {
            findObjectContainsText("Titres", "Tracks").click();
        } else {
            findObjectById("com.edjing.edjingdjturntable:id/library_frame").clickTopLeft();
            clickContainsText("Local");
        }
    }

    @Test
    public void testEdjingFreeNoAds_04_DeleteQueue() throws UiObjectNotFoundException {

        gotoLocal();
        Log.d(TAG, "deleteQueue");
        //Go to queue and delete items
        click("com.edjing.edjingdjturntable:id/queue_fab");
        takeScreenShot(AppSupported.EDJING_FREE, "Queue " + getCurrentDateString());
        while (findObjectById("com.edjing.edjingdjturntable:id/row_current_list_delete_button").exists()) {
            click("com.edjing.edjingdjturntable:id/row_current_list_delete_button");
        }
    }

    @Test
    public void testEdjingFreeNoAds_05_DeletePlaylist() throws UiObjectNotFoundException {

        gotoLocal();
        Log.d(TAG, "deletePlaylist");

        //Go to playlist and delete items
        clickWaitNewWindowContainsText("Playlists");
        takeScreenShot(AppSupported.EDJING_FREE, "Playlist " + getCurrentDateString());
        while (findObjectById("com.edjing.edjingdjturntable:id/row_playlist_library").exists()) {
            click("com.edjing.edjingdjturntable:id/row_playlist_library_cover");
            clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
            clickWaitNewWindowContainsText("Delete", "Supprimer");
            click("android:id/button1");
        }
    }
    @Test
    public void testEdjingFreeNoAds_06_DeleteMixes() throws UiObjectNotFoundException {

        gotoLocal();
        Log.d(TAG, "deleteMixes");

        //Go to mixes and delete items
        clickWaitNewWindowContainsText("Mixes");
        takeScreenShot(AppSupported.EDJING_FREE, "Mixes " + getCurrentDateString());
        while (findObjectById("com.edjing.edjingdjturntable:id/row_mix_library").exists()) {
            findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
            clickWaitNewWindowContainsText("Edition","Édition");
            click("com.edjing.edjingdjturntable:id/deleteButton");
            click("android:id/button1");
        }
    }


    @Test
    public void testEdjingFreeNoAds_08_AddTracktoQueuefromCover() throws UiObjectNotFoundException {


        gotoLocal();
        Log.d(TAG, "addTrackToQueueFromCover");

        //Add a track to the queue by clicking on a cover
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        takeScreenShot(AppSupported.EDJING_FREE, "Add to queue " + getCurrentDateString());
        sleep(1_500);
        if (findObjectContainsText("attente", "queue").exists()) {
            click("android:id/button1");
        }
    }

    @Test
    public void testEdjingFreeNoAds_09_AddTracktoQueuefromMultiselectionMenu() throws UiObjectNotFoundException {

        gotoLocal();
        Log.d(TAG, "addTracktoQueuefromMultiselectionMenu");

        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        //Add a track to the queue by clicking on the multiselection tick
        click("com.edjing.edjingdjturntable:id/row_track_library_overflow_button");
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        takeScreenShot(AppSupported.EDJING_FREE, "Add to queue " + getCurrentDateString());
    }

    @Test
    public void testEdjingFreeNoAds_10_AddTracktoQueuefromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        Log.d(TAG, "addTrackToQueueFromContextualMenu");

        UiObject list;
        UiObject child;

        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        if (findObjectById("com.edjing.edjingdjturntable:id/selection_toolbar_nb_selected_items").exists()){
            pressBack();
        }
        //Add a track to the queue by clicking on the contextual menu
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        Log.d(TAG, "clickOnContextualMenu");
        sleep(1_500);
        findObjectContainsText("attente", "Add to the queue").click();
        if (findObjectContainsText("attente", "queue").exists()) {
            click("android:id/button2");
        }
    }

    @Test
    public void testEdjingFreeNoAds_11_AddTracktoPlaylistfromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        Log.d(TAG, "addTrackToPlaylistFromContextualMenu");

        UiObject list;
        UiObject child;

        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        if (findObjectById("com.edjing.edjingdjturntable:id/selection_toolbar_nb_selected_items").exists()){
            pressBack();
        }
        //Add a track to a playlist by clicking on the contextual menu
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 3));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        Log.d(TAG, "clickOnContextualMenu");
        sleep(1_500);
        findObjectContainsText("Ajouter à une playlist","Add to a playlist").click();

        //Create playlist
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("riri " + getCurrentDateString());
        click("android:id/button1");
    }
    @Test
    public void testEdjingFreeNoAds_13_GotoArtists() throws UiObjectNotFoundException {

        gotoLocal();

        //Go to artist tab
        findObjectContainsText("Artistes", "Artists").click();

    }
    @Test
    public void testEdjingFreeNoAds_14_AddArtisttoQueue() throws UiObjectNotFoundException {

        gotoLocal();
        UiObject list;
        UiObject child;

        //Add an artist to the queue
        findObjectContainsText("Artistes", "Artists").click();
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_artist_library_cover")).click();
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_artist_clipping_header"));
        findObjectContainsText("file d'attente", "Add all apparent tracks to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        pressBack();

    }

    @Test
    public void testEdjingFreeNoAds_15_AddanArtisttoaPlaylist() throws UiObjectNotFoundException {

        gotoLocal();
        UiObject list;
        UiObject child;

        //Add an artist to a playlist
        findObjectContainsText("Artistes", "Artists").click();
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_artist_library_cover")).click();
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_artist_clipping_header"));
        findObjectContainsText("Ajouter à une playlist","Add to playlist").click();
        sleep(1_500);
        click("android:id/button1");
        pressBack();

    }

    @Test
    public void testEdjingFreeNoAds_16_GotoAlbums() throws UiObjectNotFoundException {

        gotoLocal();

        //Go to albums tab
        clickWaitNewWindowContainsText("Albums");
    }

    @Test
    public void testEdjingFreeNoAds_17_AddAlbumToQueue() throws UiObjectNotFoundException {

        gotoLocal();
        insideAlbum();

        //Add an album to the queue by clicking on the contextual menu
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
        findObjectContainsText("file d'attente", "to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        pressBack();

    }


    private void insideAlbum () throws UiObjectNotFoundException {
        UiObject list;
        UiObject child;
        clickWaitNewWindowContainsText("Albums");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_album_library_cover")).click();
    }


    @Test
    public void testEdjingFreeNoAds_18_AddAlbumToExistingPlaylistFromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        insideAlbum();

        //Add an album to an existing playlist by clicking on the contextual menu
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
        findObjectContainsText("Ajouter à une playlist","Add to a playlist").click();
        click("android:id/button1");
        pressBack();
    }

    @Test
    public void testEdjingFreeNoAds_19_AddAlbumToExistingPlaylistFromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        insideAlbum();

        //Add an album to a playlist by clicking on the contextual menu
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
        findObjectContainsText("Ajouter à une playlist", "Add to playlist").click();
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("fifi " + getCurrentDateString());
        click("android:id/button1");
        pressBack();

    }

    @Test
    public void testEdjingFreeNoAds_20_GotoPlaylist() throws UiObjectNotFoundException {


        //Go to playlists tab
        clickWaitNewWindowContainsText("Playlists");

    }

    @Test
    public void testEdjingFreeNoAds_21_AddaPlaylistToQueue() throws UiObjectNotFoundException {

        UiObject list;
        UiObject child;
        gotoLocal();
        insidePlaylist();

        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
        findObjectContainsText("file d'attente", "Add all apparent tracks to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
    }

    private void insidePlaylist () throws UiObjectNotFoundException{
        UiObject list;
        UiObject child;

        clickWaitNewWindowContainsText("Playlists");
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_playlist_library_cover")).click();
    }


    @Test
    public void testEdjingFreeNoAds_22_EditaPlaylist() throws UiObjectNotFoundException {

        clickWaitNewWindowContainsText("Playlists");
        click("com.edjing.edjingdjturntable:id/row_playlist_library_cover");
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
        findObjectContainsText("Rennomer", "Edit").click();
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("ririlerelou " + getCurrentDateString());
        click("android:id/button1");
        pressBack();

    }

    @Test
    public void testEdjingFreeNoAds_23_GotoQueue() throws UiObjectNotFoundException {

        gotoLocal();
        click("com.edjing.edjingdjturntable:id/queue_fab");

    }

    @Test
    public void testEdjingFreeNoAds_24_AddQueuetoaNewPlaylist() throws UiObjectNotFoundException {


        gotoLocal();
        click("com.edjing.edjingdjturntable:id/queue_fab");
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/view_current_setlist_list_view"));
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/view_current_setlist_list_view"));
        clickTopRight(findObjectById("android:id/content"));
        findObjectContainsText("playlist").click();
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("loulou " + getCurrentDateString());
        click("android:id/button1");

    }

    @Test
    public void testEdjingFreeNoAds_25_LaunchAutomix() throws UiObjectNotFoundException {

        gotoLocal();
        //Launch automix
        click("com.edjing.edjingdjturntable:id/automix_fab");

    }

    @Test
    public void testEdjingFreeNoAds_26_WaitForAds() throws UiObjectNotFoundException {

        gotoLocal();
        //Launch automix
        click("com.edjing.edjingdjturntable:id/automix_fab");
        //Waiting for ads
        sleep(2_500);
        pressBack();
        if (findObjectContainsText("vous", "want").exists()) {
            sleep(1_500);
            click("android:id/button2");
        }
        sleep(5_000);

    }

    @Test
    public void testEdjingFreeNoAds_27_QuitAutomix() throws UiObjectNotFoundException {

        //Quit automix
        pressBack();
        if (findObjectContainsText("vous", "want").exists()) {
            sleep(1_500);
            click("android:id/button1");
            sleep(1_500);
        }

    }

    @Test
    public void testEdjingFreeNoAds_28_StartRec() throws UiObjectNotFoundException {

        //Start record
        click("com.edjing.edjingdjturntable:id/platine_top_menu_record_flash");
    }

    @Test
    public void testEdjingFreeNoAds_29_LaunchAutomix() throws UiObjectNotFoundException {

        //Launch automix
        click("com.edjing.edjingdjturntable:id/cover_deck_b");
        click("com.edjing.edjingdjturntable:id/queue_fab");
        click("com.edjing.edjingdjturntable:id/automix_fab");
        sleep(120_000);
    }

    @Test
    public void testEdjingFreeNoAds_30_GoBacktotheMainUI() throws UiObjectNotFoundException {


        //Go back to the main UI
        pressBack();
        click("android:id/button1");

    }

    @Test
    public void testEdjingFreeNoAds_31_WaitForAds() throws UiObjectNotFoundException {


        //Waiting for ads
        sleep(2_500);
        pressBack();
        if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
            click("android:id/button2");
        }
    }

    @Test
    public void testEdjingFreeNoAds_32_StopRecord() throws UiObjectNotFoundException {

        //Stop the record
        click("com.edjing.edjingdjturntable:id/platine_top_menu_record_flash");
        sleep(6_000);
        findObjectById("com.edjing.edjingdjturntable:id/mixTitleEditText").setText("ð/ĳµ…}ù_~⌨ " + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/mixArtistNameEditText").setText("ð/ẞĳµ…}ù_~⌨ " + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/mixTagsEditText").setText("ð/♦,☺ĳµ…}ù_~⌨ " + getCurrentDateString());
        click("com.edjing.edjingdjturntable:id/backButton");

    }

    @Test
    public void testEdjingFreeNoAds_33_GotoMixes() throws UiObjectNotFoundException {

        //Go to Mixes tab
        click("com.edjing.edjingdjturntable:id/cover_deck_a");
        findObjectContainsText("Mixes").click();

    }

    @Test
    public void testEdjingFreeNoAds_34_ShareMixLink() throws UiObjectNotFoundException {


        findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
        clickWaitNewWindowContainsText("Share link", "Partager le lien");
        if (findObjectContainsText("internet").exists()) {
            click("android:id/button1");
        } else {
            clickWaitNewWindowContainsText("Gmail");
            sleep(40_000);

            if (findObjectById("com.google.android.gm:id/to").exists()) {
                findObjectById("com.google.android.gm:id/to").setText("adrien.larus@djit.fr");
                click("com.google.android.gm:id/send");
            }

            click("com.edjing.edjingdjturntable:id/dialog_cancel");
            pressBack();

        }

    }

    @Test
    public void testEdjingFreeNoAds_35_ShareMixMp3() throws UiObjectNotFoundException {

            findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
            clickWaitNewWindowContainsText("Share mp3", "Partager le mp3");
            if (findObjectContainsText("internet").exists()) {
                click("android:id/button1");
            } else {
                clickWaitNewWindowContainsText("Gmail");
                sleep(30_000);

                if (findObjectById("com.google.android.gm:id/to").exists()) {
                    findObjectById("com.google.android.gm:id/to").setText("adrien.larus@djit.fr");
                    click("com.google.android.gm:id/send");
                }
                click("com.edjing.edjingdjturntable:id/dialog_cancel");
                pressBack();

                }
        }
    @Test
    public void testEdjingFreeNoAds_36_GotoMainUI() throws UiObjectNotFoundException {

        //Go back to the main UI
        findObjectById("com.edjing.edjingdjturntable:id/library_frame").clickTopLeft();
        clickContainsText("Come back to the Deck view","platines");
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
