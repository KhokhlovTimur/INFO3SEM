import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class CustomServer {
    private Integer port;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    private CustomServer() {
    }

    public static CustomServer create(int port) throws IOException {
        CustomServer server = new CustomServer();
        server.serverSocket = new ServerSocket(port);
        server.port = port;
        return server;
    }

    public void startServer() {
        try {

            clientSocket = serverSocket.accept();
            try (OutputStream outputStream = clientSocket.getOutputStream();
                 InputStream inputStream = clientSocket.getInputStream();) {

                Packet packetWithMess = Packet.parse(readInput(inputStream));
                Packet packKey = Packet.parse(readInput(inputStream));

                String mess = packetWithMess.getContentFromPacketField(0, String.class);
                int key = Integer.parseInt(packKey.getContentFromPacketField(0, String.class));
                System.out.println("Получил сообщение... " + mess);

                String messToClient = getMessage(mess, key);
                Packet packet = Packet.create((byte) 1, (byte) 1);
                packet.setContentToPacketField(messToClient, 0);
                outputStream.write(packet.packetToByteArray());
                outputStream.flush();

                System.out.println(messToClient);

                System.out.println("Отправляю...");

                System.out.println("Wait...");
                Packet answer = Packet.parse(readInput(inputStream));

                if (answer.getType() == (byte) 1 && answer.getSubtype() == (byte) 2){
                    System.out.println("Go!!");
                }
                else if (answer.getType() == (byte) 2){
                    System.exit(0);
                }

                Packet packet1 = Packet.parse(readInput(inputStream));
                int diap = Integer.parseInt(packet1.getContentFromPacketField(0, String.class));

                int leftBound = 0;
                int rightBound = diap;

                int myN = (diap) / 2;
                Packet umber = Packet.create((byte) 1, (byte) 1);
                System.out.println(myN);
                umber.setContentToPacketField(String.valueOf(myN), 0);
                outputStream.write(umber.packetToByteArray());
                outputStream.flush();

                while (true) {

                    Packet client = Packet.parse(readInput(inputStream));
                    String result = client.getContentFromPacketField(0, String.class);

                    if (result.equals("lower")){
                        if (myN >= leftBound) {
                            rightBound = myN- 1;
                        } else {
                            rightBound--;
                        };
                        myN = (int) (leftBound + 0.5 * (rightBound - leftBound));

                    }
                    else if (result.equals("higher")){
                        if (myN <= rightBound) {
                            leftBound = myN + 1;
                        } else {
                            leftBound++;
                        }
                        myN = (int) (rightBound - 0.5 * (rightBound - leftBound));
                    }

                    else if (client.getType() == (byte) 3){
                        System.out.println("game over");
                        System.exit(0);
                    }

                    System.out.println(myN);
                    Packet nn = Packet.create((byte) 1, (byte) 2);
                    nn.setContentToPacketField(String.valueOf(myN), 0);
                    outputStream.write(nn.packetToByteArray());
                    outputStream.flush();


                }
            }

        } catch (IOException e) {
            System.out.println("Игра окончена");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String guessNumber(int n, int left, int right) {


        return String.valueOf(n);
    }

    private String getMessage(String message, int key) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            builder.append((char) ((char) message.charAt(i) - key));
        }

        return builder.toString();
    }


    public byte[] readInput(InputStream inputStream) throws IOException {

        int b;
        byte[] data = new byte[10];
        int counter = 0;

        while ((b = inputStream.read()) > -1) {

            data[counter++] = (byte) b;

            if (counter >= data.length) {
                data = extendArray(data);
            }

            if (counter > 1 && Packet.isEOP(data, counter - 1)) {
                break;
            }
        }

        byte[] allData = new byte[counter];
        System.arraycopy(data, 0, allData, 0, counter);
        return allData;
    }

    public byte[] extendArray(byte[] oldArr) {
        byte[] newArr = new byte[oldArr.length * 2];
        System.arraycopy(oldArr, 0, newArr, 0, oldArr.length);
        return newArr;
    }

}
