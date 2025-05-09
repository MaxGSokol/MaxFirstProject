package runnable;

import datapacks.FullDataPack;
import datapacks.InputDataPack;
import serves.ConsoleTools;
import serves.DataType;
import storege.ClientConfig;
import storege.DataStorage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.CRC32;

public class FullDataPackCollector implements Runnable {

    @Override
    public void run() {

        while (!ClientConfig.IS_EXIT) {
            FullDataPack fullDataPack;
            InputDataPack inputDataPack = DataStorage.INPUT_DATA_STORAGE.pollLast();
            if (inputDataPack != null) {
                fullDataPack = new FullDataPack(
                        inputDataPack, getDataLength(getDataBytesArray(inputDataPack)),
                        getCRC32(getDataBytesArray(inputDataPack)));
                DataStorage.FULL_PACK_STORAGE.addFirst(fullDataPack);
            }
        }

    }

    private byte[] getDataBytesArray(InputDataPack inputDataPack) {
        byte[] obj = null;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(inputDataPack);
            obj = byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            ConsoleTools.exceptionMessage("Невозможно корректно обработать данные!");
        }
        return obj;
    }

    private long getDataLength(byte[] obj) {
        return obj.length;
    }

    private CRC32 getCRC32(byte[] obj) {
        CRC32 crc32 = new CRC32();
        crc32.update(obj);
        return crc32;
    }

}
