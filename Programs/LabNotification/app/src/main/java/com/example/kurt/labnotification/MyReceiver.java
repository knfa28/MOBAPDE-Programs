package com.example.kurt.labnotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class MyReceiver extends BroadcastReceiver {
    final static int NOTIF_ID = 0;
    static int count = 0;
    final static int MA_PENDING =0;

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        count++;

        Intent maIntent = new Intent();
        maIntent.setClass(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, MA_PENDING, maIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i2 = new Intent();
        i2.setClass(context, SecondActivity.class);
        PendingIntent pi2 = PendingIntent.getActivity(context, MA_PENDING, i2, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent i3 = new Intent();
        i3.setClass(context, ThirdActivity.class);
        PendingIntent pi3 = PendingIntent.getActivity(context, MA_PENDING, i3, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat
                .Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Waddup World")
                .setContentText("Peace out World")
                .setTicker("Touch me all over")
                .setNumber(count)
                .setContentIntent(pendingIntent)
                .addAction(R.mipmap.ic_launcher, "Second", pi2)
                .addAction(R.mipmap.ic_launcher, "Third", pi3);

        NotificationManager notifManager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);

        notifManager.notify(NOTIF_ID, builder.build());
    }
}
