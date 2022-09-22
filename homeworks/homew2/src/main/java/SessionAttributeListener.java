import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

import java.util.*;

@WebListener
public class SessionAttributeListener implements HttpSessionAttributeListener {
    private static final Map<String, List<String>> CHAT_HISTORY = new HashMap<>();
    private static String chatId;

    public static List<String> getHistory(String chatID) {
        chatId = chatID;
        List<String> history = CHAT_HISTORY.get(chatID);
        if (history != null) {
            return new LinkedList<>(history);
        }
        return Collections.emptyList();
    }


    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        String message = formPredefinedHistoryMessage((String) event.getValue(), event.getSession().getId());
        putToHistory(chatId, message);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        String message = formPredefinedHistoryMessage((String) event.getValue(), event.getSession().getId());
        putToHistory(chatId, message);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        String message = formPredefinedHistoryMessage(
                (String) event.getSession().getAttribute(event.getName()), event.getSession().getId());
        putToHistory(chatId, message);
    }

    private void putToHistory(String chatID, String message) {
        CHAT_HISTORY.computeIfAbsent(chatID, key -> new LinkedList<>()).add(message);
    }

    private String formPredefinedHistoryMessage(String value, String sessionID) {
        Date date = new Date();
        return String.format(sessionID + " (" + date.getHours() + ":" + date.getMinutes() + ") : " + value);
    }
}
