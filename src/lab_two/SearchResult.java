package lab_two;

public class SearchResult {
    ListNode result;
    ListNode successor;
    int cost;
    boolean bit;

    SearchResult(ListNode node, ListNode successor, int cost, boolean bit) {
        this.result = node;
        this.cost = cost;
        this.successor = successor;
        this.bit = bit;
    }
}
