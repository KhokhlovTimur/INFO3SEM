import java.io.IOException;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        CustomServer server = CustomServer.create(4444);
        server.startServer();
    }
}
