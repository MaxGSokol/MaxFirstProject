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

    }
}
