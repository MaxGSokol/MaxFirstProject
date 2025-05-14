package datapacks;

import lombok.Builder;
import lombok.Getter;
import source.ClientServerConfig;

import java.io.Serializable;

@Getter
@Builder
public class FullDataPack implements Serializable {
    private final String signature = ClientServerConfig.SIGNATURE;
    private final InputDataPack inputDataPack;
    private final long dataLength;
    private final long controlSum;
}
