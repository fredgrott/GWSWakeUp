package com.github.shareme.gwswakeup.library;

import android.app.Activity;
import android.app.KeyguardManager;
import android.os.PowerManager;

import static android.content.Context.KEYGUARD_SERVICE;
import static android.content.Context.POWER_SERVICE;
import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.FULL_WAKE_LOCK;
import static android.os.PowerManager.ON_AFTER_RELEASE;
import static android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;


/**
 * Add this to your application's src/debug/... sources
 *
 * Requires Two Permissions in the AndroidManifest:
 *
 *         WAKE_LOCK
 *         DISABLE_KEYGUARD
 * Debug AndroidManifest should look like this:
 *
 * <code>
 *<?xml version="1.0" encoding="utf-8"?>
 *<!-- Add this as a debug manifest so the permissions won't be required by your production app -->
 *<manifest xmlns:android="http://schemas.android.com/apk/res/android">
 *<uses-permission android:name="android.permission.WAKE_LOCK" />
 *<uses-permission android:name="android.permission.DISABLE_KEYGUARD" />
 *</manifest>
 *
 *
 *
 * </code>
 *
 *
 * Created by fgrott on 10/10/2015.
 */
@SuppressWarnings("unused")
public class DebugUtils {

    /**
     * Show the activity over the lockscreen and wake up the device. If you launched the app manually
     * both of these conditions are already true. If you deployed from the IDE, however, this will
     * save you from hundreds of power button presses and pattern swiping per day!
     */
    @SuppressWarnings("deprecation")
    public static void riseAndShine(Activity activity) {

        KeyguardManager keyguardManager = (KeyguardManager) activity.getSystemService(KEYGUARD_SERVICE);
        final KeyguardManager.KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("Unlock!");
        keyguardLock.disableKeyguard();

        activity.getWindow().addFlags(FLAG_SHOW_WHEN_LOCKED);

        PowerManager powerManager = (PowerManager) activity.getSystemService(POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager.newWakeLock(FULL_WAKE_LOCK | ACQUIRE_CAUSES_WAKEUP | ON_AFTER_RELEASE, "Wakeup!");
        wakeLock.acquire();
        wakeLock.release();

    }

}
