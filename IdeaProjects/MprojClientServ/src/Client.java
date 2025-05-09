import datapacks.InputDataPack;
import runnable.DataSender;
import runnable.FullDataPackCollector;
import runnable.InputDataCollector;
import serves.ClientServerConnection;
import serves.ConsoleTools;
import serves.DataType;
import storege.ClientConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        DataSender dataSender = new DataSender();
        Thread thread1 = new Thread(dataSender);
        thread1.start();

        FullDataPackCollector fullDataPackCollector = new FullDataPackCollector();
        Thread thread2 = new Thread(fullDataPackCollector);
        thread2.start();

        InputDataCollector dataCollector = new InputDataCollector();
        Thread thread3 = new Thread(dataCollector);
        thread3.start();

    }

}
