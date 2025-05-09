package runnable;

import datapacks.FullDataPack;
import serves.ClientServerConnection;
import serves.ConsoleTools;
import serves.DataType;
import storege.ClientConfig;
import storege.DataStorage;

public class DataSender implements Runnable {
    private final ClientServerConnection CLIENT_SERVER_CONNECTION;

    public DataSender() {
        this.CLIENT_SERVER_CONNECTION = new ClientServerConnection();
    }

    @Override
    public void run() {

        while (!ClientConfig.IS_EXIT) {
            FullDataPack fullDataPack = DataStorage.FULL_PACK_STORAGE.pollLast();
            if (fullDataPack != null) {
                checkData(fullDataPack);

                ClientConfig.IS_SEND = true;
            }
        }

    }

    private void checkData(FullDataPack fullDataPack) {
        ConsoleTools.statusMessage("Проверка содержимого пакета перед отправкой.");
        ConsoleTools.writeMessage("Содержимое пакета данных.");
        ConsoleTools.writeMessage("Сигнатура - " + fullDataPack.getSIGNATURE());
        ConsoleTools.writeMessage("Имя пользователя - "
                + fullDataPack.getINPUT_DATA_PACK().getUSER_NAME());
        ConsoleTools.writeMessage("Способ вывода данных на сервере - "
                + fullDataPack.getINPUT_DATA_PACK().getFILE_TYPE().name());

        if (fullDataPack.getINPUT_DATA_PACK().getDATA_TYPE() == DataType.ADVANCE) {
            ConsoleTools.writeMessage("Выбранные температурные режимы.");

            for (String key : fullDataPack.getINPUT_DATA_PACK().getDataMap().keySet()) {
                Integer value = fullDataPack.getINPUT_DATA_PACK().getDataMap().get(key);
                ConsoleTools.writeMessage(key + " - " + value + " градуса.");
            }
        }

        if (fullDataPack.getINPUT_DATA_PACK().getDATA_TYPE() == DataType.SIMPLE) {
            ConsoleTools.writeMessage("Выбранный температурный режим - "
                    + fullDataPack.getINPUT_DATA_PACK().getSimpleData() + " градуса.");
        }

        ConsoleTools.writeMessage("Длинна данных в байтах - " + fullDataPack.getDATA_LENGTH());
        ConsoleTools.writeMessage("CRC32 - " + fullDataPack.getCONTROL_SUM().getValue());
    }

}


