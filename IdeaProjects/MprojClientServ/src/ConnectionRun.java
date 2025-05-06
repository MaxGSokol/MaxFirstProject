public class ConnectionRun implements Runnable{
Connection connection = new Connection();
DataManager dataManager;


    @Override
    public void run() {
        connection.testTr();
        while (true){
            if (dataManager != null){
                ConsoleTools.writeMessage("Содержимое пакета данных.");
                ConsoleTools.writeMessage("Сигнатура - " + dataManager.getSignature());
                ConsoleTools.writeMessage("Имя пользователя - " + dataManager.getUserName());
                ConsoleTools.writeMessage("Способ вывода данных на сервере - " + dataManager.getDataType().name());
                ConsoleTools.writeMessage("Выбранный температурный режим - " + dataManager.getSimpleData() + " градуса.");
                ConsoleTools.writeMessage("Длинна данных в байтах - " + dataManager.getDataLength());
                ConsoleTools.writeMessage("CRC32 - " + dataManager.getControlSum().getValue());

                ConsoleTools.writeMessage("Сообщение отправлено!");
            break;
            }
        }
    }

    public void setDataManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
