import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;

public class DataPackCollector implements Runnable{
    private String userName;
    private DataType dataType;
    private int simpleData;
    private Map<String, Integer> dataMap;



    @Override
    public void run() {
        ConnectionRun connection = new ConnectionRun();
        DataManager dataManager = null;
        while (true) {
             if (userName != null  && dataType != null && dataMap != null) {
                dataManager = new DataManager(userName, dataType, dataMap, toGetBigDataLength(userName,dataMap), toGetBigCRC32(userName,dataMap));
                ConsoleTools.writeMessage("Содержимое пакета данных.");
                ConsoleTools.writeMessage("Сигнатура - " + dataManager.getSignature());
                ConsoleTools.writeMessage("Имя пользователя - " + dataManager.getUserName());
                ConsoleTools.writeMessage("Способ вывода данных на сервере - " + dataManager.getDataType().name());
                ConsoleTools.writeMessage("Выбранные температурные режимы.");
                  for (String key : dataManager.getDataMap().keySet()) {
                    Integer value = dataManager.getDataMap().get(key);
                    ConsoleTools.writeMessage(key + " - " + value + " градуса.");
                  }
                ConsoleTools.writeMessage("Длинна данных в байтах - " + dataManager.getDataLength());
                ConsoleTools.writeMessage("CRC32 - " + dataManager.getControlSum().getValue());

                this.userName = null;
                this.dataType = null;
                this.dataMap = null;
            }

            if (userName != null && dataType != null && simpleData != 0) {
                dataManager = new DataManager(userName, dataType, simpleData, toGetSmaleDataLength(userName), toGetSmaleCRC32(userName, simpleData));
                ConsoleTools.writeMessage("Содержимое пакета данных.");
                ConsoleTools.writeMessage("Сигнатура - " + dataManager.getSignature());
                ConsoleTools.writeMessage("Имя пользователя - " + dataManager.getUserName());
                ConsoleTools.writeMessage("Способ вывода данных на сервере - " + dataManager.getDataType().name());
                ConsoleTools.writeMessage("Выбранный температурный режим - " + dataManager.getSimpleData() + " градуса.");
                ConsoleTools.writeMessage("Длинна данных в байтах - " + dataManager.getDataLength());
                ConsoleTools.writeMessage("CRC32 - " + dataManager.getControlSum().getValue());

                connection.setDataManager(dataManager);

                Thread thread = new Thread(connection);
                thread.start();

                this.userName = null;
                this.dataType = null;
                this.simpleData = 0;
            }

        }
    }

    private long toGetBigDataLength(String userName , Map<String, Integer> dataMap){
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

    private long toGetSmaleDataLength(String userName ) {
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

    private CRC32 toGetBigCRC32(String userName , Map<String, Integer> dataMap) {
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

    private CRC32 toGetSmaleCRC32(String userName , int simpleDate) {
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public void setSimpleData(int simpleData) {
        this.simpleData = simpleData;
    }

    public void setDataMap(Map<String, Integer> dataMap) {
        this.dataMap = dataMap;
    }

}
