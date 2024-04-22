package utility;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileToText {
    public static String readFile(String path) {
        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "ISO-8859-15"))) {
            StringBuilder stringBuilder = new StringBuilder();
            var line = bufferedReader.readLine();

            while(line != null) {
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }

            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
}
