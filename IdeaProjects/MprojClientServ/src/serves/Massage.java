package serves;

import datapacks.FullDataPack;

public class Massage {
    private FullDataPack fullDataPack;
    private Info servesInfo;

    public Massage(Info servesInfo) {
        this.servesInfo = servesInfo;
    }

    public Massage(FullDataPack fullDataPack, Info servesInfo) {
        this.fullDataPack = fullDataPack;
        this.servesInfo = servesInfo;
    }

    public FullDataPack getFullDataPack() {
        return fullDataPack;
    }

    public Info getServesInfo() {
        return servesInfo;
    }
}
