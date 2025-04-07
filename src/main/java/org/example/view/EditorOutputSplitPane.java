package org.example.view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public class EditorOutputSplitPane extends JSplitPane {

    public EditorOutputSplitPane(EditorView editorView, OutputView outputView) {
        super(JSplitPane.HORIZONTAL_SPLIT, editorView, outputView);
        this.setResizeWeight(0.5);
        this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10,new Color(43, 45, 48) ));
        this.setUI(new BasicSplitPaneUI() {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    @Override
                    public void paint(Graphics g) {
                        g.setColor(new Color(43, 45, 48));
                        g.fillRect(0, 0, getSize().width, getSize().height);
                        setBorder(null);
                        super.paint(g);
                    }
                };
            }
        });
    }
}
