package runnable;

import dataclasses.InputData;
import dataenums.DayTimeSettings;
import dataenums.OutputFileType;
import serves.ConsoleTools;

import java.util.TreeMap;

import static source.SingletonClientConfig.CLIENT_CONFIG;
import static storage.SingletonClientDataStorage.CLIENT_DATA_STORAGE;

public class InputDataCollector implements Runnable {

    @Override
    public void run() {
        intro();

        while (!CLIENT_CONFIG.isExit()) {

            String userName = CLIENT_CONFIG.getUserName();
            OutputFileType fileDataType = getDataType();
            InputData inputData = getInputData(userName, fileDataType);
            CLIENT_DATA_STORAGE.putInputDataToStorage(inputData);

            stopThreads();
        }
    }

    private void intro() {
        ConsoleTools.writeMessage(
                "Приветствуем вас " + CLIENT_CONFIG.getUserName()
                        + " !\n Вы запустили программу удаленного управления\n "
                        + "системой домашнего кондиционирования.");
        ConsoleTools.writeMessage("Далее вводите информацию с клавиатуры согласно инструкции.");
    }

    private OutputFileType getDataType() {
        ConsoleTools.writeMessage("Если вы хотите вывести данные в консоль введите || 1 ||.");
        ConsoleTools.writeMessage("Если вы хотите сохранит данные в обычном файле введите || 2 ||.");
        ConsoleTools.writeMessage("Если вы хотите сохранить данные в формате \"json\" введите || 3 ||.");

        while (true) {
            switch (ConsoleTools.readInt()) {
                case 1:
                    return OutputFileType.CONSOLE;
                case 2:
                    return OutputFileType.PLAIN;
                case 3:
                    return OutputFileType.JSON;
                default:
                    ConsoleTools.exceptionMessage("Некорректный ввод. Попробуйте еще раз.");
            }
        }
    }

    private InputData getInputData(String userName, OutputFileType outputFileType) {
        ConsoleTools.writeMessage("Если желаете выставить постоянную температуру введите || 1 ||.");
        ConsoleTools.writeMessage("Если желаете настроить разную температуру на утро, день и ночь, введите || 2 ||");
        ConsoleTools.writeMessage("Наше оборудование поддерживает температуру от 16 до 35 градусов цельсия.");
        TreeMap<DayTimeSettings, Integer> mapData = new TreeMap<>();
        while (mapData.isEmpty()) {
            switch (ConsoleTools.readInt()) {
                case 1:
                    mapData = getSimpleMapDate();
                    break;
                case 2:
                    mapData = getAdvanceMapDate();
                    break;
                default:
                    ConsoleTools.exceptionMessage("Некорректный ввод. Попробуйте еще раз.");
            }
        }
        return new InputData(userName, outputFileType, mapData);
    }

    private Integer getIntData() {
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

    private TreeMap<DayTimeSettings, Integer> getSimpleMapDate() {
        TreeMap<DayTimeSettings, Integer> mapData = new TreeMap<>();
        mapData.put(DayTimeSettings.WHOLE_DAY, getIntData());
        return mapData;
    }

    private TreeMap<DayTimeSettings, Integer> getAdvanceMapDate() {
        TreeMap<DayTimeSettings, Integer> mapData = new TreeMap<>();

        ConsoleTools.writeMessage("Установка режима на первую половину дня.");
        int morn = getIntData();
        mapData.put(DayTimeSettings.MORNING, morn);

        ConsoleTools.writeMessage("Установка режима на вторую половину дня.");
        int day = getIntData();
        mapData.put(DayTimeSettings.DAY, day);

        ConsoleTools.writeMessage("Установка режима на ночное время.");
        int night = getIntData();
        mapData.put(DayTimeSettings.NIGHT, night);

        return mapData;
    }

    private void stopThreads() {
        ConsoleTools.writeMessage("Если хотите завершить программу нажмите || 1 ||.");
        ConsoleTools.writeMessage("Для продолжения нажмите любую другую клавишу.");
        if (ConsoleTools.readLine().equals("1")) {
            CLIENT_CONFIG.setExit(true);
        }
    }

}
