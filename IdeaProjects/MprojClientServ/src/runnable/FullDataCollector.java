package runnable;

import dataclasses.FullData;
import dataclasses.InputData;
import serves.ConsoleTools;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.CRC32;

import static source.SingletonClientConfig.CLIENT_CONFIG;
import static storage.SingletonClientDataStorage.CLIENT_DATA_STORAGE;

public class FullDataCollector implements Runnable {

    @Override
    public void run() {
        while (!CLIENT_CONFIG.isExit()) {
            FullData fullData;
            InputData inputData = CLIENT_DATA_STORAGE.getInputDataFromStorage();
            if (inputData != null) {
                fullData = new FullData(
                        inputData,
                        CLIENT_CONFIG.getSignature(),
                        getDataLength(getDataBytesArray(inputData)),
                        getCRC32(getDataBytesArray(inputData))
                );

                CLIENT_DATA_STORAGE.putFullDataPackToStorage(fullData);
            }
        }
    }

    private byte[] getDataBytesArray(InputData inputData) {
        byte[] obj = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(inputData);
            outputStream.flush();
            obj = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            ConsoleTools.exceptionMessage("Невозможно корректно обработать данные!");
        }
        return obj;
    }

    private long getDataLength(byte[] obj) {
        return obj.length;
    }

    private long getCRC32(byte[] obj) {
        CRC32 crc32 = new CRC32();
        crc32.update(obj);
        return crc32.getValue();
    }

}
