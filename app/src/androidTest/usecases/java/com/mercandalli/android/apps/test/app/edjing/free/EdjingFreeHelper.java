package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import com.mercandalli.android.apps.test.app.AppSupported;

import static android.support.test.uiautomator.UiAutomator.getCurrentDateString;
import static android.support.test.uiautomator.UiAutomator.pressBack;
import static android.support.test.uiautomator.UiAutomator.sleep;
import static android.support.test.uiautomator.UiAutomator.takeScreenShot;
import static android.support.test.uiautomator.UiAutomatorClick.click;
import static android.support.test.uiautomator.UiAutomatorClick.clickContainsText;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectById;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectContainsText;

/**
 * Created by Adrien on 01/08/2016.
 */
public final class EdjingFreeHelper {
    private static final String TAG = "EdjingFreeHelper";

    public static void gotoLocal()throws UiObjectNotFoundException {
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

    public static void loadTrackA () throws UiObjectNotFoundException{
        findObjectById("com.edjing.edjingdjturntable:id/row_track_library_title").click();
        sleep(2_500);
        takeScreenShot(AppSupported.EDJING_FREE,"TrackLoaded" + getCurrentDateString());
        findObjectById("com.edjing.edjingdjturntable:id/platine_menu_bottom_play_button_deckA").click();
        Log.d(TAG, "loadTrack: TrackIsPlaying");
        takeScreenShot(AppSupported.EDJING_FREE,"TrackIsPlaying" + getCurrentDateString());
    }

    public static void gotoQueue () throws UiObjectNotFoundException {
        click("com.edjing.edjingdjturntable:id/queue_fab");
        takeScreenShot(AppSupported.EDJING_FREE,"QueueView " + getCurrentDateString());
        Log.d(TAG, "gotoQueue: QueueView");
    }

    public static void gotoAutomixFromQueue()throws UiObjectNotFoundException{
        click("com.edjing.edjingdjturntable:id/automix_fab");
        Log.d(TAG, "gotoAutomixFromQueue: Clicked");
    }

    public static void stopAutomix()throws UiObjectNotFoundException{

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


}
