import runnable.DataSender;
import runnable.FullDataCollector;
import runnable.InputDataCollector;

import java.io.IOException;

public class Client {

    public static void main(String[] args) throws IOException {
        DataSender dataSender = new DataSender();
        Thread thread1 = new Thread(dataSender);
        thread1.start();

        FullDataCollector fullDataPackCollector = new FullDataCollector();
        Thread thread2 = new Thread(fullDataPackCollector);
        thread2.start();

        InputDataCollector dataCollector = new InputDataCollector();
        Thread thread3 = new Thread(dataCollector);
        thread3.start();
    }

}
