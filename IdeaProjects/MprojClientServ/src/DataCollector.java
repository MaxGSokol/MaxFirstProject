import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DataCollector implements Runnable {

    @Override
    public void run() {
     intro();
     toGetDefaultConnection();
     DataPackCollector dataPack = new DataPackCollector();
        String userName = toGetUsername();
        dataPack.setUserName(userName);
        DataType dataType = toGetDataType();
        dataPack.setDataType(dataType);
        Map<String,Integer> mapDate = null;
        int simpleDate = 0;
        switch (toGetData()){
            case "А":
                simpleDate = toGetIntDate();
                dataPack.setSimpleData(simpleDate);
                break;
            case "Б":
                mapDate = toGetMapDate();
                dataPack.setDataMap(mapDate);
                break;
        }

Thread thread = new Thread(dataPack);
        thread.start();
    }

    private void intro(){
        ConsoleTools.writeMessage(
           "Здравствуйте!\n Вас приветствует программа удаленного управления\n системой домашнего кондиционирования.");
        ConsoleTools.writeMessage("Далее вводите информацию с клавиатуры согласно инструкции.");
    }

    private void toGetDefaultConnection() {
        ConsoleTools.writeMessage("Устанавливаем соединение с сервером.");
      //  Connection connection = null;
        // connection = new Connection(new Socket("localhost",4004));
      //  connectionRun = new ConnectionRun();
       // Thread thread = new Thread(connectionRun);
       // thread.start();
    }
    private void toGetConnection(){
        ConsoleTools.writeMessage("Для начала работы нужно установить соиденение с сервером.");
        String ip = Connection.getIp();
        int port = Connection.getPort();
        Socket socket = null;
        Connection connection = null;
        connection = new Connection();
        /*
        try {
            socket = new Socket(ip,port);
            connection = new Connection();
        } catch (IOException e) {
            ConsoleTools.writeMessage("Ошибка соединения!");
        }*/
        Thread thread = new Thread(connection);
        thread.start();


    }


    private String toGetUsername(){
        ConsoleTools.writeMessage("Введите имя пользователя.");
    return ConsoleTools.readLine();
    }

    private DataType toGetDataType(){
        ConsoleTools.writeMessage("Если вы хотите вывести данные в косоль введите \"кон\"");
        ConsoleTools.writeMessage("Если вы хотите сохранит данные в обычном файле введите \"ОФ\"");
        ConsoleTools.writeMessage("Если вы хотите сохранить данные в формате \"json\" введите \"ДЖ\"");

        while (true){
            switch (ConsoleTools.readLine()){
                case "кон":
                     return DataType.CONSOLE;
                case "ОФ":
                    return DataType.PLAIN;
                case "ДЖ":
                    return  DataType.JSON;
                default:
                    ConsoleTools.writeMessage("Некоректный ввод. Попробуйте еще раз.");
            }
        }
    }
    private String toGetData() {
        ConsoleTools.writeMessage("Если желаете выставить постоянную температуру введите \"А\" ");
        ConsoleTools.writeMessage("Если желаете настроить разную температуру на утро, день и ночь, введите \"Б\" ");
        ConsoleTools.writeMessage("Наше оборудование поддерживает температуру от 16 до 35 градусов цельсия.");
        while (true) {
            switch (ConsoleTools.readLine()) {
                case "А":
                    return "А";
                case "Б":
                    return "Б";
                default:
                    ConsoleTools.writeMessage("Некоректный ввод. Попробуйте еще раз.");
            }
        }
    }
    private int toGetIntDate(){
        ConsoleTools.writeMessage("Введите желаемую температуру.");
        while (true) {
            int simpleDate = ConsoleTools.readInt();
            if (simpleDate >= 16 && simpleDate <= 35) {
                return simpleDate;
            } else {ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");}
        }
    }

    private Map<String,Integer> toGetMapDate(){
        Map<String,Integer> mapDate = new ConcurrentHashMap<>();
        ConsoleTools.writeMessage("Введите желаемую температуру на первую половину дня.");
        while (true){
        int mor = ConsoleTools.readInt();
        if (mor >= 16 && mor <= 35){
        mapDate.put("Утро",mor);
        break;
        } else {ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");}
        }
        ConsoleTools.writeMessage("Введите желаемую температуру на вторую половину дня.");
        while (true){
            int mor = ConsoleTools.readInt();
            if (mor >= 16 && mor <= 35){
                mapDate.put("Вечер",mor);
                break;
            } else {ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");}
        }
        ConsoleTools.writeMessage("Введите желаемую температуру на ночь.");
        while (true){
            int mor = ConsoleTools.readInt();
            if (mor >= 16 && mor <= 35){
                mapDate.put("Ночь",mor);
                break;
            } else {ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");}
        }
        return mapDate;
    }

}
