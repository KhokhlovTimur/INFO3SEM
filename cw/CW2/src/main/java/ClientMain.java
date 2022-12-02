import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        CustomClient client = CustomClient.create("localhost", 4444);
        client.sendMessage();

    }

}
