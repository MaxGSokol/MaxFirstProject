package datapacks;

import lombok.Builder;
import lombok.Getter;
import source.SingletonClientConfig;

@Getter
@Builder
public class FullDataPack {
    private final String signature = SingletonClientConfig.CLIENT_CONFIG.getSignature();
    private final InputDataPack inputDataPack;
    private final long dataLength;
    private final long controlSum;
}
