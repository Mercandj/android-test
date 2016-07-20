### Android Generic Tests

* Date: 2016-07-04
* Author: Jonathan Mercandalli - jonathan.mercandalli@djit.fr

On Android > Lollipop, click the app button to accept permissions.

* nohup am instrument -w -r   -e debug false -e class com.mercandalli.android.apps.test.generic.GenericAndroidTest#testFirstButton com.mercandalli.android.apps.test.test/android.support.test.runner.AndroidJUnitRunner > /sdcard/test.txt &