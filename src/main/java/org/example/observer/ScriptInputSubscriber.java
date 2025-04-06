package org.example.observer;

public interface ScriptInputSubscriber extends Subscriber{

    /**
     * This method highlights the keywords
     * @param start the start position of the keyword
     * @param end the end position of the keyword
     */
    void highlightKeywords(int start, int end);
}
