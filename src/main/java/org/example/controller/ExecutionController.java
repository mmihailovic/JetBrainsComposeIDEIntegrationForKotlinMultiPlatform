package org.example.controller;

import org.example.model.Executor;
import org.example.view.EditorView;
import org.example.view.OutputTextPaneMouseListener;
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
                    executor.executeScript(editorView.getScrollablePane().getTextPane().getText());
                    return null;
                }
            }.execute();
        });

        editorView.getScrollablePane().getTextPane().getDocument().addDocumentListener(new CodePaneDocumentListener(editorView, executor));
        outputView.getOutputTextPane().addMouseListener(new OutputTextPaneMouseListener(editorView));
    }
}
