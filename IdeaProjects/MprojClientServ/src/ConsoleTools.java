import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleTools {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readLine() {
        while (true) {
            try {
                String input = bufferedReader.readLine();
                if (input != null) {
                return input; }
            } catch (IOException e) {
                writeMessage("Ошибка");
            }
        }
    }

    public static int readInt() {
        return Integer.parseInt(readLine());
    }

}
