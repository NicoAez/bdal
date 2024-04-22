package lab_one;

import utility.FileToText;

public class Jenkins {
    public static void main(String[] args) {
        System.out.println(jenkins_one_at_a_time("BigData"));

        String text = FileToText.readFile("Mann_Buddenbrooks.txt").replaceAll("\n", "");

        System.out.println(text.length());

        long start = System.currentTimeMillis();
        System.out.println(jenkins_one_at_a_time(text));
        System.out.println(System.currentTimeMillis() - start);
    }

    public static long jenkins_one_at_a_time(String text) {
        var bytes = text.getBytes();
        long hash = 0;

        for (byte aByte : bytes) {
            hash = hash + aByte;
            hash = hash + (hash << 10) & 0x00000000ffffffffL;
            hash = hash ^ (hash >> 6) & 0x00000000ffffffffL;
        }

        hash = hash + (hash << 3) & 0x00000000ffffffffL;
        hash = hash ^ (hash >> 11) & 0x00000000ffffffffL;
        hash = hash + (hash << 15) & 0x00000000ffffffffL;

        return hash;
    }
}
