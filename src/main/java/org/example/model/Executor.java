package org.example.model;

import org.example.observer.Publisher;

import java.io.InputStream;

public interface Executor extends Publisher {

    /**
     * This method executes script
     * @param script the script to be executed
     */
    void executeScript(String script);

    /**
     * This method finds keywords in the specified script
     * and notifies the view to highlight them
     * @param script the script in which keywords are searched for
     */
    void findKeywords(String script);

    /**
     * This method retrieves output from the executed script
     * @param inputStream The {@link InputStream}
     * object that contains the output from the executed script
     */
    void readOutput(InputStream inputStream);

    /**
     * This method retrieves errors from the executed script
     * @param inputStream The {@link InputStream}
     * object that contains the errors from the executed script
     */
    void readErrors(InputStream inputStream);
}
