package org.example.controller;

import org.example.model.Executor;
import org.example.view.EditorView;
import org.example.view.OutputView;

import javax.swing.*;

public class ExecutionController {

    public ExecutionController(Executor executor, EditorView editorView, OutputView outputView) {
        editorView.getExecuteButton().addActionListener(e -> {
            outputView.getOutputTextPane().setText("");
            outputView.getStatusLabel().setText("Running ...");
            new SwingWorker<>() {
                @Override
                protected Object doInBackground() throws Exception {
                    executor.executeScript(editorView.getCodeTextPane().getText());
                    return null;
                }
            }.execute();
        });

        editorView.getCodeTextPane().getDocument().addDocumentListener(new CodePaneDocumentListener(editorView, executor));
    }
}
