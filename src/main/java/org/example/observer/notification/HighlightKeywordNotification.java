package org.example.observer.notification;

import lombok.AllArgsConstructor;
import org.example.observer.ScriptInputSubscriber;
import org.example.observer.Subscriber;

@AllArgsConstructor
public class HighlightKeywordNotification extends Notification {
    private int start;
    private int end;

    @Override
    public void handleNotification(Subscriber subscriber) {
        if(subscriber instanceof ScriptInputSubscriber scriptInputSubscriber) {
            scriptInputSubscriber.highlightKeywords(start, end);
        }
    }
}
