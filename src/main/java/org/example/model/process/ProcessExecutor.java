package org.example.model.process;

import java.io.IOException;

public interface ProcessExecutor {
    /**
     * This method creates new process and execute it with the specified parameters
     * @param params parameters
     * @return {@link Process} object representing created process
     */
    Process executeProcess(String ... params) throws IOException;
}
