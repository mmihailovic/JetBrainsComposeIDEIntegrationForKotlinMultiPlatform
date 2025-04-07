package org.example.view;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(EditorView editorView, OutputView outputView) {
        this.setLayout(new BorderLayout());
        this.add(new EditorOutputSplitPane(editorView, outputView), BorderLayout.CENTER);
    }
}
