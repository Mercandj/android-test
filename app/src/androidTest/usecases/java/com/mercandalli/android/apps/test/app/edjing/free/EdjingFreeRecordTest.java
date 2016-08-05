package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.UiAutomator.getCurrentDateString;
import static android.support.test.uiautomator.UiAutomator.sleep;
import static android.support.test.uiautomator.UiAutomator.takeScreenShot;
import static android.support.test.uiautomator.UiAutomatorClick.click;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectById;

/**
 * Created by Adrien on 01/08/2016.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public final class EdjingFreeRecordTest extends EdjingFreeAndroidTest  {

    private static final String TAG = "EdjingFreeRecordTest";


    @Test
    public void testEdjingFreeNoAds_Record() throws UiObjectNotFoundException {

        EdjingFreeHelper.gotoLocal();
        EdjingFreeHelper.loadTrackA();
        startRec();
        EdjingFreeHelper.gotoLocal();
        EdjingFreeHelper.gotoQueue();
        EdjingFreeHelper.gotoAutomixFromQueue();
        sleep(120_000);
        Log.d(TAG, "testEdjingFreeNoAds_Record: AutomixHasBeenPlayed120sec");
        EdjingFreeHelper.stopAutomix();
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


}
