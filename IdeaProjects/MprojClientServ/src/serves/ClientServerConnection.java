package serves;

import datapacks.FullDataPack;
import lombok.Getter;
import source.ClientServerConfig;

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
        this.socket = new Socket(ClientServerConfig.IP, ClientServerConfig.PORT);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
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

    public void sendAllotOfData(FullDataPack fullDataPack) {
        try {
            out.writeObject(fullDataPack.getInputDataPack().getUserName());
            out.writeObject(fullDataPack.getInputDataPack().getFileType().name());
            out.writeObject(fullDataPack.getInputDataPack().getDataType().name());
            out.writeObject(fullDataPack.getSignature());
            out.writeLong(fullDataPack.getDataLength());
            out.writeLong(fullDataPack.getControlSum());
            if (fullDataPack.getInputDataPack().getDataType() == DataType.SIMPLE) {
                out.writeInt(fullDataPack.getInputDataPack().getSimpleData());
            } else {
                out.writeObject(fullDataPack.getInputDataPack().getDataMap());
            }
            out.flush();

        } catch (IOException e) {
            ConsoleTools.exceptionMessage("Сбой при отправлении данных!");
        }
    }

}

