package storege;

import datapacks.InputDataPack;

import java.util.ArrayDeque;

public class InputDataStorage {
    public static volatile ArrayDeque<InputDataPack> inputDataStorage = new ArrayDeque<InputDataPack>();
}
