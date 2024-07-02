package lab_four;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class BucketTestLab {
        public static void main(String[] args) {
        //var b = new Bucket(9600, 151);
        var b = new Bucket(100, 80);

        //FileToText.readFile("set_2M.txt").lines().mapToInt(Integer::valueOf).forEach(b::processValue);

        try (BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(new FileInputStream("set_2M.txt"), "ISO-8859-15"))) {
            var line = bufferedReader.readLine();

            while(line != null) {
                b.processValue(Integer.valueOf(line));
                line = bufferedReader.readLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        var estimate = b.getBasicEstimate();
        System.out.println("Estimate: " + estimate);

        var error = 100 * ((estimate - 2_000_000) / 2_000_000);
        System.out.println("Error: " + error + "%");
    }


}
