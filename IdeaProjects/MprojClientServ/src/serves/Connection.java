package serves;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;


    public Connection() {
    }

    public Connection(Socket socket) throws IOException {
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
            ConsoleTools.writeMessage("Ошибка");
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

