import runnable.DataSender;
import runnable.FullDataPackCollector;
import runnable.InputDataCollector;
import serves.Connection;
import serves.ConsoleTools;

import java.io.IOException;
import java.net.Socket;

public class Client {


    public static void main(String[] args) throws IOException {

        intro();

        DataSender dataSender = new DataSender(new serves.Connection());
        Thread thread1 = new Thread(dataSender);
        thread1.start();

        FullDataPackCollector fullDataPackCollector = new FullDataPackCollector();
        Thread thread2 = new Thread(fullDataPackCollector);
        thread2.start();

        InputDataCollector dataCollector = new InputDataCollector();
        Thread thread3 = new Thread(dataCollector);
        thread3.start();


    }

    private static void intro() {
        ConsoleTools.writeMessage(
                "Здравствуйте!\n Вас приветствует программа удаленного управления\n " +
                        "системой домашнего кондиционирования.");
        ConsoleTools.writeMessage("Далее вводите информацию с клавиатуры согласно инструкции.");
    }

    private serves.Connection toSetConnection() {
        ConsoleTools.writeMessage("Выберите способ создания соединения с сервером.");
        ConsoleTools.writeMessage("Если хотите запустить программу в тестовом режиме,\n" +
                " без сетевого соединения. Нажмите || А ||.");
        ConsoleTools.writeMessage("Если хотите установить локальное соединение с сервером. Нажмите || Б ||.");
        ConsoleTools.writeMessage("Если хотите настроить соиденение в ручную. Нажмите || В ||.");

        serves.Connection connection = null;

        switch (ConsoleTools.readLine()) {
            case "А":
                connection = toGetTestConnection();
                break;
            case "Б":
                connection = toGetLocalConnection();
                break;
            case "В":
                connection = toGetManualConnection();
                break;
            default:
                ConsoleTools.exceptionMessage("Введены неверные данные. Повторите ввод.");

        }
        return connection;
    }

    private serves.Connection toGetTestConnection() {
        return new serves.Connection();
    }

    private serves.Connection toGetLocalConnection() {
        serves.Connection connection = null;
        try {
            connection = new serves.Connection(new Socket("localhost", 4004));
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Соединение не установленно!");
        }
        return connection;
    }

    private serves.Connection toGetManualConnection() {
        ConsoleTools.writeMessage("Ручная настройка соединения.");
        String ip = getIp();
        int port = getPort();
        serves.Connection connection = null;
        connection = new serves.Connection();

        try {
            connection = new serves.Connection(new Socket(ip, port));
        } catch (IOException e) {
            serves.ConsoleTools.exceptionMessage("Соединение не установленно!");
        }
        return connection;
    }

    public static String getIp() {
        ConsoleTools.writeMessage("Введите Ip адрес");
        while (true) {
            String ip = ConsoleTools.readLine();
            String[] st = ip.split("\\.");
            if (st.length != 4) {
                ConsoleTools.exceptionMessage("Введен неверный Ip, попробуйте снова.");
            }
            return ip;
        }
    }

    public static int getPort() {
        ConsoleTools.writeMessage("Введите порт");
        return ConsoleTools.readInt();
    }

}
