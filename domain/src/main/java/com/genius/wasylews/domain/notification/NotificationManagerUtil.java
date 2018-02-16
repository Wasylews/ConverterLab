package com.genius.wasylews.domain.notification;

public interface NotificationManagerUtil {

    void showNotification(String text);
    void showProgress(int start, int stop, String description);
}