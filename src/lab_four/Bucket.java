package lab_four;

import lab_one.Tabulation;

import java.util.ArrayList;
import java.util.Arrays;

public class Bucket {
    private long[][][] tables;
    private ArrayList<Integer>[] buckets;
    private int[] minLeadingZeros;
    private int maxSize;

    Bucket(int maxSize, int amountHashFunctions) {
        this.maxSize = maxSize;
        this.tables = new long[amountHashFunctions][256][4];
        this.buckets = new ArrayList[amountHashFunctions];
        this.minLeadingZeros = new int[amountHashFunctions];

        for(int i = 0; i < amountHashFunctions; i++) {
            tables[i] = Tabulation.generateTable(false);
            buckets[i] = new ArrayList<>();
            minLeadingZeros[i] = 1;
        }
    }

    void processValue(int value) {
        for(int i = 0; i < tables.length; i++) {
            int hashed = (int) Tabulation.tabula32(value, tables[i]);
            int leadingZeros = Integer.numberOfLeadingZeros(hashed);

            if(leadingZeros >= minLeadingZeros[i] && !buckets[i].contains(hashed)) {
                buckets[i].add(hashed);

                if(buckets[i].size() > maxSize) {
                    var b = ++minLeadingZeros[i];
                    buckets[i].removeIf(a -> Integer.numberOfLeadingZeros(a) < minLeadingZeros[b]);
                }
            }
        }
    }

    double getBasicEstimate() {
        var estimates = new double[tables.length];
        for(int i = 0; i < tables.length; i++) {
            estimates[i] = buckets[i].size() * Math.pow(2, minLeadingZeros[i]);
        }

        Arrays.sort(estimates);
        return estimates[estimates.length / 2];
    }
}