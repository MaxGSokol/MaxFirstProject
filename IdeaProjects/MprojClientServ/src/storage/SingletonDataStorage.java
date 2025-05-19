package storage;

import datapacks.FullDataPack;
import datapacks.InputDataPack;

import java.util.ArrayDeque;

public enum SingletonDataStorage {
    DATA_STORAGE;

    private final ArrayDeque<InputDataPack> inputDataStorage;
    private final ArrayDeque<FullDataPack> fullPackStorage;

    SingletonDataStorage() {
        fullPackStorage = new ArrayDeque<>();
        inputDataStorage = new ArrayDeque<>();
    }

    public SingletonDataStorage getDataStorage() {
        return DATA_STORAGE;
    }

    public void putInputDataToStorage(InputDataPack inputDataPack) {
        inputDataStorage.addFirst(inputDataPack);
    }

    public InputDataPack getInputDataFromStorage() {
        return inputDataStorage.pollLast();
    }

    public void putFullDataPackToStorage(FullDataPack fullDataPack) {
        fullPackStorage.addFirst(fullDataPack);
    }

    public FullDataPack getFullDataPackFromStorage() {
        return fullPackStorage.pollLast();
    }
}
