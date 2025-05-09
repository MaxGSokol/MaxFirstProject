package serves;

import datapacks.FullDataPack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientServerConnection {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;

    public ClientServerConnection() {
    }

    public ClientServerConnection(Socket socket) throws IOException {
        this.socket = socket;
        in = (ObjectInputStream) socket.getInputStream();
        out = (ObjectOutputStream) socket.getOutputStream();
    }

    public void close() {
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Сбой при закрытии потоков ввода вывода.");
        }
    }

    public void sendData(FullDataPack fullDataPack) {
        try {
            out.writeObject(fullDataPack);
        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Сбой при отправлении данных!");
        }
    }

    public FullDataPack receiveData() {
        while (true) {
            try {
                FullDataPack fullDataPack = (FullDataPack) in.readObject();
                if (fullDataPack != null) {
                    return fullDataPack;
                }
            } catch (IOException | ClassNotFoundException e) {
                ConsoleTools.exceptionMessage("Сбой при приеме данных");
            }
        }
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public Socket getSocket() {
        return socket;
    }
}

