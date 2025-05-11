package datapacks;

import serves.ConsoleTools;
import serves.DataType;

import java.io.Serializable;
import java.util.Map;

public class InputDataPack implements Serializable {
    private final String USER_NAME;
    private final DataType FILE_TYPE;
    private int simpleData;
    private Map<String, Integer> dataMap;
    private final DataType DATA_TYPE;

    public InputDataPack(String USER_NAME, DataType FILE_TYPE, int simpleData, DataType DATA_TYPE) {
        this.USER_NAME = USER_NAME;
        this.FILE_TYPE = FILE_TYPE;
        this.simpleData = simpleData;
        this.DATA_TYPE = DATA_TYPE;
        ConsoleTools.statusMessage("Данные собраны и отправленны на дальнейшую обработку.");

    }

    public InputDataPack(String USER_NAME, DataType FILE_TYPE, Map<String, Integer> dataMap, DataType DATA_TYPE) {
        this.USER_NAME = USER_NAME;
        this.FILE_TYPE = FILE_TYPE;
        this.dataMap = dataMap;
        this.DATA_TYPE = DATA_TYPE;
        ConsoleTools.statusMessage("Данные собраны и отправленны на дальнейшую обработку.");

    }

    public String getUserName() {
        return USER_NAME;
    }

    public DataType getFileType() {
        return FILE_TYPE;
    }

    public int getSimpleData() {
        return simpleData;
    }

    public Map<String, Integer> getDataMap() {
        return dataMap;
    }

    public DataType getDataType() {
        return DATA_TYPE;
    }
}
