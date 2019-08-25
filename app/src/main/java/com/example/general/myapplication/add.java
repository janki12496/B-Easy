package com.example.general.myapplication;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

public class add extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        String res1 = getIntent().getExtras().getString("abc");
        addNotification1(res1);
    }

    public void addNotification1(String res) {
        if (res.equals("Cancled")) {
            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(this)
                            .setSmallIcon(R.drawable.abc)
                            .setContentTitle("B-Easy")
                            .setContentText("Your request is cancled,Sorry!!!");
            Intent resultIntent = new Intent(this, NV.class);

            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addParentStack(NV.class);

   /* Adds the Intent that starts the Activity to the top of the stack */
            stackBuilder.addNextIntent(resultIntent);
            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(resultPendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

   /* notificationID allows you to update the notification later on. */
            mNotificationManager.notify(0, mBuilder.build());
            final String TAG = add.class.getSimpleName();
            Log.d(TAG, "he" + res);
            Intent i=new Intent(this,Search.class);
            this.startActivity(i);
            finish();
        }
        else {
            Intent i = new Intent(this, Notify.class);
            this.startActivity(i);
            finish();

        }
    }
}