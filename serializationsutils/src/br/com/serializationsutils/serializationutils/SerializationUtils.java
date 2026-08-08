package br.com.serializationsutils.serializationutils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class SerializationUtils {

    private SerializationUtils() {}

    public static void toFile(Object object, Path file) {
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file))) {
            oos.writeObject(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromFile(Path file) {
        try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file))) {
            return  (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
