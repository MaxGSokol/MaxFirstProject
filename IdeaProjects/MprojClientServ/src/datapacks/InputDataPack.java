package datapacks;

import lombok.Getter;
import serves.ConsoleTools;
import serves.DataType;

import java.util.Map;

@Getter
public class InputDataPack {
    private final String userName;
    private final DataType fileType;
    private int simpleData;
    private Map<String, Integer> dataMap;
    private final DataType dataType;

    public InputDataPack(String userName, DataType fileType, int simpleData, DataType dataType) {
        this.userName = userName;
        this.fileType = fileType;
        this.simpleData = simpleData;
        this.dataType = dataType;
        ConsoleTools.statusMessage("Данные собраны и отправленны на дальнейшую обработку.");
    }

    public InputDataPack(String userName, DataType fileType, Map<String, Integer> dataMap, DataType dataType) {
        this.userName = userName;
        this.fileType = fileType;
        this.dataMap = dataMap;
        this.dataType = dataType;
        ConsoleTools.statusMessage("Данные собраны и отправленны на дальнейшую обработку.");
    }

}
