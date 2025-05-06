import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable {
private ObjectInputStream in;
private ObjectOutputStream out;
private Socket socket;
private DataManager dataManager;


    public Connection() {
    }

    public Connection(Socket socket) throws IOException {
        this.socket = socket;
        in = (ObjectInputStream) socket.getInputStream();
        out = (ObjectOutputStream) socket.getOutputStream();
    }

    @Override
    public void run() {
        testTr();
        while (true){
            if (dataManager != null){

                ConsoleTools.writeMessage("Сообщение отправлено!");

                this.dataManager = null;
            }
        }




    }

    public void testTr(){
        ConsoleTools.writeMessage("Связь с сервером установлена");
    }

    public void handChpock(){
        sendData(new DataManager(DataType.CONNECTION_REQUEST));
        while (true){
            DataType dataType = receiveData().getDataType();
            if (dataType == DataType.CONNECTION_ACCEPT){
                ConsoleTools.writeMessage("Связь с сервером установлена");
                return;
            }
        }
    }

    public void sendData(DataManager dataManager){
        try {
            out.writeObject(dataManager);
        } catch (IOException e) {
            ConsoleTools.writeMessage("Возникла ошибка при отправлении данных!");
        }
    }

    public DataManager receiveData(){
        while (true){
            try {
                DataManager dataManager = (DataManager) in.readObject();
                if (dataManager != null){
                    return dataManager;
                }
            } catch (IOException | ClassNotFoundException e) {
                ConsoleTools.writeMessage("Возникла ошибка при приеме данных");
            }
        }
    }

    public static String getIp(){
        ConsoleTools.writeMessage("Введите Ip адрес");
        while (true){
        String ip = ConsoleTools.readLine();
        String[] st = ip.split("\\.");
        if (st.length != 4){
        ConsoleTools.writeMessage("Введен неверный Ip, попробуйте снова.");}
        return ip;
        }

    }

    public static int getPort(){
        ConsoleTools.writeMessage("Введите порт");
        return ConsoleTools.readInt();
    }

    public void close(){
        try {
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            ConsoleTools.writeMessage("Ошибка");
        }

    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
