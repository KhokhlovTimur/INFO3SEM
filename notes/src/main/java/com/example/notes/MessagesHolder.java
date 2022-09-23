package com.example.notes;

import java.util.*;

public class MessagesHolder {
    private static Map<String, List<String>> messages = new HashMap<>();

    public static void putMessageToMap(String login, String message) {
        messages.computeIfAbsent(login, k -> new ArrayList<>()).add(message);
    }

    public static List<String> get(String login) {
        List<String> mess = messages.get(login);
        if (mess!=null){
            return new ArrayList<>(mess);
        }
        return Collections.emptyList();
    }

}
