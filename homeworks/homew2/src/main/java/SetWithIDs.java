import java.util.HashSet;
import java.util.Set;

public class SetWithIDs {
    private static Set<String> chatIds = new HashSet();
    public static Set<String> getSet() {
        return chatIds;
    }

    public static void addToSet(String chatId) {
        if (chatId != null) {
            chatIds.add(chatId);
        }
    }
}
