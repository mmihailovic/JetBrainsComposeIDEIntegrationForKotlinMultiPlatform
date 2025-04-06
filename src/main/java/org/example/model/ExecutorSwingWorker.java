package org.example.model;

import javax.swing.*;
import java.io.InputStream;
import java.util.function.Consumer;

public class ExecutorSwingWorker extends SwingWorker<Void, Void> {
    private final Consumer<InputStream> doInBackgroundFunction;
    private final InputStream inputStreamReader;

    public ExecutorSwingWorker(InputStream inputStreamReader, Consumer<InputStream> doInBackgroundFunction) {
        this.doInBackgroundFunction = doInBackgroundFunction;
        this.inputStreamReader = inputStreamReader;
    }

    @Override
    protected Void doInBackground() throws Exception {
        doInBackgroundFunction.accept(inputStreamReader);
        return null;
    }
}
