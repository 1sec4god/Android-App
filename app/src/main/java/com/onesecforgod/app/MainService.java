package com.onesecforgod.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

//TODO: On Min SDK 16
//    - Change NotificationCompat to Notification as the build() function
//      is available after that version
//    - Remove the version check on setPriority

public final class MainService extends Service {

  private Tracker mTracker;

  private static final int NOTIFICATION_ID = 1;
  private BroadcastReceiver m_screenStateReceiver = null;

  @Override
  public final void onCreate() {
    super.onCreate();

    //Build Notification
    final Intent notificationIntent = new Intent(this, MainActivity.class);
    final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

    final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);

    //noinspection DuplicateStringLiteralInspection,HardCodedStringLiteral
    notificationBuilder.setContentTitle(this.getString(this.getResources().getIdentifier("app_name", "string", this.getPackageName())));

    //notificationBuilder.setContentText("Notification Context");
    notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
    //notificationBuilder.setTicker("Notification Ticker");
    notificationBuilder.setOngoing(true);
    notificationBuilder.setContentIntent(pendingIntent);

    if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) //API 16
    {
      // Hides the icon of the notification form the title bar
      notificationBuilder.setPriority(Notification.PRIORITY_MIN);
    }
    //Show Notification
    this.startForeground(MainService.NOTIFICATION_ID, notificationBuilder.build());


    //Create IntentFilter and register ScreenStateReceiver
    final IntentFilter screenStateFilter = new IntentFilter();
    screenStateFilter.addAction(Intent.ACTION_SCREEN_ON);
    screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF);

    this.m_screenStateReceiver = new ScreenStateReceiver();
    this.registerReceiver(this.m_screenStateReceiver, screenStateFilter);

    //Set default values. False = do not write if already written
    PreferenceManager.setDefaultValues(this, R.xml.preferences, false);

    this.mTracker = ((MainApplication) getApplication()).getDefaultTracker();
  }

  @Override
  public final void onDestroy() {
    super.onDestroy();

    this.stopForeground(true);

    this.unregisterReceiver(this.m_screenStateReceiver);
    //noinspection AssignmentToNull Suppress lint null assignment warning
    this.m_screenStateReceiver = null;
  }

  @Override
  public final int onStartCommand(final Intent intent, final int flags, final int startId)
  {
    super.onStartCommand(intent, flags, startId);

    String intentAction = "";
    //Check if intent getAction returns Null to avoid App NullPointerException
    if ((intent!= null) && (intent.getAction() != null)) {
      intentAction = intent.getAction();
    }

    switch (intentAction) {

      case CONST.actions.ACTION_SHOW_MESSAGE:
      case Intent.ACTION_USER_PRESENT:
        this.showMessage();
        break;

      // Sources of intent: MainReceiver, MainActivity, ScreenStateReceiver
      default: // in ALL other cases, do nothing at ALL but make sure the service is running :)
        break;
    }

    return Service.START_STICKY;
  }

  private void showMessage () {

    //TODO: make the data persistent and read changes when they happen instead of reading everytime
    // this is called

    final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
    final String messageStyle = preferences.getString(CONST.preference.key.STYLE, CONST.preference.defaults.MESSAGE_STYLE);

    final String messageTextValue = preferences.getString(CONST.preference.key.TEXT, CONST.preference.defaults.MESSAGE_TEXT);
    final String messageLanguage = preferences.getString(CONST.preference.key.LANGUAGE, CONST.preference.value.ARABIC_LANGUAGE);

    //noinspection DuplicateStringLiteralInspection,HardCodedStringLiteral,MagicCharacter,StringConcatenation
    final String messageText = this.getString(this.getResources().getIdentifier(messageTextValue + '.' + messageLanguage, "string", this.getPackageName()));

    this.mTracker.send(new HitBuilders.EventBuilder()
        .setCategory(messageTextValue) //Message Name
        .setAction(messageStyle) //Full Screen or Toast
        .setLabel(messageLanguage) //ar or en
        .build());

    //noinspection CallToStringEquals
    if (CONST.preference.value.MESSAGE_STYLE_FULL_SCREEN.equals(messageStyle)) {

      final Intent showMessageIntent = new Intent(this, MessageActivity.class);
      showMessageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

      showMessageIntent.putExtra(CONST.config.MESSAGE_TEXT, messageText);
      showMessageIntent.putExtra(CONST.config.MESSAGE_DURATION, CONST.preference.defaults.MESSAGE_DURATION);

      this.startActivity(showMessageIntent);

    } else {
      Toast.makeText(this, messageText, Toast.LENGTH_SHORT).show();
    }
  }


  /*
    It is a must to declare this method or the code will not cmpile.
    From the documentation: You must always implement this method, but if you don't want to allow
    binding, then you should return null.
    Source: http://developer.android.com/guide/components/services.html
  */
  @Override
  public final IBinder onBind(final Intent intent) {
    /* Instead of throwing an error as the default class creator does,
       return null as this is never used in this App
    */
    //noinspection ReturnOfNull Required to suppress lint check on this null return
    return null;
  }
}
