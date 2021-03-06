package com.mercandalli.android.apps.test;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class LauncherActivity extends Activity implements OnClickListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        findViewById(R.id.launcher_activity_main_button).setOnClickListener(this);
        findViewById(R.id.launcher_activity_initialize_button).setOnClickListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(final View v) {
        final int viewId = v.getId();
        switch (viewId) {
            case R.id.launcher_activity_main_button:
                Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
                break;
            case R.id.launcher_activity_initialize_button:
                // Click this button first before test. (on Android API > Lollipop)
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0);
                break;
        }
    }
}