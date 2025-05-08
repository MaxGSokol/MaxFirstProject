package runnable;

import datapacks.FullDataPack;
import datapacks.InputDataPack;
import serves.ConsoleTools;
import serves.Info;
import storege.FullPackageStorage;
import storege.InputDataStorage;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.zip.CRC32;

public class FullDataPackCollector implements Runnable {



    @Override
    public void run() {
        while (true) {
            FullDataPack fullDataPack;
            InputDataPack inputDataPack = InputDataStorage.inputDataStorage.pollLast();
            if (inputDataPack != null) {
                if (inputDataPack.getDataType() == Info.ADVANCE) {

                    fullDataPack = new FullDataPack(inputDataPack,
                            toGetBigDataLength(inputDataPack.getUserName(), inputDataPack.getDataMap()),
                            toGetBigCRC32(inputDataPack.getUserName(), inputDataPack.getDataMap()));

                    ConsoleTools.writeMessage("Содержимое пакета данных.");
                    ConsoleTools.writeMessage("Сигнатура - " + fullDataPack.getSignature());
                    ConsoleTools.writeMessage("Имя пользователя - " + inputDataPack.getUserName());
                    ConsoleTools.writeMessage("Способ вывода данных на сервере - " + inputDataPack.getFileType().name());
                    ConsoleTools.writeMessage("Выбранные температурные режимы.");

                    for (String key : fullDataPack.getInputDataPack().getDataMap().keySet()) {
                        Integer value = fullDataPack.getInputDataPack().getDataMap().get(key);
                        ConsoleTools.writeMessage(key + " - " + value + " градуса.");
                    }

                    ConsoleTools.writeMessage("Длинна данных в байтах - " + fullDataPack.getDataLength());
                    ConsoleTools.writeMessage("CRC32 - " + fullDataPack.getControlSum().getValue());

                    FullPackageStorage.fullPackStorage.addFirst(fullDataPack);

                }

                if (inputDataPack.getDataType() == Info.SIMPLE) {
                    fullDataPack = new FullDataPack(inputDataPack, toGetSmaleDataLength(inputDataPack.getUserName()),
                            toGetSmaleCRC32(inputDataPack.getUserName(), inputDataPack.getSimpleData()));
                    ConsoleTools.writeMessage("Содержимое пакета данных.");
                    ConsoleTools.writeMessage("Сигнатура - " + fullDataPack.getSignature());
                    ConsoleTools.writeMessage("Имя пользователя - " + inputDataPack.getUserName());
                    ConsoleTools.writeMessage("Способ вывода данных на сервере - " + inputDataPack.getFileType().name());
                    ConsoleTools.writeMessage("Выбранный температурный режим - " + inputDataPack.getSimpleData()
                            + " градуса.");
                    ConsoleTools.writeMessage("Длинна данных в байтах - " + fullDataPack.getDataLength());
                    ConsoleTools.writeMessage("CRC32 - " + fullDataPack.getControlSum().getValue());

                    FullPackageStorage.fullPackStorage.addFirst(fullDataPack);


                }
            }

        }
    }


    private long toGetBigDataLength(String userName, Map<String, Integer> dataMap) {
        long dataLength = 0;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(userName);
            byte[] obj = byteArrayOutputStream.toByteArray();
            dataLength = obj.length;
        } catch (Exception e) {
        }

        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(dataMap);
            byte[] obj = byteArrayOutputStream.toByteArray();
            dataLength += obj.length;
        } catch (Exception ignored) {
        }
        return dataLength;
    }

    private long toGetSmaleDataLength(String userName) {
        long dataLength = 4;
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(userName);
            byte[] obj = byteArrayOutputStream.toByteArray();
            dataLength += obj.length;
        } catch (Exception ignored) {
        }
        return dataLength;
    }

    private CRC32 toGetBigCRC32(String userName, Map<String, Integer> dataMap) {
        CRC32 crc32 = new CRC32();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(userName);
            outputStream.writeObject(dataMap);
            byte[] obj = byteArrayOutputStream.toByteArray();
            crc32.update(obj);
        } catch (Exception ignored) {
        }
        return crc32;
    }

    private CRC32 toGetSmaleCRC32(String userName, int simpleDate) {
        CRC32 crc32 = new CRC32();
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            outputStream.writeObject(simpleDate);
            outputStream.writeObject(userName);
            byte[] obj = byteArrayOutputStream.toByteArray();
            crc32.update(obj);
        } catch (Exception ignored) {
        }
        return crc32;
    }


}
