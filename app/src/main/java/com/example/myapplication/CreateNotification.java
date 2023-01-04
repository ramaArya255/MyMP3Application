package com.example.myapplication;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.Service.NotificationActionService;

public class CreateNotification {
    public static String CHANEL_ID = "chanel1";
    public static String ACTIONPREVIOUS = "acntionprevious";
    public static String ACTIONNEXT = "actionnext";



    public static Notification notification;

    public static void createNotification(Context context, AudioModel track){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationManagerCompat notificationManagerCompat;
            notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat;
            mediaSessionCompat = new MediaSessionCompat(context, "tag");

            //skip previous notif
            PendingIntent pendingIntentPrevious;
            int drw_previous;
            Intent intentPrevious = new Intent(context, NotificationActionService.class)
                    .setAction(ACTIONPREVIOUS);
            pendingIntentPrevious = PendingIntent.getBroadcast(context,0,
                    intentPrevious, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_previous = R.drawable.notif_ic_baseline_skip_previous_24;

            //skip next notif
            PendingIntent pendingIntentNext;
            int drw_next;
            Intent intentNext = new Intent(context, NotificationActionService.class)
                    .setAction(ACTIONNEXT);
            pendingIntentNext = PendingIntent.getBroadcast(context,0,
                    intentNext, PendingIntent.FLAG_UPDATE_CURRENT);
            drw_next = R.drawable.notif_ic_baseline_skip_next_24;

            notification = new NotificationCompat.Builder(context, CHANEL_ID)
                    .setSmallIcon(R.drawable.music_icon_big)
                    .setContentTitle(track.getTitle())
                    .setOnlyAlertOnce(false)
                    .setShowWhen(false)
                    .addAction(drw_previous, "Previous", pendingIntentPrevious)
                    .addAction(drw_next, "Next", pendingIntentNext)
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                    .build();
            notificationManagerCompat.notify(1,notification);
        }
    }
}
