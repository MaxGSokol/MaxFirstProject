package datapacks;

import serves.Info;

import java.util.Map;

public class InputDataPack {
    private String userName;
    private Info fileType;
    private int simpleData;
    private Map<String, Integer> dataMap;
    private Info dataType;

    public InputDataPack(String userName, Info fileType, int simpleData, Info dataType) {
        this.userName = userName;
        this.fileType = fileType;
        this.simpleData = simpleData;
        this.dataType = dataType;
        System.out.println(" I ");
    }

    public InputDataPack(String userName, Info fileType, Map<String, Integer> dataMap, Info dataType) {
        this.userName = userName;
        this.fileType = fileType;
        this.dataMap = dataMap;
        this.dataType = dataType;

        System.out.println(" I ");
    }

    public String getUserName() {
        return userName;
    }

    public Info getFileType() {
        return fileType;
    }

    public int getSimpleData() {
        return simpleData;
    }

    public Map<String, Integer> getDataMap() {
        return dataMap;
    }

    public Info getDataType() {
        return dataType;
    }
}
