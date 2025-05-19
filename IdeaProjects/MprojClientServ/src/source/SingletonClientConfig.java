package source;

import lombok.Getter;
import lombok.Setter;
import serves.ConsoleTools;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Getter
public enum SingletonClientConfig {
    CLIENT_CONFIG;

    @Setter
    private volatile boolean isExit;
    private String userName;
    private String ip;
    private int port;
    private String signature;
    private String logPath;
    private boolean isLogging;

    SingletonClientConfig() {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader("src/source/client.config")) {
            properties.load(fileReader);
            userName = properties.getProperty("userName");
            ip = properties.getProperty("ip");
            port = Integer.parseInt(properties.getProperty("port"));
            signature = properties.getProperty("signature");
            logPath = properties.getProperty("logPath");
            isLogging = Boolean.parseBoolean(properties.getProperty("log"));
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Невозможно загрузить конфигурационный файл.\n" +
                    "Будет использовано значение по умолчанию.");
            userName = "DEFAUlT USER";

        }
    }

    public SingletonClientConfig getClientConfig() {
        return CLIENT_CONFIG;
    }

}
