package org.example.observer.notification;

import lombok.AllArgsConstructor;
import org.example.observer.ScriptExecutionSubscriber;
import org.example.observer.Subscriber;

@AllArgsConstructor
public class ShowStatusNotification extends Notification {
    private String status;

    @Override
    public void handleNotification(Subscriber subscriber) {
        if(subscriber instanceof ScriptExecutionSubscriber scriptExecutionSubscriber) {
            scriptExecutionSubscriber.showExecutionStatus(status);
        }
    }
}
