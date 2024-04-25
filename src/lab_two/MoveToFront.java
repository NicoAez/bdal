package lab_two;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MoveToFront {
    double observedCost = 0.00;
    MoveToFront(TreeMap<Character, Double> probabilities, String requests) {
        var sequence = FrequencyCounter.getRequested(probabilities);

        // expected value
        double sum = 0;
        for(int i = 0; i < sequence.length; i++) {
            for(int j = 0; j < sequence.length; j++) {
                sum += (probabilities.get(sequence[i]) * probabilities.get(sequence[j]))
                        / (probabilities.get(sequence[i]) + probabilities.get(sequence[j]));
            }
        }
        System.out.println("Predicted for MTF: " + (0.5 + sum));

        // actual value
        ListSettings.bit = false;
        ListSettings.moveToFront = true;

        ListNode list = new ListNode((char) sequence[0]);
        for(int i = 1; i < sequence.length; i++) {
            list.addElement(new ListNode((char) sequence[i]));
        }

        ArrayList<Integer> costs = new ArrayList<>();
        for(int i = 0; i < requests.length(); i++) {
            SearchResult result = list.search(requests.charAt(i), 0);
            if(result.result != list) {
                result.result.successor = list;
                list = result.result;
            }

            costs.add(result.cost);
        }

        this.observedCost = costs.stream().mapToInt(Integer::intValue).average().getAsDouble();
        System.out.println("Observed for MTF: " + this.observedCost);
    }
}
