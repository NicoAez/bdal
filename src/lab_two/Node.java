package lab_two;

public class Node {
    public int value;
    public Node left, right, parent;

    public Node(int value) {
        this.value = value;
        this.left = this.right = this.parent = null;
    }
}
