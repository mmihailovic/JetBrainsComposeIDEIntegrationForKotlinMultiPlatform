package org.example.view;

import lombok.Getter;
import org.example.observer.Publisher;
import org.example.observer.ScriptExecutionSubscriber;
import org.example.observer.notification.Notification;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;


@Getter
public class OutputView extends JPanel implements ScriptExecutionSubscriber {
    private final JTextPane outputTextPane = new JTextPane();
    private final JLabel statusLabel = new JLabel("Script not executed");
    private final EditorView editorView;
    private final SimpleAttributeSet errorAttributeSet = new SimpleAttributeSet();

    public OutputView(Publisher publisher, EditorView editorView) {
        this.editorView = editorView;
        StyleConstants.setForeground(errorAttributeSet, new Color(217, 83, 92));
        this.setLayout(new BorderLayout());
        outputTextPane.setEditable(false);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        JScrollPane scrollPane = new JScrollPane(outputTextPane);
        this.add(statusLabel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        publisher.addSubscriber(this);
    }

    @Override
    public void showOutput(String output) {
        SwingUtilities.invokeLater(() -> {
            try {
                Document document = outputTextPane.getDocument();
                document.insertString(document.getLength(), output + "\n", null);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void showError(String error) {
        SwingUtilities.invokeLater(() -> {
            try {
                Document document = outputTextPane.getDocument();
                StyledDocument styledDocument = outputTextPane.getStyledDocument();
                styledDocument.insertString(document.getLength(), error + "\n", errorAttributeSet);
            } catch (BadLocationException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void showExecutionStatus(String status) {
        this.statusLabel.setText(status);
    }

    @Override
    public void update(Notification notification) {
        notification.handleNotification(this);
    }
}
