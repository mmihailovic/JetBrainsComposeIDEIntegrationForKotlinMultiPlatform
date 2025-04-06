package org.example.model.process;

import java.io.IOException;

public class ProcessExecutorImpl implements ProcessExecutor {
    @Override
    public Process executeProcess(String... params) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(params);
        return processBuilder.start();
    }
}
