package org.example.view;

import lombok.Getter;
import org.example.observer.Publisher;
import org.example.observer.ScriptInputSubscriber;
import org.example.observer.notification.Notification;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

@Getter
public class EditorView extends JPanel implements ScriptInputSubscriber {
    private final JButton executeButton = new JButton("Execute");
    private final JTextPane codeTextPane = new JTextPane();
    private final SimpleAttributeSet highlightAttributeSet = new SimpleAttributeSet();

    public EditorView(Publisher publisher) {
        StyleConstants.setForeground(highlightAttributeSet, new Color(206, 141, 109));
        StyleConstants.setBold(highlightAttributeSet, true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(codeTextPane);
        executeButton.setAlignmentX(LEFT_ALIGNMENT);
        scrollPane.setAlignmentX(LEFT_ALIGNMENT);
        this.add(executeButton);
        this.add(scrollPane);
        publisher.addSubscriber(this);
    }

    @Override
    public void highlightKeywords(int start, int end) {
        SwingUtilities.invokeLater(() -> codeTextPane.getStyledDocument().setCharacterAttributes(start, end - start,
                highlightAttributeSet, false));
    }

    @Override
    public void update(Notification notification) {
        notification.handleNotification(this);
    }
}
