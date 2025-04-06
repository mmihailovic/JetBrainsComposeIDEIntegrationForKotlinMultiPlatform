package org.example.observer;

import org.example.observer.notification.Notification;

public interface Subscriber {

    /**
     * This method updates the subscriber based on a specified notification
     * @param notification {@link Notification} object represents the notification
     */
    void update(Notification notification);
}
