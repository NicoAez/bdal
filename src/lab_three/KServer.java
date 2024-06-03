package lab_three;

import java.util.Random;

public class KServer {
    private final int[][] distance = {
            {0, 400, 300, 1900, 2600},
            {400, 0, 500, 2400, 2900},
            {300, 500, 0, 2300, 2700},
            {1900, 2400, 2300, 0, 800},
            {2600, 2900, 2700, 800, 0}
    };

    private final int[] sequence = {1, 4, 0, 4, 0, 3};

    private int server_one = 2;
    private int server_two = 0;

    private int greedy() {
        int d = 0;
        for(int i = 0; i < sequence.length; i++) {
            int distance_one = distance[sequence[i]][server_one];
            int distance_two = distance[sequence[i]][server_two];

            if (distance_one > distance_two) {
                d += distance_two;
                server_two = sequence[i];
            } else {
                d += distance_one;
                server_one = sequence[i];
            }
        }

        return  d;
    }

    public static void main (String[] args) {
        KServer k = new KServer();

        System.out.println(k.greedy());

        // in the real world most data is not random, e.g. different time zones, less internet traffic at night
        // you could take advantage of that by analyzing the last n requests to possibly predict certain trends
    }
}
