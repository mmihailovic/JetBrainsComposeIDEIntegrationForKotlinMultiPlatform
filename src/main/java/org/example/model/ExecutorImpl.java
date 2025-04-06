package org.example.model;

import org.example.model.process.ProcessExecutor;
import org.example.observer.Subscriber;
import org.example.observer.notification.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExecutorImpl implements Executor {
    private static final String SCRIPT_NAME = "script.kts";
    private final ProcessExecutor processExecutor;
    private final List<Subscriber> subscribers;
    private Pattern pattern;

    public ExecutorImpl(ProcessExecutor processExecutor) {
        this.processExecutor = processExecutor;
        this.subscribers = new ArrayList<>();
        makeRegex();
    }

    @Override
    public void executeScript(String script) {
        try {
            File scriptFile = new File(SCRIPT_NAME);

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(scriptFile))) {
                writer.write(script);
            }

            Process process = processExecutor.executeProcess("cmd", "/c", "kotlinc", "-script", scriptFile.getAbsolutePath());
            new ExecutorSwingWorker(process.getInputStream(), this::readOutput).execute();
            new ExecutorSwingWorker(process.getErrorStream(), this::readErrors).execute();

            int exitCode = process.waitFor();
            notifySubscribers(new ShowOutputNotification("Process finished with exit code " + exitCode));

            String status = exitCode == 0 ? "SUCCESS" : "FAILURE";
            notifySubscribers(new ShowStatusNotification(status));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void findKeywords(String script) {
        Matcher matcher = pattern.matcher(script.replace("\r", ""));

        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            notifySubscribers(new HighlightKeywordNotification(start, end));
        }
    }

    @Override
    public void readOutput(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                notifySubscribers(new ShowOutputNotification(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readErrors(InputStream inputStream) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                int start = line.indexOf("script.kts:");
                if(start < 0) {
                    notifySubscribers(new ShowErrorNotification(line));
                    continue;
                }

                int startOfLineNumber = start + SCRIPT_NAME.length() + 1;
                int lineNumber = 0;
                int i;

                for(i = startOfLineNumber; i < line.length(); i++) {
                    if(!Character.isDigit(line.charAt(i))) {
                        break;
                    }
                    lineNumber = lineNumber * 10 + line.charAt(i) - '0';
                }

                String preLinkPart = line.substring(0, start);
                String linkPart;
                String postLinkPart;

                if(line.charAt(i) == ')') {
                    linkPart = line.substring(start, i);
                    postLinkPart = line.substring(i);
                }
                else {
                    int endOfLinkPart = line.indexOf(':', i + 1);
                    linkPart = line.substring(start, endOfLinkPart);
                    postLinkPart = line.substring(endOfLinkPart);
                }

                LinkedError linkedError = new LinkedError(preLinkPart, linkPart, postLinkPart, lineNumber);
                notifySubscribers(new ShowLinkedErrorNotification(linkedError));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addSubscriber(Subscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void notifySubscribers(Notification notification) {
        for(Subscriber subscriber: this.subscribers) {
            subscriber.update(notification);
        }
    }

    private void makeRegex() {
        StringBuilder regexBuilder = new StringBuilder();
        for (String keyword : Keywords.KEYWORDS) {
            if (!regexBuilder.isEmpty()) {
                regexBuilder.append("|");
            }
            regexBuilder.append("\\b").append(keyword).append("\\b");
        }

        this.pattern = Pattern.compile(regexBuilder.toString());
    }
}
