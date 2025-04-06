package org.example.observer.notification;

import lombok.AllArgsConstructor;
import org.example.observer.ScriptExecutionSubscriber;
import org.example.observer.Subscriber;

@AllArgsConstructor
public class ShowOutputNotification extends Notification {
    private String output;

    @Override
    public void handleNotification(Subscriber subscriber) {
        if(subscriber instanceof ScriptExecutionSubscriber scriptExecutionSubscriber) {
            scriptExecutionSubscriber.showOutput(output);
        }
    }
}
