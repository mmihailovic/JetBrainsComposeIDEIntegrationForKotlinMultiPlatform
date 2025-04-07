package org.example.view;

import lombok.Getter;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.Objects;

@Getter
public class ScrollablePane extends JScrollPane {
    private final String className;
    private final JTextPane textPane;

    public ScrollablePane(JTextPane textPane, String className, boolean editable) {
        this.className = className;
        this.setViewportView(textPane);
        this.textPane = textPane;
        this.textPane.setEditable(editable);
        this.styleCodePane();
        this.styleScrollBar(this.getVerticalScrollBar());
        this.styleScrollBar(this.getHorizontalScrollBar());
        this.setBorder(new LineBorder(new Color(30, 31, 34)));
    }

    private void styleScrollBar(JScrollBar scrollBar) {
        scrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(90, 90, 94);
                trackColor = Color.DARK_GRAY;
                thumbDarkShadowColor = Color.BLACK;
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
    }

    private void styleCodePane() {
        textPane.setBackground(new Color(30,31,34));
        textPane.setCaretColor(new Color(215, 223, 227));
        textPane.setForeground(new Color(215, 223, 227));
        textPane.getCaret().setVisible(true);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ScrollablePane that = (ScrollablePane) o;
        return Objects.equals(className, that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(className);
    }
}
