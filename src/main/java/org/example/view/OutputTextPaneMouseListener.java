package org.example.view;

import lombok.AllArgsConstructor;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@AllArgsConstructor
public class OutputTextPaneMouseListener extends MouseAdapter {
    private EditorView editorView;

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        JTextPane pane = (JTextPane) e.getSource();
        Element selectedElement = pane.getStyledDocument()
                .getCharacterElement(pane.viewToModel2D(e.getPoint()));

        AttributeSet attributeSet = selectedElement.getAttributes();
        Integer line = (Integer) attributeSet.getAttribute("line");

        if (line != null) {
            int lineStartOffset = editorView.getCodeTextPane().getStyledDocument().getDefaultRootElement()
                    .getElement(line - 1).getStartOffset();

            editorView.getCodeTextPane().setCaretPosition(lineStartOffset);
            editorView.getCodeTextPane().requestFocus();
        }
    }
}
