import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Client {

    public static void main(String[] args) throws IOException {
        DataCollector dataCollector = new DataCollector();
        Thread thread = new Thread(dataCollector);
        thread.start();
      /*  ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);
        int x = Integer.MAX_VALUE;
        int y = 67;
        long z = 656453354;
        ArrayList<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("d");
        strings.add("c");
        String s = "d";
        outputStream.writeInt(x);
        outputStream.writeObject(s);
      //  outputStream.writeInt(y);
      //  outputStream.writeLong(z);
         // outputStream.writeObject(s); // 8 8
       // outputStream.writeObject(strings); // 70 72
                                      //75 abcd 77 a
        byte[] obj = byteArrayOutputStream.toByteArray();

        System.out.println(obj.length);
        М
1143
PLAIN
778070078
23Ночь
34Вечер
33Утро
        */
    }
}
