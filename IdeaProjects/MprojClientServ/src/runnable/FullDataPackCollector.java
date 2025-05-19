package runnable;

import datapacks.FullDataPack;
import datapacks.InputDataPack;
import serves.ConsoleTools;
import serves.DataType;
import source.SingletonClientConfig;
import storage.SingletonDataStorage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.CRC32;

public class FullDataPackCollector implements Runnable {

    @Override
    public void run() {
        while (!SingletonClientConfig.CLIENT_CONFIG.isExit()) {
            FullDataPack fullDataPack;
            InputDataPack inputDataPack = SingletonDataStorage.DATA_STORAGE.getInputDataFromStorage();
            if (inputDataPack != null) {
                fullDataPack = FullDataPack.builder()
                        .inputDataPack(inputDataPack)
                        .dataLength(getDataLength(getDataBytesArray(inputDataPack)))
                        .controlSum(getCRC32(getDataBytesArray(inputDataPack))).build();

                SingletonDataStorage.DATA_STORAGE.putFullDataPackToStorage(fullDataPack);
            }
        }
    }

    private byte[] getDataBytesArray(InputDataPack inputDataPack) {
        byte[] obj = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(inputDataPack.getUserName());
            if (inputDataPack.getDataType() == DataType.SIMPLE) {
                outputStream.writeInt(inputDataPack.getSimpleData());
            } else {
                outputStream.writeObject(inputDataPack.getDataMap());
            }
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
