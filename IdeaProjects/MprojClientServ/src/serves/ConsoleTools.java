package serves;

import source.SingletonClientConfig;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleTools {
    private static final BufferedReader BUFFERED_READER = new BufferedReader(new InputStreamReader(System.in));
    private static LocalDateTime LOCAL_DATE_TIME;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static String DATE_TIME;

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static void statusMessage(String statusMessage) {
        if (SingletonClientConfig.CLIENT_CONFIG.isLogging()) {
            System.out.println("\u001B[31m" + "СТАТУС : " + statusMessage + "\u001B[0m");
        }
        LOCAL_DATE_TIME = LocalDateTime.now();
        DATE_TIME = LOCAL_DATE_TIME.format(FORMATTER);
        try (FileWriter fileWriter = new FileWriter(SingletonClientConfig.CLIENT_CONFIG.getLogPath(), true)) {
            fileWriter.write(DATE_TIME + " СТАТУС : " + statusMessage + "\n");
        } catch (IOException e) {
            exceptionMessage("Невозможно произвести запись в файл.");
        }
    }

    public static void exceptionMessage(String exceptionMessage) {
        System.out.println("ОШИБКА ! " + exceptionMessage);

        LOCAL_DATE_TIME = LocalDateTime.now();
        DATE_TIME = LOCAL_DATE_TIME.format(FORMATTER);
        try (FileWriter fileWriter = new FileWriter(SingletonClientConfig.CLIENT_CONFIG.getLogPath(), true)) {
            fileWriter.write(DATE_TIME + " СОШИБКА ! " + exceptionMessage + "\n");
        } catch (IOException e) {
            exceptionMessage("Невозможно произвести запись в файл.");
        }
    }

    public static String readLine() {
        String input = null;
        try {
            input = BUFFERED_READER.readLine();
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

}
