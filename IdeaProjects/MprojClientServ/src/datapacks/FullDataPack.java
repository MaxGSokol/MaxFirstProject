package datapacks;

import java.util.zip.CRC32;

public class FullDataPack {
    private static final String signature = "(13)JU84";
    private InputDataPack inputDataPack;
    private long dataLength;
    private CRC32 controlSum;

    public FullDataPack(InputDataPack inputDataPack, long dataLength, CRC32 controlSum) {
        this.inputDataPack = inputDataPack;
        this.dataLength = dataLength;
        this.controlSum = controlSum;
        System.out.println(" F ");
    }

    public InputDataPack getInputDataPack() {
        return inputDataPack;
    }

    public long getDataLength() {
        return dataLength;
    }

    public CRC32 getControlSum() {
        return controlSum;
    }

    public String getSignature() {
        return signature;
    }
}
