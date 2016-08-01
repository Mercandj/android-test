package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.After;
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



    private static boolean sIsEnterToApp=false;

    @Before
    public void returnToMainView() throws UiObjectNotFoundException {
        // TODO: 27/07/2016 voir avec Jo pour l'initialiser autre part
        if (!sIsEnterToApp){
            Log.d(TAG, "returnToMainView: sIsEnterToApp");
            sIsEnterToApp=true;
            getDevice();
            sleep(500);
            pressHome();
            openApp(AppSupported.EDJING_FREE);
            sleep(3_500);
            //// TODO: 27/07/2016 permission for M
            if (findObjectContainsText("commencer", "starting").exists()) {
                click("com.edjing.edjingdjturntable:id/btn_mix_right_now");
                Log.d(TAG, "returnToMainView: Tuto");
            }
        }
        openApp(AppSupported.EDJING_FREE);
        sleep(5_500);
        Log.d(TAG, "returnToMainView: start");

        //Activity currentActivity = Tests.getCurrentActivity();
        //String activityName = currentActivity == null ? "null" : currentActivity.getLocalClassName();
        //Log.d("debug_thursday_vb", "returnToMainView: " + activityName);

        while (!findObjectById("com.edjing.edjingdjturntable:id/settings_button_automix").exists()){
            Log.d(TAG, "returnToMainView: AdsPressBack");
            if (findObjectContainsText("Êtes-vous sur de vouloir quitter l'application?", "Do you really want to shut down the app?").exists()) {
                Log.d(TAG, "returnToMainView: checkQuit");
                click("android:id/button2");
                break;
            }
            pressBack();
            sleep(3_500);
        }

        //manageAds();
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

    @After
    public void resetAppAfter() throws UiObjectNotFoundException {
        // TODO: 01/08/2016 utiliser le texte

        while (!findObjectById("com.edjing.edjingdjturntable:id/alertTitle").exists()) {
            Log.d(TAG, "resetAppAfter: pressback");
            pressBack();
            sleep(3_500);
        }
        click("android:id/button1");
        pressBack();
        if(findObjectById("com.edjing.edjingdjturntable:id/alertTitle").exists()){
            Log.d(TAG, "resetAppAfter: FromAutomix");
            click("android:id/button1");
        }
        //resetApp(InstrumentationRegistry.getTargetContext());




    }


    @Test
    public void testEdjingFreeNoAds_02_GotoLib() throws UiObjectNotFoundException {

        //Go to library
        click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");
        Log.d(TAG, "testEdjingFreeNoAds_02_GotoLib: IntoLib");

    }

    @Test
    public void testEdjingFreeNoAds_03_GotoLocal() throws UiObjectNotFoundException {

        gotoLocal();
        takeScreenShot(AppSupported.EDJING_FREE, "Tracks " + getCurrentDateString());
        Log.d(TAG, "testEdjingFreeNoAds_03_GotoLocal: IntoLocal");
    }

    private void gotoLocal()throws UiObjectNotFoundException {
        if (findObjectById("com.edjing.edjingdjturntable:id/vinyl_view_disc").exists()){
            click("com.edjing.edjingdjturntable:id/platine_menu_top_cover_deckA");
            Log.d(TAG, "gotoLocal: clickHasBeenDoneOnLibCoverA");
        }else{
            click("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA");
            Log.d(TAG, "gotoLocal: clickHasBeenDoneOnPlayA");
        }
        sleep(1_500);

        //Go to Local

        if (findObjectContainsText("Local").exists()) {
            findObjectById("com.edjing.edjingdjturntable:id/fragment_music_source_pager_tabs").clickTopLeft();
        } else {
            findObjectById("com.edjing.edjingdjturntable:id/library_frame").clickTopLeft();
            clickContainsText("Local");
        }
    }

    @Test
    public void testEdjingFreeNoAds_04_LoadTrack() throws UiObjectNotFoundException {

        gotoLocal();
        loadTrackA();
    }

    private void loadTrackA () throws UiObjectNotFoundException{
        findObjectById("com.edjing.edjingdjturntable:id/row_track_library_title").click();
        sleep(2_500);
        takeScreenShot(AppSupported.EDJING_FREE,"TrackLoaded" + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA").click();
        Log.d(TAG, "loadTrack: TrackIsPlaying");
        takeScreenShot(AppSupported.EDJING_FREE,"TrackIsPlaying" + getCurrentDateString());
    }

    @Test
    public void testEdjingFreeNoAds_05_DeleteQueue() throws UiObjectNotFoundException {

        gotoLocal();
        gotoQueue();
        deleteQueueItems();
    }

    private void deleteQueueItems()throws UiObjectNotFoundException{

        while (findObjectById("com.edjing.edjingdjturntable:id/row_current_list_delete_button").exists()) {
            click("com.edjing.edjingdjturntable:id/row_current_list_delete_button");
            Log.d(TAG, "testEdjingFreeNoAds_05_DeleteQueue: TrackIntoQueueDeleted");
        }
        Log.d(TAG, "testEdjingFreeNoAds_05_DeleteQueue: QueueEmpty");
        takeScreenShot(AppSupported.EDJING_FREE,"QueueEmpty");
    }

    @Test
    public void testEdjingFreeNoAds_06_DeletePlaylist() throws UiObjectNotFoundException {

        gotoLocal();
        gotoPlaylistsList();

        while (findObjectById("com.edjing.edjingdjturntable:id/row_playlist_library").exists()) {
            gotoPlaylist();
            clickOnContxtMenuPlaylist();
            deletePlaylist();
        }
        Log.d(TAG, "testEdjingFreeNoAds_06_DeletePlaylist: PlaylistListEmpty");
        takeScreenShot(AppSupported.EDJING_FREE,"PlaylistListEmpty"+getCurrentDateString());
        pressBack();
    }

    private void gotoPlaylist()throws UiObjectNotFoundException{
        click("com.edjing.edjingdjturntable:id/row_playlist_library_cover");
        Log.d(TAG, "gotoPlaylist: PlaylistView");
        takeScreenShot(AppSupported.EDJING_FREE,"Playlist "+getCurrentDateString());
    }

    private void deletePlaylist()throws UiObjectNotFoundException{
        clickWaitNewWindowContainsText("Delete", "Supprimer");
        click("android:id/button1");
        Log.d(TAG, "deletePlaylist: PlaylistDeleted");
        takeScreenShot(AppSupported.EDJING_FREE,"PlaylistDeleted "+getCurrentDateString());
    }

    @Test
    public void testEdjingFreeNoAds_07_DeleteMixes() throws UiObjectNotFoundException {

        gotoLocal();
        gotoMixes();

        while (findObjectById("com.edjing.edjingdjturntable:id/row_mix_library").exists()) {
            clickOnContxtMenuMix();
            deleteMixes();
        }
        takeScreenShot(AppSupported.EDJING_FREE, "emptyMixes " + getCurrentDateString());
        Log.d(TAG, "testEdjingFreeNoAds_07_DeleteMixes: emptyMixList");
    }

    private void gotoMixes ()throws UiObjectNotFoundException{
        clickWaitNewWindowContainsText("Mixes");
        takeScreenShot(AppSupported.EDJING_FREE, "Mixes " + getCurrentDateString());
        Log.d(TAG, "gotoMixes: MixList");
    }
    private void clickOnContxtMenuMix()throws UiObjectNotFoundException{
        findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
        takeScreenShot(AppSupported.EDJING_FREE, "ContextualMenu " + getCurrentDateString());
        Log.d(TAG, "clickOnContxtMenuMix: contextualMenuAppears");
    }
    private void deleteMixes ()throws UiObjectNotFoundException{
        clickWaitNewWindowContainsText("Edition","Édition");
        click("com.edjing.edjingdjturntable:id/deleteButton");
        click("android:id/button1");
        Log.d(TAG, "deleteMixes: MixDeleted");
    }
    @Test
    public void testEdjingFreeNoAds_08_AddTracktoQueuefromCover() throws UiObjectNotFoundException {


        gotoLocal();

        //Add a track to the queue by clicking on a cover
        click("com.edjing.edjingdjturntable:id/row_track_library_cover");
        takeScreenShot(AppSupported.EDJING_FREE, "Add to queue " + getCurrentDateString());
        sleep(1_500);
        Log.d(TAG, "testEdjingFreeNoAds_08_AddTracktoQueuefromCover: TrackAddedToQueue");
        if (findObjectContainsText("attente", "queue").exists()) {
            click("android:id/button1");
        }
    }

    @Test
    public void testEdjingFreeNoAds_09_AddTracktoQueuefromMultiselectionMenu() throws UiObjectNotFoundException {

        gotoLocal();
        moveIntoList();


        takeScreenShot(AppSupported.EDJING_FREE,"MultiSelection");
        //Add a track to the queue by clicking on the multiselection tick
        click("com.edjing.edjingdjturntable:id/row_track_library_overflow_button");
        Log.d(TAG, "testEdjingFreeNoAds_09_AddTracktoQueuefromMultiselectionMenu: ClickOnContextualMenu");
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        takeScreenShot(AppSupported.EDJING_FREE, "Add to queue " + getCurrentDateString());
    }

    @Test
    public void testEdjingFreeNoAds_10_AddTracktoQueuefromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        moveIntoList();

        UiObject list;
        UiObject child;



        if (findObjectById("com.edjing.edjingdjturntable:id/selection_toolbar_nb_selected_items").exists()){
            Log.d(TAG, "testEdjingFreeNoAds_10_AddTracktoQueuefromContextualMenu: ToolbarExists");
            pressBack();
        }
        //Add a track to the queue by clicking on the contextual menu
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        Log.d(TAG, "testEdjingFreeNoAds_10_AddTracktoQueuefromContextualMenu: clickOnContextualMenu");
        sleep(1_500);
        findObjectContainsText("attente", "Add to the queue").click();
        if (findObjectContainsText("attente", "queue").exists()) {
            click("android:id/button2");
        }
        Log.d(TAG, "testEdjingFreeNoAds_10_AddTracktoQueuefromContextualMenu: TrackAddedToQueue");
    }

    @Test
    public void testEdjingFreeNoAds_11_AddTracktoPlaylistfromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        moveIntoList();

        if (findObjectById("com.edjing.edjingdjturntable:id/selection_toolbar_nb_selected_items").exists()){
            pressBack();
        }
        clickOnContxtMenuTrack();
        addToPlaylistFroContxtMenu();
        createNewPlaylistRiri();

    }

    private void createNewPlaylistRiri()throws UiObjectNotFoundException{
        click("android:id/button2");
        Log.d(TAG, "createNewPlaylistRiri: CreateButtonClicked");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("riri " + getCurrentDateString());
        click("android:id/button1");
        Log.d(TAG, "createNewPlaylistRiri: Created");
    }
    private void clickOnContxtMenuTrack()throws UiObjectNotFoundException{
        UiObject list;
        UiObject child;
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 3));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_track_library_overflow_button")).clickTopLeft();
        Log.d(TAG, "clickOnContxtMenuTrack: ClickOnContextualMenu");
        sleep(1_500);
    }
    @Test
    public void testEdjingFreeNoAds_12_GotoArtists() throws UiObjectNotFoundException {

        gotoLocal();
        gotoArtists();

    }

    private void gotoArtists()throws UiObjectNotFoundException{
        findObjectContainsText("Artistes", "Artists").click();
        Log.d(TAG, "gotoArtists: ArtistTab");
    }
    @Test
    public void testEdjingFreeNoAds_13_AddArtistToQueue() throws UiObjectNotFoundException {

        gotoLocal();
        gotoArtists();
        moveIntoList();
        gotoArtistView();
        clickOnContxtMenuArtist();
        addToQueueFromContxtMenu();
        pressBack();

    }

    private void gotoArtistView ()throws UiObjectNotFoundException{
        UiObject list;
        UiObject child;
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_artist_library_cover")).click();
        Log.d(TAG, "gotoArtistView: ArtistView");
        takeScreenShot(AppSupported.EDJING_FREE,"ArtistView " +getCurrentDateString());
    }

    private void clickOnContxtMenuArtist()throws UiObjectNotFoundException{
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_artist_clipping_header"));
        Log.d(TAG, "clickOnContxtMenuArtist: ContextualMenuAppears");
        takeScreenShot(AppSupported.EDJING_FREE,"ContextualView "+getCurrentDateString());
    }

    private void addToQueueFromContxtMenu()throws UiObjectNotFoundException{
        findObjectContainsText("file d'attente", "Add all apparent tracks to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
    }

    @Test
    public void testEdjingFreeNoAds_14_AddArtistToPlaylist() throws UiObjectNotFoundException {

        gotoLocal();
        gotoArtists();
        gotoArtistView();
        clickOnContxtMenuArtist();
        addArtistToPlaylist();
    }

    private void addArtistToPlaylist()throws UiObjectNotFoundException{
        findObjectContainsText("Ajouter à une playlist","Add to playlist").click();
        Log.d(TAG, "addArtistToPlaylist: AddARtistToPlaylist");
        takeScreenShot(AppSupported.EDJING_FREE,"AddArtistToPlaylist "+getCurrentDateString());
        sleep(1_500);
        click("android:id/button1");
        pressBack();
    }

    @Test
    public void testEdjingFreeNoAds_15_GotoAlbums() throws UiObjectNotFoundException {

        gotoLocal();
        gotoAlbumsList();

    }

    private void gotoAlbumsList () throws UiObjectNotFoundException{
        clickWaitNewWindowContainsText("Albums");
    }

    @Test
    public void testEdjingFreeNoAds_16_AddAlbumToQueue() throws UiObjectNotFoundException {

        gotoLocal();
        gotoAlbumsList();
        moveIntoList();
        gotoAlbumView();
        clickOnContxtMenuAlbum();
        addAlbumToQueue();
    }

    private void moveIntoList()throws UiObjectNotFoundException{
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        Log.d(TAG, "moveIntoList: dragBottom");
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll"));
        Log.d(TAG, "moveIntoList: dragTop");
    }

    private void gotoAlbumView () throws UiObjectNotFoundException {
        UiObject list;
        UiObject child;
        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_album_library_cover")).click();
        Log.d(TAG, "gotoAlbumView: AlbumClicked");
    }
    private void clickOnContxtMenuAlbum()throws UiObjectNotFoundException{
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
    }

    private void addAlbumToQueue()throws UiObjectNotFoundException{
        Log.d(TAG, "addAlbumToQueue: addAlbumToQueue");
        findObjectContainsText("file d'attente", "to the queue").click();
        takeScreenShot(AppSupported.EDJING_FREE,"addAlbumToQueue "+getCurrentDateString());
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        pressBack();
    }


    @Test
    public void testEdjingFreeNoAds_17_AddAlbumToExistingPlaylistFromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        gotoAlbumsList();
        gotoAlbumView();
        clickOnContxtMenuAlbum();
        addAlbumToPlaylist();

    }


    private void addAlbumToPlaylist ()throws UiObjectNotFoundException{
        findObjectContainsText("Ajouter à une playlist","Add to playlist").click();
        Log.d(TAG, "addAlbumToPlaylist: ClickOnExistingPlaylist");
        takeScreenShot(AppSupported.EDJING_FREE,"ClickOnExistingPlaylist "+getCurrentDateString());
        click("android:id/button1");
        pressBack();
    }

    @Test
    public void testEdjingFreeNoAds_18_AddAlbumToExistingPlaylistFromContextualMenu() throws UiObjectNotFoundException {

        gotoLocal();
        gotoAlbumsList();
        gotoAlbumView();

        //Add an album to a playlist by clicking on the contextual menu
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_album_clipping_header"));
        findObjectContainsText("Ajouter à une playlist", "Add to playlist").click();
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("fifi " + getCurrentDateString());
        click("android:id/button1");
        pressBack();

    }

    @Test
    public void testEdjingFreeNoAds_19_GotoPlaylist() throws UiObjectNotFoundException {

        gotoLocal();
        gotoPlaylistsList();

    }

    private void gotoPlaylistsList ()throws UiObjectNotFoundException{
        clickWaitNewWindowContainsText("Playlists");
        takeScreenShot(AppSupported.EDJING_FREE, "Playlist " + getCurrentDateString());
    }

    @Test
    public void testEdjingFreeNoAds_20_AddPlaylistToQueue() throws UiObjectNotFoundException {

        gotoLocal();
        gotoPlaylistsList();
        gotoPlaylistView();

        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
        findObjectContainsText("file d'attente", "Add all apparent tracks to the queue").click();
        click("com.edjing.edjingdjturntable:id/dialog_add_all_ok");
        pressBack();
    }
    //Doesn't take the first item
    private void gotoPlaylistView() throws UiObjectNotFoundException{
        UiObject list;
        UiObject child;

        list = findObjectById("com.edjing.edjingdjturntable:id/list_fast_scroll_list");
        child = list.getChild(new UiSelector().index(list.getChildCount() - 2));
        child.getChild(new UiSelector().resourceId("com.edjing.edjingdjturntable:id/row_playlist_library_cover")).click();
    }


    @Test
    public void testEdjingFreeNoAds_21_EditPlaylist() throws UiObjectNotFoundException {

        gotoLocal();
        gotoPlaylistsList();
        gotoPlaylist();
        clickOnContxtMenuPlaylist();


        findObjectContainsText("Modifier", "Edit").click();
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("ririlerelou " + getCurrentDateString());
        click("android:id/button1");
        pressBack();

    }

    @Test
    public void testEdjingFreeNoAds_22_GotoQueue() throws UiObjectNotFoundException {

        gotoLocal();
        gotoQueue();

    }

    @Test
    public void testEdjingFreeNoAds_23_AddQueueToNewPlaylist() throws UiObjectNotFoundException {

        gotoLocal();
        gotoQueue();
        moveIntoPlaylistList();
        clickOnContxtMenuPlaylist();
        addToPlaylistFroContxtMenu();
        createNewPlaylistLoulou();

    }
    private void gotoQueue () throws UiObjectNotFoundException {
        click("com.edjing.edjingdjturntable:id/queue_fab");
        takeScreenShot(AppSupported.EDJING_FREE,"QueueView " + getCurrentDateString());
        Log.d(TAG, "gotoQueue: QueueView");
    }

    private void moveIntoPlaylistList () throws UiObjectNotFoundException {
        dragBottomList(findObjectById("com.edjing.edjingdjturntable:id/view_current_setlist_list_view"));
        Log.d(TAG, "moveIntoList: dragBottom");
        dragTopList(findObjectById("com.edjing.edjingdjturntable:id/view_current_setlist_list_view"));
        Log.d(TAG, "moveIntoList: dragTop");
    }
    private void clickOnContxtMenuPlaylist () throws UiObjectNotFoundException {
        clickTopRight(findObjectById("com.edjing.edjingdjturntable:id/activity_playlist_clipping_header"));
        Log.d(TAG, "clickOnContxtMenuPlaylist: contextualMenuAppears");

    }
    private void addToPlaylistFroContxtMenu()throws UiObjectNotFoundException {
        findObjectContainsText("playlist").click();
        Log.d(TAG, "addToPlaylistFroContxtMenu: PlaylistSelected");
    }
    private void createNewPlaylistLoulou ()throws UiObjectNotFoundException {
        click("android:id/button2");
        findObjectById("com.edjing.edjingdjturntable:id/dialog_edit_text_edit_text").setText("loulou " + getCurrentDateString());
        click("android:id/button1");
        Log.d(TAG, "createNewPlaylistLoulou: PlaylistLoulouCreated");
    }


    @Test
    public void testEdjingFreeNoAds_24_Record() throws UiObjectNotFoundException {

        gotoLocal();
        loadTrackA();
        startRec();
        gotoLocal();
        gotoQueue();
        gotoAutomixFromQueue();
        sleep(120_000);
        Log.d(TAG, "testEdjingFreeNoAds_25_LaunchAutomix: AutomixHasBeenPlayed120sec");
        stopAutomix();
        stopRecord();
    }

    private void startRec()throws UiObjectNotFoundException{

        sleep(2_500);
        takeScreenShot(AppSupported.EDJING_FREE,"ReadyToRec");
        Log.d(TAG, "startRec: ReadyToRec");
        click("com.edjing.edjingdjturntable:id/platine_top_menu_record_flash");
        sleep(2_500);
        takeScreenShot(AppSupported.EDJING_FREE,"Recording");
        Log.d(TAG, "startRec: Recording");
    }

    @Test
    public void testEdjingFreeNoAds_25_Automix() throws UiObjectNotFoundException {

        gotoLocal();
        gotoQueue();
        gotoAutomixFromQueue();
        waitingForAutomixAds();
        sleep(20_000);
        Log.d(TAG, "testEdjingFreeNoAds_25_LaunchAutomix: AutomixHasBeenPlayed20sec");
        stopAutomix();



    }
    private void stopAutomix()throws UiObjectNotFoundException{

        while (!findObjectById("com.edjing.edjingdjturntable:id/alertTitle").exists()) {
            pressBack();
            sleep(3_500);
        }
        if(findObjectById("com.edjing.edjingdjturntable:id/alertTitle").exists()) {
            sleep(3_500);
            Log.d(TAG, "stopAutomix:stopAutomixPanel ");
            click("android:id/button1");
            sleep(1_500);
        }
        takeScreenShot(AppSupported.EDJING_FREE,"AutomixEnded");
    }
    private void gotoAutomixFromQueue()throws UiObjectNotFoundException{
        click("com.edjing.edjingdjturntable:id/automix_fab");
        Log.d(TAG, "gotoAutomixFromQueue: Clicked");
    }
    private void waitingForAutomixAds()throws UiObjectNotFoundException{
        sleep(2_500);
        takeScreenShot(AppSupported.EDJING_FREE,"AutomixAds" + getCurrentDateString());
        while (!findObjectById("com.edjing.edjingdjturntable:id/alertTitle").exists()) {
            pressBack();
            sleep(3_500);
        }
        if(findObjectById("com.edjing.edjingdjturntable:id/alertTitle").exists()) {
            sleep(3_500);
            Log.d(TAG, "waitingForAutomixAds: gotoAutomix");
            click("android:id/button2");
            sleep(1_500);
        }
        takeScreenShot(AppSupported.EDJING_FREE,"AutomixView");
    }

    private void stopRecord () throws UiObjectNotFoundException{
        Log.d(TAG, "stopRecord: stopRecordBegin");
        click("com.edjing.edjingdjturntable:id/platine_top_menu_record_flash");
        while (!findObjectById("com.edjing.edjingdjturntable:id/mixTitleEditText").exists()) {
            sleep(10_000);
            Log.d(TAG, "stopRecord: WaitingForAfterRecordPanel");
        }
        takeScreenShot(AppSupported.EDJING_FREE,"AfterRecordAppears" + getCurrentDateString());
        Log.d(TAG, "stopRecord: AfterRecordAppears");
        findObjectById("com.edjing.edjingdjturntable:id/mixTitleEditText").setText("ð/ĳµ…}ù_~⌨ " + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/mixArtistNameEditText").setText("ð/ẞĳµ…}ù_~⌨ " + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/mixTagsEditText").setText("ð/♦,☺ĳµ…}ù_~⌨ " + getCurrentDateString());
        takeScreenShot(AppSupported.EDJING_FREE,"AfterRecordEdition" + getCurrentDateString());
        click("com.edjing.edjingdjturntable:id/backButton");
        takeScreenShot(AppSupported.EDJING_FREE,"stopRecord: RecordDone "+getCurrentDateString());
        Log.d(TAG, "stopRecord: RecordSaved");
    }

    @Test
    public void testEdjingFreeNoAds_27_GotoMixes() throws UiObjectNotFoundException {

        //Go to Mixes tab
        gotoLocal();
        gotoMixes();

    }

    @Test
    public void testEdjingFreeNoAds_28_ShareMixLink() throws UiObjectNotFoundException {

        gotoLocal();
        gotoMixes();
        findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
        clickWaitNewWindowContainsText("Share link", "Partager le lien");
        if (findObjectContainsText("internet").exists()) {
            Log.d(TAG, "testEdjingFreeNoAds_28_ShareMixLink: NoInternet");
            click("android:id/button1");
        } else {
            clickWaitNewWindowContainsText("Gmail");
            sleep(40_000);

            if (findObjectById("com.google.android.gm:id/to").exists()) {
                Log.d(TAG, "testEdjingFreeNoAds_29_ShareMixMp3: emailFieldExists");
                findObjectById("com.google.android.gm:id/to").setText("adrien.larus@djit.fr");
                Log.d(TAG, "testEdjingFreeNoAds_29_ShareMixMp3: emailIsCompleted");

                click("com.google.android.gm:id/send");
            }

            click("com.edjing.edjingdjturntable:id/dialog_cancel");
            Log.d(TAG, "testEdjingFreeNoAds_29_ShareMixMp3: ShareHasBeenCancelled");
            pressBack();

        }

    }

    @Test
    public void testEdjingFreeNoAds_29_ShareMixMp3() throws UiObjectNotFoundException {

        gotoLocal();
        gotoMixes();
        findObjectById("com.edjing.edjingdjturntable:id/row_mix_library_overflow_button").clickTopLeft();
        clickWaitNewWindowContainsText("Share mp3", "Partager le mp3");
        if (findObjectContainsText("internet").exists()) {
            click("android:id/button1");
        } else {
            clickWaitNewWindowContainsText("Gmail");
            sleep(30_000);

            if (findObjectById("com.google.android.gm:id/to").exists()) {
                Log.d(TAG, "testEdjingFreeNoAds_29_ShareMixMp3: emailFieldExists");
                findObjectById("com.google.android.gm:id/to").setText("adrien.larus@djit.fr");
                Log.d(TAG, "testEdjingFreeNoAds_29_ShareMixMp3: emailIsCompleted");
                click("com.google.android.gm:id/send");
            }
            click("com.edjing.edjingdjturntable:id/dialog_cancel");
            Log.d(TAG, "testEdjingFreeNoAds_29_ShareMixMp3: ShareHasBeenCancelled");
            pressBack();

            }
    }
    @Test
    public void testEdjingFreeNoAds_30_GotoMainUI() throws UiObjectNotFoundException {


        }

        //findObjectContainsText("com.edjing.edjingdjturntable:id/list_fast_scroll_list").swipeUp(100);




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
