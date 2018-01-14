package com.onesecforgod.app;

import android.app.Activity;
import android.os.Handler;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public final class MessageActivity extends Activity implements Runnable {

  @Override
  protected final void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    this.setContentView(R.layout.activity_message);

    //Retrieve Extras from Intent
    final Bundle extrasBundle = this.getIntent().getExtras();

    final TextView messageText = (TextView) this.findViewById(R.id.message_text);
    messageText.setText(extrasBundle.getString(CONST.config.MESSAGE_TEXT));

    // Close the message after set time
    // Should run in another thread in order fo the user interface to remain responsive
    final Handler handler = new Handler();
    handler.postDelayed(this, extrasBundle.getLong(CONST.config.MESSAGE_DURATION));
  }

  //Runnable code that will run as per handler.postdelayed
  //The class implemnts Runnable instead of creating a Runnable anonymous instance.
  @Override
  public final void run() {this.closeMessage();}


  //This is to override the back button transition as without this the system default (slide down
  //in my case) will be used.
  @Override
  public void onBackPressed() {
    super.onBackPressed();
    this.overridePendingTransition(android.R.anim.fade_in, 0);
  }

  public final void closeMessage(final View view) {this.closeMessage();}
  public final void closeMessage() {
    this.finish();

    // Fade-out the message regardless of the default animation in the OS
    // This is important as if there is a window behind the message, the message will slide down
    // when disappearing as slide down is the default on my ASUS. This will make it fade out.
    this.overridePendingTransition(android.R.anim.fade_in, 0); //android.R.anim.fade_out);
  }

}
