package com.example.notes;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.*;

public class AttributeListener implements HttpSessionAttributeListener {
    private static Map<String, List<String>> messages = new HashMap<>();
    private static String logiN;
    public static List<String> get(String login) {
        logiN = login;
        List<String> mess = messages.get(login);
        if (mess != null) {
            return new ArrayList<>(mess);
        }
        return Collections.emptyList();
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        String message = formPredefinedHistoryMessage((String) event.getValue(), event.getSession().getId());
        putToHistory(logiN, message);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String message = formPredefinedHistoryMessage((String) event.getValue(), event.getSession().getId());
        putToHistory(logiN, message);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        String message = formPredefinedHistoryMessage(
                (String) event.getSession().getAttribute(event.getName()), event.getSession().getId());
        putToHistory(logiN, message);
    }

    private void putToHistory(String chatID, String message) {
       messages.computeIfAbsent(chatID, key -> new LinkedList<>()).add(message);
    }

    private String formPredefinedHistoryMessage(String value, String sessionID) {
        Date date = new Date();
        return String.format(sessionID + " (" + date.getHours() + ":" + date.getMinutes() + ") : " + value);
    }
}
