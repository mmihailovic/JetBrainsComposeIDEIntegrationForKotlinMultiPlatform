package org.example.observer;

public interface ScriptExecutionSubscriber extends Subscriber {
    /**
     * This method shows output from the executed script
     * @param output the output to be shown
     */
    void showOutput(String output);

    /**
     * This method shows error from the executed script
     * @param error the error to be shown
     */
    void showError(String error);

    /**
     * This method shows the execution status
     * @param status the status to be shown
     */
    void showExecutionStatus(String status);
}
