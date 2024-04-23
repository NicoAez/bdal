package lab_two;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;

public class StaticList {
    public StaticList(Map<Character, Double> probabilities) {
        // no need to implement a static list, thus only computing average costs
        int index = 1;
        double cost = 0;

        for(double probability : probabilities.values()) {
            cost += index++ * probability;
        }

        System.out.println("STATIC LIST - ALPHABETIC ORDER: " + cost);

        index = 1;
        cost = 0;

        for(double probability : FrequencyCounter.sortByProbability(probabilities).keySet()) {
            cost += index++ * probability;
        }

        System.out.println("STATIC LIST - DECREASING PROBABILITY: " + cost);
    }
}
