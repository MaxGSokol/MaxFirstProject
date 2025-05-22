package runnable;

import dataclasses.FullData;
import dataenums.DayTimeSettings;
import serves.ClientServerConnection;
import serves.ConsoleTools;

import java.io.IOException;

import static source.SingletonClientConfig.CLIENT_CONFIG;
import static storage.SingletonClientDataStorage.CLIENT_DATA_STORAGE;

public class DataSender implements Runnable {
    private final ClientServerConnection clientServerConnection;

    public DataSender() throws IOException {
        this.clientServerConnection = new ClientServerConnection();
    }

    @Override
    public void run() {
        while (!CLIENT_CONFIG.isExit()) {
            FullData fullData = CLIENT_DATA_STORAGE.getFullDataPackFromStorage();
            if (fullData != null) {
                checkData(fullData);
                clientServerConnection.send(fullData);
            }
        }
        clientServerConnection.close();


    }

    private void checkData(FullData fullData) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Проверка содержимого пакета перед отправкой.\n")
                .append("Содержимое пакета данных.\n")
                .append("Сигнатура - ")
                .append(fullData.getSignature())
                .append("\n")
                .append("Имя пользователя - ")
                .append(fullData.getInputData().getUserName())
                .append("\n")
                .append("Способ вывода данных на сервере - ")
                .append(fullData.getInputData().getFileType().name())
                .append("\n")
                .append("Выбранные температурные режим.\n");
        for (DayTimeSettings key : fullData.getInputData().getDataMap().keySet()) {
            Integer value = fullData.getInputData().getDataMap().get(key);
            stringBuilder.append(key).append(" - ").append(value).append(" град.\n");
        }
        stringBuilder.append("Длинна данных в байтах - ")
                .append(fullData.getDataLength())
                .append("\n")
                .append("CRC32 - ")
                .append(fullData.getControlSum());
        ConsoleTools.statusMessage(stringBuilder.toString());
    }

}


