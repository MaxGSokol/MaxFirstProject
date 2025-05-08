package runnable;

import datapacks.InputDataPack;
import serves.ConsoleTools;
import serves.Info;
import storege.InputDataStorage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InputDataCollector implements Runnable {

    @Override
    public void run() {
        while (true) {
            InputDataPack inputDataPack = null;

            String userName = toGetUsername();

            Info fileInfo = toGetDataType();

            Map<String, Integer> mapDate = null;
            int simpleDate = 0;
            switch (toGetData()) {
                case "А":
                    simpleDate = toGetIntDate();
                    inputDataPack = new InputDataPack(userName, fileInfo, simpleDate, Info.SIMPLE);
                    break;
                case "Б":
                    mapDate = toGetMapDate();
                    inputDataPack = new InputDataPack(userName, fileInfo, mapDate, Info.ADVANCE);
                    break;
            }

            InputDataStorage.inputDataStorage.addFirst(inputDataPack);
        }

    }


    private String toGetUsername() {
        ConsoleTools.writeMessage("Введите имя пользователя.");
        return ConsoleTools.readLine();
    }

    private Info toGetDataType() {
        ConsoleTools.writeMessage("Если вы хотите вывести данные в косоль введите \"кон\"");
        ConsoleTools.writeMessage("Если вы хотите сохранит данные в обычном файле введите \"ОФ\"");
        ConsoleTools.writeMessage("Если вы хотите сохранить данные в формате \"json\" введите \"ДЖ\"");

        while (true) {
            switch (ConsoleTools.readLine()) {
                case "кон":
                    return Info.CONSOLE;
                case "ОФ":
                    return Info.PLAIN;
                case "ДЖ":
                    return Info.JSON;
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

    private int toGetIntDate() {
        ConsoleTools.writeMessage("Введите желаемую температуру.");
        while (true) {
            int simpleDate = ConsoleTools.readInt();
            if (simpleDate >= 16 && simpleDate <= 35) {
                return simpleDate;
            } else {
                ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");
            }
        }
    }

    private Map<String, Integer> toGetMapDate() {
        Map<String, Integer> mapDate = new ConcurrentHashMap<>();
        ConsoleTools.writeMessage("Введите желаемую температуру на первую половину дня.");
        while (true) {
            int mor = ConsoleTools.readInt();
            if (mor >= 16 && mor <= 35) {
                mapDate.put("Утро", mor);
                break;
            } else {
                ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");
            }
        }
        ConsoleTools.writeMessage("Введите желаемую температуру на вторую половину дня.");
        while (true) {
            int mor = ConsoleTools.readInt();
            if (mor >= 16 && mor <= 35) {
                mapDate.put("Вечер", mor);
                break;
            } else {
                ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");
            }
        }
        ConsoleTools.writeMessage("Введите желаемую температуру на ночь.");
        while (true) {
            int mor = ConsoleTools.readInt();
            if (mor >= 16 && mor <= 35) {
                mapDate.put("Ночь", mor);
                break;
            } else {
                ConsoleTools.writeMessage("Введены неверные данные. Повторите ввод");
            }
        }
        return mapDate;
    }

}
