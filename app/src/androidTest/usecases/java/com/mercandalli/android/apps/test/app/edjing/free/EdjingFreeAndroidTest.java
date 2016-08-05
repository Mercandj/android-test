package com.mercandalli.android.apps.test.app.edjing.free;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.util.Log;

import com.mercandalli.android.apps.test.app.AppSupported;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import static android.support.test.uiautomator.UiAutomator.getDevice;
import static android.support.test.uiautomator.UiAutomator.openApp;
import static android.support.test.uiautomator.UiAutomator.pressBack;
import static android.support.test.uiautomator.UiAutomator.pressHome;
import static android.support.test.uiautomator.UiAutomator.sleep;
import static android.support.test.uiautomator.UiAutomatorClick.click;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectById;
import static android.support.test.uiautomator.UiAutomatorFind.findObjectContainsText;

/**
 * Created by Adrien on 05/08/2016.
 */

@RunWith(AndroidJUnit4.class)
public class EdjingFreeAndroidTest  {

    private static final String TAG = "EdjingFreeAndroidTest";
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
}
