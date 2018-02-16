package com.genius.wasylews.device.notification;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;

import com.genius.wasylews.domain.notification.NotificationManagerUtil;

import javax.inject.Inject;


public class NotificationUtil implements NotificationManagerUtil {

    private static final int NOTIFICATION_ID = 0;
    private final android.app.NotificationManager mNotificationManager;
    private final NotificationCompat.Builder mNotificationBuilder;

    @Inject
    public NotificationUtil(android.app.NotificationManager notificationManager,
                            NotificationCompat.Builder builder) {
        mNotificationManager = notificationManager;
        mNotificationBuilder = builder;
    }

    @Override
    public void showNotification(String text) {
        Notification notification = mNotificationBuilder.setContentText(text)
                .setOngoing(false)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }

    @Override
    public void showProgress(int start, int stop, String description) {
        Notification notification = mNotificationBuilder.setContentText(description)
                .setProgress(start, stop, false)
                .setOngoing(true)
                .build();

        mNotificationManager.notify(NOTIFICATION_ID, notification);
    }
}
