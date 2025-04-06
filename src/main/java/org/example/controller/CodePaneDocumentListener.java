package org.example.controller;

import org.example.model.Executor;
import org.example.view.EditorView;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

public class CodePaneDocumentListener implements DocumentListener {
    private final EditorView editorView;
    private final Executor executor;
    private final SimpleAttributeSet attributeSet;

    public CodePaneDocumentListener(EditorView editorView, Executor executor) {
        this.editorView = editorView;
        this.executor = executor;
        attributeSet = new SimpleAttributeSet();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        clearAttributes();
        highlightKeywords();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        clearAttributes();
        highlightKeywords();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {

    }

    private void clearAttributes() {
        SwingUtilities.invokeLater(() -> {
            StyledDocument styledDocument = editorView.getCodeTextPane().getStyledDocument();
            styledDocument.setCharacterAttributes(0, styledDocument.getLength(), attributeSet, true);
        });
    }

    private void highlightKeywords() {
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() throws Exception {
                executor.findKeywords(editorView.getCodeTextPane().getText());
                return null;
            }
        }.execute();
    }
}
