package datapacks;

import lombok.Builder;
import lombok.Getter;
import source.ClientServerConfig;

@Getter
@Builder
public class FullDataPack {
    private final String signature = ClientServerConfig.SIGNATURE;
    private final InputDataPack inputDataPack;
    private final long dataLength;
    private final long controlSum;
}
