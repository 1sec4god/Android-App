package com.onesecforgod.app;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

/**
 ** Created by afiouni on 1/22/16.
 */
public final class MainApplication extends Application {
  private Tracker mTracker;

  /**
   * Gets the default {@link Tracker} for this {@link Application}.
   * @return tracker
   */
  synchronized public Tracker getDefaultTracker() {
    if (mTracker == null) {
      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
      // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
      mTracker = analytics.newTracker(R.xml.global_tracker);
    }
    mTracker.enableAdvertisingIdCollection(true);
    mTracker.enableExceptionReporting(true);
    mTracker.enableAutoActivityTracking(true);
    return mTracker;
  }
}