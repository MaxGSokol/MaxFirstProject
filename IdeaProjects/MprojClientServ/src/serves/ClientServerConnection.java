package serves;

import lombok.Getter;
import source.SingletonClientConfig;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Getter
public class ClientServerConnection {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final Socket socket;

    public ClientServerConnection() throws IOException {
        this.socket = new Socket(
                SingletonClientConfig.CLIENT_CONFIG.getIp(),
                SingletonClientConfig.CLIENT_CONFIG.getPort()
        );
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void close() {
        try {
            out.close();
            in.close();
            socket.close();
            ConsoleTools.statusMessage("Соединение закрыто.");
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Сбой при закрытии потоков ввода вывода.");
        }
    }

    public void send(Object fullData) {
        try {
            out.writeObject(fullData);
            out.flush();
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Сбой при передаче данных серверу.");
        }
    }

}

