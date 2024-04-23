package lab_two;

import java.util.*;

public class FrequencyCounter {
    public static TreeMap<Character, Double> getFrequencies(String string) {
        Map<Character, Integer> counters = new HashMap<>();

        // counter characters
        string
                .chars()
                .mapToObj(c -> (char) c)
                .forEach(c -> {
                    if(!counters.containsKey(c))
                        counters.put(c, 1);
                    else
                        counters.compute(c, (key, value) -> value += 1);
                });

        // get sum of all chars
        int totalNumberOfCharacters = counters.values().stream().mapToInt(Integer::intValue).sum();

        // prepare map to return
        TreeMap<Character, Double> result = new TreeMap<>();
        counters.forEach((key, value) -> {
            result.put(key, (double) value / totalNumberOfCharacters);
        });

        return result;
    }

    public static NavigableMap<Double, Character> sortByProbability(Map<Character, Double> map) {
        TreeMap<Double, Character> result = new TreeMap<>();
        map.forEach((key, value) -> {
            result.put(value, key);
        });

        return result.descendingMap();
    }
}
