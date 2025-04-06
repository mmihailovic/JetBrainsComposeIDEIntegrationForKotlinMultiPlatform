package org.example.observer;

import org.example.observer.notification.Notification;

public interface Publisher {

    /**
     * This method adds a subscriber to the subscribers list
     * @param subscriber {@link Subscriber} object represents the subscriber to be added
     */
    void addSubscriber(Subscriber subscriber);

    /**
     * This method notifies all subscribers sending notification
     * @param notification {@link Notification} object represents notification to be sent
     */
    void notifySubscribers(Notification notification);
}
