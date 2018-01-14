package com.onesecforgod.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class MainActivity extends AppCompatActivity {

  @Override
  protected final void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.setContentView(R.layout.activity_main);

    final Toolbar toolbar = (Toolbar) this.findViewById(R.id.tool_bar);
    this.setSupportActionBar(toolbar);

    // Show the icon on the ToolBar
    //noinspection ConstantConditions
    this.getSupportActionBar().setIcon(R.drawable.icon_with_right_padding);
    this.getSupportActionBar().setDisplayUseLogoEnabled(true);
  }

  @Override
  protected final void onStart() {
    super.onStart();

    // Tell the service the MainActivity is on
    final Intent mainServiceIntent = new Intent(this, MainService.class);
    // Add action to this intent. Not necessary as already checking for Null in MainService
    mainServiceIntent.setAction(this.getClass().getSimpleName());
    this.startService(mainServiceIntent);
  }

  public final void testMessage(final View view) {

    final Intent mainServiceIntent = new Intent(this, MainService.class);
    mainServiceIntent.setAction(CONST.actions.ACTION_SHOW_MESSAGE);
    this.startService(mainServiceIntent);
  }
}
