package serves;

import storege.ClientConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleTools {
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void statusMessage(String statusMessage) {
        System.out.println("СТАТУС : " + statusMessage);
    }

    public static void exceptionMessage(String exceptionMessage) {
        System.out.println("ОШИБКА ! " + exceptionMessage);
    }

    public static String readLine() {
        String input = null;
        try {
            input = BUFFERED_READER.readLine();
            if (input != null) {

            }
        } catch (IOException e) {
            exceptionMessage("Данные не считываются.");
        }
        return input;
    }

    public static int readInt() {
        int num = 0;
        try {
          num = Integer.parseInt(readLine());
        } catch (NumberFormatException e) {
            exceptionMessage("Ввести нужно именно число.");
        }
        return num;
    }

    public static void intro() {
        ConsoleTools.writeMessage(
                "Приветствуем вас " + ClientConfig.CLIENT_CONFIG.get("userName")
                        + " !\n Вы запустили программу удаленного управления\n "
                        + "системой домашнего кондиционирования.");
        ConsoleTools.writeMessage("Далее вводите информацию с клавиатуры согласно инструкции.");
    }

}
