package org.example.observer.notification;

import lombok.AllArgsConstructor;
import org.example.model.LinkedError;
import org.example.observer.ScriptExecutionSubscriber;
import org.example.observer.Subscriber;

@AllArgsConstructor
public class ShowLinkedErrorNotification extends Notification {
    private LinkedError error;

    @Override
    public void handleNotification(Subscriber subscriber) {
        if(subscriber instanceof ScriptExecutionSubscriber scriptExecutionSubscriber) {
            scriptExecutionSubscriber.showLinkedError(error);
        }
    }
}
