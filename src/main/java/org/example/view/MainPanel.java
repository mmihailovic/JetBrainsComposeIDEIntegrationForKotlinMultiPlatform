package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(EditorView editorView, OutputView outputView) {
        this.setLayout(new BorderLayout());
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, editorView, outputView);
        splitPane.setResizeWeight(0.5);
        this.add(splitPane, BorderLayout.CENTER);
    }
}
