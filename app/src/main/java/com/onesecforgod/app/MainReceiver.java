package com.onesecforgod.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*
**
** Created by Khaled Afiouni on 1/5/16
**
** MainReceiver Class
**
*/

public final class MainReceiver extends BroadcastReceiver {

  @Override
  public final void onReceive(final Context context, final Intent intent) {

    // Send events received to MainService
    // This will either start the service only or show a message
    final Intent mainServiceIntent = new Intent(context, MainService.class);
    mainServiceIntent.setAction(intent.getAction());
    context.startService(mainServiceIntent);
  }
}


/*
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN); // Intent.ACTION_VIEW);
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); | Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY | Intent.FLAG_ACTIVITY_SINGLE_TOP);


            i.setComponent(new ComponentName("com.khaledafiouni.myfirstproject", "com.khaledafiouni.myfirstproject.DisplayMessageActivity"));
//        i.setAction("android.intent.action.MAIN");
//        i.addCategory("android.intent.category.LAUNCHER");
//        i.addCategory("android.intent.category.DEFAULT");
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra(BISMILLAH_MESSAGE, "Test Message");
            this.startActivity(i);


/*
        Intent intent2 = new Intent(context.getApplicationContext(),DisplayMessageActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent2.putExtra(BISMILLAH_MESSAGE, "Test Message");
        context.startActivity(intent2);



        Intent intent2 = new Intent(context,DisplayMessageActivity.class);
        intent2.putExtra(BISMILLAH_MESSAGE, "Test Message");
        context.startActivity(intent2);



        new AlertDialog.Builder(context)
                .setTitle("Test Dialog")
                .setMessage("Test Dialog :)")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
*/
