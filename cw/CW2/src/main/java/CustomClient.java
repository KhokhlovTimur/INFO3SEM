import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CustomClient {
    private Socket clientSocket;

    private CustomClient() {
    }

    public static CustomClient create(String host, int port) throws IOException {
        CustomClient client = new CustomClient();
        client.clientSocket = new Socket(host, port);
        return client;
    }

    public void sendMessage() {
        try (OutputStream outputStream = clientSocket.getOutputStream();
             InputStream inputStream = clientSocket.getInputStream()){


            Packet packet = Packet.create((byte) 1, (byte) 1);
            Scanner sc = new Scanner(System.in);
            System.out.println("Введите сообщение:");
            String message = sc.nextLine();

            System.out.println("Введите ключ для сдвига: ");
            int key = Integer.parseInt(sc.next());
            packet.setContentToPacketField(cypherMessage(message, key), 0);
            outputStream.write(packet.packetToByteArray());
            outputStream.flush();

            Packet packetWithkey  = Packet.create((byte) 1, (byte) 1);
            packetWithkey.setContentToPacketField(String.valueOf(key), 0);
            outputStream.write(packetWithkey.packetToByteArray());
            outputStream.flush();

            Packet packetFromServ = Packet.parse(readInput(inputStream));
            String answer = packetFromServ.getContentFromPacketField(0, String.class);
            System.out.println("Сервер отправил: " + answer);
            System.out.println("Ожидалось: " + message);


            if (!answer.equals(message)){
                Packet packet1 = Packet.create((byte) 2, (byte) 1);
                outputStream.write(packet1.packetToByteArray());
                outputStream.flush();
            }

            Packet packet1 = Packet.create((byte) 1, (byte) 2);
            outputStream.write(packet1.packetToByteArray());
            outputStream.flush();

            System.out.println("Игра начинается!");

            //загадал число
            System.out.println("Выберите диапазон: ");
            String diap = sc.next();
            System.out.println("Выберите число: ");
            String number = sc.next();

            Packet packet2 = Packet.create((byte) 1, (byte) 1);
            packet2.setContentToPacketField(diap, 0);
            outputStream.write(packet2.packetToByteArray());
            outputStream.flush();
//            String num;

            while (true) {

                Packet serv = Packet.parse(readInput(inputStream)) ;
                String servNum = serv.getContentFromPacketField(0, String.class);
                System.out.println("Сервер ввел: " + servNum);

                if (servNum.equals(number)){
                    Packet gg = Packet.create((byte) 3, (byte) 1);
                    outputStream.write(gg.packetToByteArray());
                    outputStream.flush();
                    System.out.println("Congr");
                }
                else {
                    Packet notGg = Packet.create((byte) 1, (byte) 1);
                    if (Integer.parseInt(servNum) > Integer.parseInt(number)){
                        notGg.setContentToPacketField("lower", 0);
                    }
                    else {
                        notGg.setContentToPacketField("higher", 0);
                    }

                    outputStream.write(notGg.packetToByteArray());
                    outputStream.flush();
                }


            }

        } catch (IOException e) {
            System.out.println("Игра окончена");
            System.exit(0);
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String cypherMessage(String message, int key){
        StringBuilder mess = new StringBuilder();
        for (int i = 0; i < message.length(); i++){
            mess.append((char) ((char) message.charAt(i) + key));
        }

        return mess.toString();
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
