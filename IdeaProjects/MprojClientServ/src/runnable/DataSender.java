package runnable;

import serves.ClientServerConnection;

import java.io.IOException;

import static source.SingletonClientConfig.CLIENT_CONFIG;
import static storage.SingletonClientDataStorage.CLIENT_DATA_STORAGE;

public class DataSender implements Runnable {
    private final ClientServerConnection clientServerConnection;

    public DataSender() throws IOException {
        this.clientServerConnection = new ClientServerConnection();
    }

    @Override
    public void run() {
        while (!CLIENT_CONFIG.isExit()) {
            Object fullData = CLIENT_DATA_STORAGE.getFullDataPackFromStorage();
            if (fullData != null) {
                clientServerConnection.send(fullData);
            }
        }
        clientServerConnection.close();
    }

}


