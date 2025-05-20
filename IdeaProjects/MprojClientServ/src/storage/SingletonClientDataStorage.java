package storage;

import datapacks.FullDataPack;
import datapacks.InputDataPack;

import java.util.ArrayDeque;

public enum SingletonClientDataStorage {
    CLIENT_DATA_STORAGE;

    private volatile ArrayDeque<InputDataPack> inputDataStorage;
    private volatile ArrayDeque<FullDataPack> fullPackStorage;

    SingletonClientDataStorage() {
        fullPackStorage = new ArrayDeque<>();
        inputDataStorage = new ArrayDeque<>();
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
