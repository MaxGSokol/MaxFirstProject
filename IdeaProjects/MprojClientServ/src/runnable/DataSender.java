package runnable;

import datapacks.FullDataPack;
import serves.Connection;
import serves.ConsoleTools;
import serves.Info;
import serves.Massage;
import storege.FullPackageStorage;

import java.io.IOException;

public class DataSender implements Runnable {
    private Connection connection;

    public DataSender(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (true) {
            FullDataPack fullDataPack = FullPackageStorage.fullPackStorage.pollLast();
            if (fullDataPack != null) {
                System.out.println(fullDataPack.getInputDataPack().getUserName());
            }
        }

    }

    public void testTr() {
        ConsoleTools.writeMessage("Связь с сервером установлена");
    }

    public void handChpock() {
        sendData(new Massage(Info.CONNECTION_REQUEST));
        while (true) {
            Info info = receiveData().getServesInfo();
            if (info == Info.CONNECTION_ACCEPT) {
                ConsoleTools.writeMessage("Связь с сервером установлена");
                return;
            }
        }
    }

    public void sendData(Massage massage) {
        try {
            connection.getOut().writeObject(massage);
        } catch (IOException e) {
            ConsoleTools.writeMessage("Возникла ошибка при отправлении данных!");
        }
    }

    public Massage receiveData() {
        while (true) {
            try {
                Massage massage = (Massage) connection.getIn().readObject();
                if (massage != null) {
                    return massage;
                }
            } catch (IOException | ClassNotFoundException e) {
                ConsoleTools.writeMessage("Возникла ошибка при приеме данных");
            }
        }
    }

}
