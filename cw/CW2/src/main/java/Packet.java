
import lombok.AllArgsConstructor;
import lombok.Data;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
public class Packet {

    private static final byte HEADER_1 = (byte) 0xe4;
    private static final byte HEADER_2 = (byte) 0x15;

    private static final byte FOOTER_1 = (byte) 0x00;
    private static final byte FOOTER_2 = (byte) 0x90;

    private byte type;
    private byte subtype;

    private List<Field> fields = new ArrayList<>();

    private Packet() {
    }

    public static Packet create(byte type, byte subtype) {
        Packet packet = new Packet();
        packet.setType(type);
        packet.setSubtype(subtype);
        return packet;
    }

    public byte[] packetToByteArray() {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

            baos.write(new byte[]{HEADER_1, HEADER_2});

            baos.write(this.type);
            baos.write(this.subtype);

            for (Field field : this.fields) {
                baos.write(field.getId());
                baos.write(field.getSize());
                baos.write(field.getContent());

            }

            baos.write(new byte[]{FOOTER_1, FOOTER_2});

            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Packet parse(byte[] reformedPacket) {
//        if (reformedPacket[0] != HEADER_1 && reformedPacket[1] != HEADER_2
//                || reformedPacket[reformedPacket.length - 1] != FOOTER_2 && reformedPacket[reformedPacket.length - 2] != FOOTER_1) {
//            throw new IllegalArgumentException("This is not my packet");
//        }

        byte type = reformedPacket[2];
        byte subtype = reformedPacket[3];

        Packet packet = Packet.create(type, subtype);
        //packet header, header, type, subtype
        int offset = 4;

        while (true) {
            if (reformedPacket.length - 2 <= offset) {
                return packet;
            }

            byte id = reformedPacket[offset];
            byte size = reformedPacket[offset + 1];
            byte[] content = new byte[Byte.toUnsignedInt(size)];


            if (size != 0) {
                System.arraycopy(reformedPacket, offset + 2, content, 0, Byte.toUnsignedInt(size));
            }

            Field field = new Field(id, size, content);
            field.setContent(content);

            packet.getFields().add(field);

            //2 - id, size
            offset += size + 2;
        }
    }

    private Field getField(int id) {
        Optional<Field> field = fields.stream().filter(x -> x.id == (byte) id).findFirst();
        if (field.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return field.get();
    }

    public void setContentToPacketField(Object content, int id) {
        Field field;
        try {
            field = getField(id);
        } catch (IllegalArgumentException e) {
            field = new Field((byte) id);
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {

            oos.writeObject(content);

            byte[] data = baos.toByteArray();
            field.setSize((byte) data.length);
            field.setContent(data);

        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        fields.add(field);
    }

    public <T> T getContentFromPacketField(int id, Class<T> clazz) {
        Field field = getField(id);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(field.getContent());
             ObjectInputStream ois = new ObjectInputStream(bais)) {

            return (T) ois.readObject();

        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean isEOP(byte[] data, int counter) {
        return data[counter] == FOOTER_2 && data[counter - 1] == FOOTER_1;
    }

    @Data
    @AllArgsConstructor
    public static class Field {
        private byte id;
        private byte size;
        byte[] content = new byte[size];

        public Field(byte id) {
            this.id = id;
        }

    }
}
