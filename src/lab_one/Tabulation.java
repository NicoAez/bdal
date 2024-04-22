package lab_one;

import java.util.ArrayList;
import java.util.Random;

public class Tabulation {
    public static void main(String[] args) {
        var s_table = generateTable(false);
        var b_table = generateTable(true);
        ArrayList<Double> norm = new ArrayList<>();
        ArrayList<Double> mixed = new ArrayList<>();

        for(int i = 0; i < 10000000; i++) {
            double normFlips = 0;
            double mixedFlips = 0;

            // normal
            int tab32 = (int) tabula32(i, s_table);
            for(int j = 0; j < 32; j++) {
                int shifted = tab32 ^ (1<<j);
                int tab32_x = (int) tabula32(shifted, s_table);
                normFlips += Integer.bitCount(tab32 ^ tab32_x);
            }

            // mixed
            int mix32 = (int) tabulaMix32(i, s_table, b_table);
            for(int j = 0; j < 32; j++) {
                int shifted = mix32 ^ (1<<j);
                int mix32_x = (int) tabulaMix32(shifted, s_table, b_table);
                mixedFlips += Integer.bitCount(mix32 ^ mix32_x);
            }

            norm.add(normFlips / 32);
            mixed.add(mixedFlips / 32);
        }

        double normAvg = norm.stream().mapToDouble(a -> a).average().getAsDouble();
        //double mixedAvg = mixed.stream().mapToDouble(a -> a).average().getAsDouble();
        System.out.println(normAvg);
        //System.out.println(mixedAvg);
    }

    public static long tabula32(long x, long[][] hTab) {
        long hash = 0;
        long c;

        for(int i = 0; i < 4; i++) {
            c = x & 0xff;
            hash ^= hTab[i][(int) c];
            x = x >> 8;
        }

        return hash & 0xffffffffL;
    }

    public static long tabulaMix32(long x, long[][] hTab_32, long[][] hTab_64) {
        long hash = 0;

        for(int i = 0; i < 4; i++) {
            hash ^= hTab_64[i][(byte) x];
            x = x >> 8;
        }

        long drv = hash >> 32;
        for(int i = 0; i < 4; i++){
            hash ^= hTab_32[i][(byte) drv];
            drv = drv >> 8;
        }

        return hash & 0xffffffffL;
    }

    public static long[][] generateTable(boolean big) {
        Random r = new Random();
        long max = big ? Long.MAX_VALUE : Integer.MAX_VALUE;
        long[][] table = new long[4][256];

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 256; j++) {
                table[i][j] = r.nextLong(max);
            }
        }

        return table;
    }
}
