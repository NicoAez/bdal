package lab_two;

public class ListNode {
    char value;
    ListNode successor = null;
    boolean notABit = ListSettings.randomBit && Math.random() > 0.5;

    ListNode(char value) {
        this.value = value;
    }

    SearchResult search(char searchValue, int cost) {
        if(this.value != searchValue) {
            SearchResult searchResult = this.successor.search(searchValue, cost + 1);
            this.successor = searchResult.successor;

            return new SearchResult(searchResult.result, this, searchResult.cost, searchResult.bit);
        }

        if(ListSettings.moveToFront)
            return new SearchResult(this, this.successor, cost + 1, true);

        if(ListSettings.bit && this.notABit) {
            this.notABit = false;
            return new SearchResult(this, this.successor, cost + 1, true);
        }

        this.notABit = true;
        return new SearchResult(this, this, cost, false);
    }

    void addElement(ListNode node) {
        if(this.successor == null)
            this.successor = node;
        else
            this.successor.addElement(node);
    }
}
