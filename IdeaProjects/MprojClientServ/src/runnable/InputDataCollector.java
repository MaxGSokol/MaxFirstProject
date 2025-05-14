package runnable;

import datapacks.InputDataPack;
import serves.ConsoleTools;
import serves.DataType;
import source.ClientServerConfig;
import storage.DataStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InputDataCollector implements Runnable {

    @Override
    public void run() {

        intro();

        while (!ClientServerConfig.IS_EXIT) {

            String userName = ClientServerConfig.USER_NAME;
            DataType fileDataType = getDataType();
            InputDataPack inputDataPack = getDataPack(userName, fileDataType);
            DataStorage.INPUT_DATA_STORAGE.addFirst(inputDataPack);

            stopThreads();
        }

    }

    private void intro() {
        ConsoleTools.writeMessage(
                "Приветствуем вас " + ClientServerConfig.USER_NAME
                        + " !\n Вы запустили программу удаленного управления\n "
                        + "системой домашнего кондиционирования.");
        ConsoleTools.writeMessage("Далее вводите информацию с клавиатуры согласно инструкции.");
    }

    private DataType getDataType() {
        ConsoleTools.writeMessage("Если вы хотите вывести данные в косоль введите || 1 ||.");
        ConsoleTools.writeMessage("Если вы хотите сохранит данные в обычном файле введите || 2 ||.");
        ConsoleTools.writeMessage("Если вы хотите сохранить данные в формате \"json\" введите || 3 ||.");

        while (true) {
            switch (ConsoleTools.readInt()) {
                case 1:
                    return DataType.CONSOLE;
                case 2:
                    return DataType.PLAIN;
                case 3:
                    return DataType.JSON;
                default:
                    ConsoleTools.exceptionMessage("Некоректный ввод. Попробуйте еще раз.");
            }
        }
    }

    private InputDataPack getDataPack(String userName, DataType dataType) {
        ConsoleTools.writeMessage("Если желаете выставить постоянную температуру введите || 1 ||.");
        ConsoleTools.writeMessage("Если желаете настроить разную температуру на утро, день и ночь, введите || 2 ||");
        ConsoleTools.writeMessage("Наше оборудование поддерживает температуру от 16 до 35 градусов цельсия.");
        while (true) {
            switch (ConsoleTools.readInt()) {
                case 1:
                    return new InputDataPack(
                            userName,
                            dataType,
                            getIntDate(),
                            DataType.SIMPLE
                    );
                case 2:
                    return new InputDataPack(
                            userName,
                            dataType,
                            getMapDate(),
                            DataType.ADVANCE
                    );
                default:
                    ConsoleTools.exceptionMessage("Некоректный ввод. Попробуйте еще раз.");
            }
        }
    }

    private int getIntDate() {
        ConsoleTools.writeMessage("Введите желаемую температуру.");
        while (true) {
            int simpleDate = ConsoleTools.readInt();
            if (simpleDate >= 16 && simpleDate <= 35) {
                return simpleDate;
            } else {
                ConsoleTools.exceptionMessage("Введены неверные данные. Повторите ввод");
            }
        }
    }

    private Map<String, Integer> getMapDate() {
        Map<String, Integer> mapDate = new ConcurrentHashMap<>();

        ConsoleTools.writeMessage("Установка режима на первую половину дня.");
        int morn = getIntDate();
        mapDate.put("Утро", morn);

        ConsoleTools.writeMessage("Установка режима на вторую половину дня.");
        int day = getIntDate();
        mapDate.put("Вечер", day);

        ConsoleTools.writeMessage("Установка режима на ночное время.");
        int night = getIntDate();
        mapDate.put("Ночь", night);

        return mapDate;
    }

    private void stopThreads() {
        ConsoleTools.writeMessage("Если хотите завершить программу нажмите || 1 ||.");
        ConsoleTools.writeMessage("Для продолжения нажмите любую другую клавишу.");
        if (ConsoleTools.readLine().equals("1")) {
            ClientServerConfig.IS_EXIT = true;
        }
    }

}
