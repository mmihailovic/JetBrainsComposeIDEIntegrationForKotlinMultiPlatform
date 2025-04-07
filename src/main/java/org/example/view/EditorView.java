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
    private static final String SCRIPT_NAME = "script.kts";
    private final JButton executeButton = new JButton("Execute");
    private final JTabbedPane jTabbedPane;
    private final SimpleAttributeSet highlightAttributeSet = new SimpleAttributeSet();
    private final ScrollablePane scrollablePane;

    public EditorView(Publisher publisher) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(executeButton);
        this.jTabbedPane = new JTabbedPane();
        this.scrollablePane = new ScrollablePane(new JTextPane(), SCRIPT_NAME, true);
        this.jTabbedPane.addTab(SCRIPT_NAME, scrollablePane);
        this.add(jTabbedPane);
        this.styleComponents();
        publisher.addSubscriber(this);
    }

    private void styleComponents() {
        this.setBackground(new Color(43, 45, 48));
        this.jTabbedPane.setAlignmentX(LEFT_ALIGNMENT);
        this.executeButton.setAlignmentX(LEFT_ALIGNMENT);
        StyleConstants.setForeground(highlightAttributeSet, new Color(206, 141, 109));
        StyleConstants.setBold(highlightAttributeSet, true);
    }

    @Override
    public void highlightKeywords(int start, int end) {
        SwingUtilities.invokeLater(() -> scrollablePane.getTextPane().getStyledDocument()
                .setCharacterAttributes(start, end - start, highlightAttributeSet, false));
    }

    @Override
    public void update(Notification notification) {
        notification.handleNotification(this);
    }

    public void addTab(String className, String code, Integer line) {
        ScrollablePane scrollablePane = new ScrollablePane(new JTextPane(), className, false);
        scrollablePane.getTextPane().setText(code);
        jTabbedPane.addTab(className, scrollablePane);
        jTabbedPane.setSelectedComponent(scrollablePane);
        int lineStartOffset = scrollablePane.getTextPane().getStyledDocument().getDefaultRootElement()
                    .getElement(line).getStartOffset();
        scrollablePane.getTextPane().setCaretPosition(lineStartOffset);
        scrollablePane.getTextPane().requestFocus();
    }

    public void showCodePane(Integer line) {
        JTextPane codePane = scrollablePane.getTextPane();
        int lineStartOffset = codePane.getStyledDocument().getDefaultRootElement()
                .getElement(line - 1).getStartOffset();
        jTabbedPane.setSelectedComponent(scrollablePane);
        codePane.setCaretPosition(lineStartOffset);
        codePane.requestFocus();
    }
}
