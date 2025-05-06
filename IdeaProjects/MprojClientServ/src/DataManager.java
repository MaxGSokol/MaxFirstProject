import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.CRC32;

public class DataManager {
    private static final String signature = "(13)JU84";
    private String userName;
    private long dataLength;
    private DataType dataType;
    private int simpleData;
    private Map<String, Integer> dataMap = new ConcurrentHashMap<>(3);
    private CRC32 controlSum;

    public DataManager(DataType dataType) {
        this.dataType = dataType;
    }

    public DataManager(String userName, DataType dataType, int simpleData,  long dataLength, CRC32 controlSum) {
        this.userName = userName;
        this.simpleData = simpleData;
        this.dataType = dataType;
        this.dataLength = dataLength;
        this.controlSum = controlSum;
        ConsoleTools.writeMessage("Пакет с данными собран.");
    }

    public DataManager(String userName, DataType dataType, Map<String, Integer> dataMap, long dataLength, CRC32 controlSum) {
        this.userName = userName;
        this.dataLength = dataLength;
        this.dataType = dataType;
        this.dataMap = dataMap;
        this.controlSum = controlSum;
        ConsoleTools.writeMessage("Пакет с данными собран.");
    }

    public String getUserName() {
        return userName;
    }

    public String getSignature() {
        return signature;
    }

    public long getDataLength() {
        return dataLength;
    }

    public DataType getDataType() {
        return dataType;
    }

    public int getSimpleData() {
        return simpleData;
    }

    public Map<String, Integer> getDataMap() {
        return dataMap;
    }

    public CRC32 getControlSum() {
        return controlSum;
    }
}
