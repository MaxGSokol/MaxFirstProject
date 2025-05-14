package storage;

import datapacks.FullDataPack;
import datapacks.InputDataPack;

import java.util.ArrayDeque;

public class DataStorage {
    public static volatile ArrayDeque<InputDataPack> INPUT_DATA_STORAGE = new ArrayDeque<>();
    public static volatile ArrayDeque<FullDataPack> FULL_PACK_STORAGE = new ArrayDeque<>();
}
