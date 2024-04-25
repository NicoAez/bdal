package lab_two;

import java.util.ArrayList;
import java.util.TreeMap;

public class Bit {
    double observedRandomCosts;
    double observedDeterminedCosts;
    Bit(TreeMap<Character, Double> probabilities, String requests) {
        ListSettings.bit = true;
        ListSettings.moveToFront = false;

        this.observedRandomCosts = start(probabilities, requests, true);
        this.observedDeterminedCosts = start(probabilities, requests, false);
    }

    private double start(TreeMap<Character, Double> probabilities, String requests, boolean randomBits) {
        ListSettings.randomBit = randomBits;

        var randomSequence = FrequencyCounter.getRequested(probabilities);
        FrequencyCounter.shuffleArray(randomSequence);

        ListNode list = new ListNode((char) randomSequence[0]);
        for(int i = 1; i < randomSequence.length; i++) {
            list.addElement(new ListNode((char) randomSequence[i]));
        }

        ArrayList<Integer> costs = new ArrayList<>();
        for(int i = 0; i < requests.length(); i++) {
            SearchResult searchResult = list.search(requests.charAt(i), 0);
            if(searchResult.result != list && searchResult.bit) {
                searchResult.result.successor = list;
                list = searchResult.result;
            }

            costs.add(searchResult.cost);
        }

        double a = costs.stream().mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println("Random Bits: " + randomBits + ", " + a);

        return a;
    }
}
