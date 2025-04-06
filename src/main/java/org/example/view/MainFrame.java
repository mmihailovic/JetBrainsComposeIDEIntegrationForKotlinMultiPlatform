package org.example.view;

import org.example.controller.ExecutionController;
import org.example.model.Executor;
import org.example.model.ExecutorImpl;
import org.example.model.process.ProcessExecutorImpl;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final Executor executor = new ExecutorImpl(new ProcessExecutorImpl());
    private final EditorView editorView = new EditorView(executor);
    private final OutputView outputView = new OutputView(executor, editorView);
    private final ExecutionController executionController = new ExecutionController(executor, editorView, outputView);

    public MainFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(800, 800));
        this.add(new MainPanel(editorView, outputView));
    }
}
