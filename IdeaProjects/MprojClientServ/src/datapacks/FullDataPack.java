package datapacks;

import serves.ConsoleTools;

import java.util.zip.CRC32;

public class FullDataPack {
    private static final String SIGNATURE = "(13)JU84";
    private final InputDataPack INPUT_DATA_PACK;
    private final long DATA_LENGTH;
    private final CRC32 CONTROL_SUM;

    public FullDataPack(InputDataPack INPUT_DATA_PACK, long DATA_LENGTH, CRC32 CONTROL_SUM) {
        this.INPUT_DATA_PACK = INPUT_DATA_PACK;
        this.DATA_LENGTH = DATA_LENGTH;
        this.CONTROL_SUM = CONTROL_SUM;
        ConsoleTools.statusMessage("Пакет данных полностью укомплектован.");
    }

    public InputDataPack getINPUT_DATA_PACK() {
        return INPUT_DATA_PACK;
    }

    public long getDATA_LENGTH() {
        return DATA_LENGTH;
    }

    public CRC32 getCONTROL_SUM() {
        return CONTROL_SUM;
    }

    public String getSIGNATURE() {
        return SIGNATURE;
    }
}
