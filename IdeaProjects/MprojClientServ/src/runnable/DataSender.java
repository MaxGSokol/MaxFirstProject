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
        ConsoleTools.writeMessage("Сигнатура - " + fullDataPack.getSignature());
        ConsoleTools.writeMessage("Имя пользователя - "
                + fullDataPack.getInputDataPack().getUserName());
        ConsoleTools.writeMessage("Способ вывода данных на сервере - "
                + fullDataPack.getInputDataPack().getFileType().name());

        if (fullDataPack.getInputDataPack().getDataType() == DataType.ADVANCE) {
            ConsoleTools.writeMessage("Выбранные температурные режимы.");

            for (String key : fullDataPack.getInputDataPack().getDataMap().keySet()) {
                Integer value = fullDataPack.getInputDataPack().getDataMap().get(key);
                ConsoleTools.writeMessage(key + " - " + value + " градуса.");
            }
        }

        if (fullDataPack.getInputDataPack().getDataType() == DataType.SIMPLE) {
            ConsoleTools.writeMessage("Выбранный температурный режим - "
                    + fullDataPack.getInputDataPack().getSimpleData() + " градуса.");
        }

        ConsoleTools.writeMessage("Длинна данных в байтах - " + fullDataPack.getDataLength());
        ConsoleTools.writeMessage("CRC32 - " + fullDataPack.getControlSum().getValue());
    }

}


