package klasy;
import java.io.*;
class Serializator implements Serializable{

    <T extends Serializable> void save(T object){
        try {
            FileOutputStream fos = new FileOutputStream("src/serializacja.txt");
            try (ObjectOutputStream output = new ObjectOutputStream(fos)) {
                output.writeObject(object);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    <T> T read(){
        try {
            FileInputStream fis = new FileInputStream("src/serializacja.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            T readObject = (T) ois.readObject();

            return readObject;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}