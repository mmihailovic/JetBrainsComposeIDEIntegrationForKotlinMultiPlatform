package org.example.observer.notification;

import org.example.observer.Subscriber;

public abstract class Notification {
    /**
     * This method handles the notification for the given subscriber
     * @param subscriber {@link Subscriber} object represents the subscriber
     */
    public abstract void handleNotification(Subscriber subscriber);
}
