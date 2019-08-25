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

import java.util.Timer;
import java.util.TimerTask;

public class Notify extends Activity {
    @Override
protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        addNotification();
        }

    private void addNotification() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.abc)
                        .setContentTitle("B-Easy")
                        .setContentText("You sucessfully issued your book!!!");
      Intent resultIntent = new Intent(this, NotificationView.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(NotificationView.class);

   /* Adds the Intent that starts the Activity to the top of the stack */
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

   /* notificationID allows you to update the notification later on. */
        mNotificationManager.notify(0,mBuilder.build());
        final String TAG = Notify.class.getSimpleName();
        Timer timer = new Timer();
        TimerTask t = new TimerTask() {
            @Override
            public void run() {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(Notify.this)
                                .setSmallIcon(R.drawable.abc)
                                .setContentTitle("B-Easy")
                                .setContentText("Tomorrow is Your return date");
                Intent resultIntent = new Intent(Notify.this, NotificationView.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(Notify.this);
                stackBuilder.addParentStack(NotificationView.class);

   /* Adds the Intent that starts the Activity to the top of the stack */
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =stackBuilder.getPendingIntent(1,PendingIntent.FLAG_UPDATE_CURRENT);

                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

   /* notificationID allows you to update the notification later on. */
                mNotificationManager.notify(0,mBuilder.build());
                final String TAG = Notify.class.getSimpleName();
                Intent i=new Intent(Notify.this,Search.class);
                startActivity(i);
                finish();

            }
        };

        timer.schedule(t, 1000*10);
    }

}