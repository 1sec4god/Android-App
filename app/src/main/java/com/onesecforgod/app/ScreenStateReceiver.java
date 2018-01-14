package com.onesecforgod.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 ** Created by afiouni on 1/15/16.
 **
 ** Receiver to get SREEN_ON and SCREEN_OFF actions
 ** This is required as those actions cannot be added to manifest and should be coded.
 ** Source: http://developer.android.com/reference/android/content/Intent.html#ACTION_SCREEN_OFF
 */
public final class ScreenStateReceiver extends BroadcastReceiver {

  @Override
  public final void onReceive(final Context context, final Intent intent) {

    // Send events received to MainService
    // This is used to keep the service alive
    // on SCREEN_ON and SCREEN_OFF ping the service for it to be ready when device unlocks
    final Intent mainServiceIntent = new Intent(context, MainService.class);
    mainServiceIntent.setAction((intent.getAction() != null) ? intent.getAction() : "");
    context.startService(mainServiceIntent);

  }
}
