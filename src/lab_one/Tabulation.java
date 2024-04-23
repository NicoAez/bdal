package lab_one;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tabulation {
    public static void main(String[] args) {
        var s_table = generateTable(false);
        var b_table = generateTable(true);
        ArrayList<Double> norm = new ArrayList<>();
        ArrayList<Double> mixed = new ArrayList<>();
        int[] countAmountFlippedNorm = new int[32];
        int[] countAmountFlippedMixed = new int[32];

        for(int i = 0; i < 10_000_000; i++) {
            double normFlips = 0;
            double mixedFlips = 0;

            // normal
            int tab32 = (int) tabula32(i, s_table);
            for(int j = 0; j < 32; j++) {
                int shifted = tab32 ^ (1<<j);
                int tab32_x = (int) tabula32(shifted, s_table);
                int amountFlipped = Integer.bitCount(tab32 ^ tab32_x);
                countAmountFlippedNorm[amountFlipped]++;
                normFlips += amountFlipped;
            }

            // mixed
            int mix32 = (int) tabulaMix32(i, s_table, b_table);
            for(int j = 0; j < 32; j++) {
                int shifted = mix32 ^ (1<<j);
                int mix32_x = (int) tabulaMix32(shifted, s_table, b_table);
                int amountFlipped = Integer.bitCount(mix32 ^ mix32_x);
                countAmountFlippedMixed[amountFlipped]++;
                mixedFlips += amountFlipped;
            }

            // add to list
            norm.add(normFlips / 32);
            mixed.add(mixedFlips / 32);
        }

        // print avgs
        double normAvg = norm.stream().mapToDouble(a -> a).average().getAsDouble();
        double mixedAvg = mixed.stream().mapToDouble(a -> a).average().getAsDouble();
        System.out.println("Norm: " + normAvg);
        System.out.println("Mixed: " + mixedAvg);

        // print chi
        System.out.println("Norm: " + computeChiSquared(countAmountFlippedNorm));
        System.out.println("Mixed: " + computeChiSquared(countAmountFlippedMixed));
    }

    static long tabula32(long x, long[][] hTab) {
        long hash = 0;
        long c;

        for(int i = 0; i < 4; i++) {
            c = x & 0xff;
            hash ^= hTab[i][(int) c];
            x = x >> 8;
        }

        return hash & 0xffffffffL;
    }

    static long tabulaMix32(long x, long[][] hTab_32, long[][] hTab_64) {
        long hash = 0;

        for(int i = 0; i < 4; i++) {
            hash ^= hTab_64[i][(int) (x & 0xff)];
            x = x >> 8;
        }

        long drv = hash >> 32;
        for(int i = 0; i < 4; i++){
            hash ^= hTab_32[i][(int) (drv & 0xff)];
            drv = drv >> 8;
        }

        return hash & 0xffffffffL;
    }

    static long[][] generateTable(boolean big) {
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

    static double computeChiSquared(int[] observed) {
        double result = 0;
        int mult = 10000000 * 32;
        for(int i = 0; i < 32; i++) {
            double expected = binomialInt(32, i) * Math.pow(0.5, 32) * mult;

            //System.out.println("Expected " + expected + ", got " + observed[i]);

            result += Math.pow(observed[i] - expected, 2.0) / expected;
        }

        return result;
    }

    static long binomialInt(int n, int k) {
        if (k > n - k)
            k = n - k;

        long binom = 1;
        for (int i = 1; i <= k; i++)
            binom = binom * (n + 1 - i) / i;
        return binom;
    }
}
