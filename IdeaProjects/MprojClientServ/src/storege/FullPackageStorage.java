package storege;

import datapacks.FullDataPack;

import java.util.ArrayDeque;

public class FullPackageStorage {
    public static volatile ArrayDeque<FullDataPack> fullPackStorage = new ArrayDeque<FullDataPack>();
}
