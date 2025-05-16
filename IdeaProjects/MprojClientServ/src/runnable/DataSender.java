package runnable;

import datapacks.FullDataPack;
import serves.ClientServerConnection;
import serves.ConsoleTools;
import serves.DataType;
import source.ClientServerConfig;
import storage.DataStorage;

import java.io.IOException;

public class DataSender implements Runnable {
    private final ClientServerConnection CLIENT_SERVER_CONNECTION;

    public DataSender() throws IOException {
        this.CLIENT_SERVER_CONNECTION = new ClientServerConnection();
    }

    @Override
    public void run() {

        while (!ClientServerConfig.IS_EXIT) {
            FullDataPack fullDataPack = DataStorage.FULL_PACK_STORAGE.pollLast();
            if (fullDataPack != null) {
                checkData(fullDataPack);
                CLIENT_SERVER_CONNECTION.sendAllotOfData(fullDataPack);
            }
        }
        CLIENT_SERVER_CONNECTION.close();
    }

    private void checkData(FullDataPack fullDataPack) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Проверка содержимого пакета перед отправкой.\n");
        stringBuilder.append("Содержимое пакета данных.\n");
        stringBuilder.append("Сигнатура - " + fullDataPack.getSignature() + "\n");
        stringBuilder.append("Имя пользователя - " + fullDataPack.getInputDataPack().getUserName() + "\n");
        stringBuilder.append("Способ вывода данных на сервере - " +
                fullDataPack.getInputDataPack().getFileType().name() + "\n");
        if (fullDataPack.getInputDataPack().getDataType() == DataType.ADVANCE) {
            stringBuilder.append("Выбранные температурные режимы.\n");

            for (String key : fullDataPack.getInputDataPack().getDataMap().keySet()) {
                Integer value = fullDataPack.getInputDataPack().getDataMap().get(key);
                stringBuilder.append(key + " - " + value + " градуса.\n");
            }
        }
        if (fullDataPack.getInputDataPack().getDataType() == DataType.SIMPLE) {
            stringBuilder.append("Выбранный температурный режим - "
                    + fullDataPack.getInputDataPack().getSimpleData() + " градуса.\n");
        }
        stringBuilder.append("Длинна данных в байтах - " + fullDataPack.getDataLength() + "\n");
        stringBuilder.append("CRC32 - " + fullDataPack.getControlSum());
        ConsoleTools.statusMessage(stringBuilder.toString());
    }

}


