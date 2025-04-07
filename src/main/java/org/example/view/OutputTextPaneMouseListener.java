package org.example.view;

import lombok.AllArgsConstructor;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.Element;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

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
        String fullPackageName = (String) attributeSet.getAttribute("className");

        if(line == null || fullPackageName == null)
            return;

        if(fullPackageName.equals("script.kts")) {
            editorView.showCodePane(line);
            return;
        }

        String[] classNameParts = fullPackageName.split("/");

        String moduleName = classNameParts[0];
        String className = classNameParts[1].replace(".", "/");

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("https://raw.githubusercontent.com/openjdk/jdk/master/src/" + moduleName + "/share/classes/" + className + ".java"))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            editorView.addTab(className, response.body(), line);
        } catch (URISyntaxException | InterruptedException | IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
