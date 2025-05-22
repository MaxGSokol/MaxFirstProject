package storage;

import dataclasses.FullData;
import dataclasses.InputData;

import java.util.ArrayDeque;

public enum SingletonClientDataStorage {
    CLIENT_DATA_STORAGE;

    private volatile ArrayDeque<InputData> inputDataStorage;
    private volatile ArrayDeque<FullData> fullPackStorage;

    SingletonClientDataStorage() {
        fullPackStorage = new ArrayDeque<>();
        inputDataStorage = new ArrayDeque<>();
    }

    public void putInputDataToStorage(InputData inputData) {
        inputDataStorage.addFirst(inputData);
    }

    public InputData getInputDataFromStorage() {
        return inputDataStorage.pollLast();
    }

    public void putFullDataPackToStorage(FullData fullData) {
        fullPackStorage.addFirst(fullData);
    }

    public FullData getFullDataPackFromStorage() {
        return fullPackStorage.pollLast();
    }
}
