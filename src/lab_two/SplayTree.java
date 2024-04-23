package lab_two;

public class SplayTree {
    private Node root;

    public SplayTree() {
        this.root = null;
    }

    private void rotateLeft(Node node) {
        Node rightChild = node.right;

        if(rightChild != null)  {
            node.right = rightChild.left;

            if(rightChild.left != null)
                rightChild.left.parent = node;

            rightChild.parent = node.parent;
        }

        if(node.parent == null)
            this.root = rightChild;
        else if(node == node.parent.left)
            node.parent.left = rightChild;
        else
            node.parent.right = rightChild;

        if(rightChild != null)
            rightChild.left = node;

        node.parent = rightChild;
    }

    private void rotateRight(Node node) {
        Node leftChild = node.left;

        if(leftChild != null) {
            node.left = leftChild.right;

            if(leftChild.right != null)
                leftChild.right.parent = node;

            leftChild.parent = node.parent;
        }

        if(node.parent == null)
            this.root = leftChild;
        else if(node == node.parent.left)
            node.parent.left = leftChild;
        else
            node.parent.right = leftChild;

        if(leftChild != null)
            leftChild.right = node;

        node.parent = leftChild;
    }

    private void splay(Node node) {
        while(node.parent != null) {
            if(node.parent.parent == null) {
                if(node.parent.left == node)
                    rotateRight(node.parent);
                else
                    rotateLeft(node.parent);
            } else if(node.parent.left == node && node.parent.parent.left == node.parent) {
                rotateRight(node.parent.parent);
                rotateRight(node.parent);
            } else if(node.parent.right == node && node.parent.parent.right == node.parent) {
                rotateLeft(node.parent.parent);
                rotateLeft(node.parent);
            } else if (node.parent.left == node && node.parent.parent.right == node.parent) {
                rotateRight(node.parent);
                rotateLeft(node.parent);
            } else {
                rotateLeft(node.parent);
                rotateRight(node.parent);
            }
        }
    }

    public void insert(int value) {
        Node toInsert = new Node(value);
        if(this.root == null) {
            this.root = toInsert;
            return;
        }

        Node parent = null;
        Node node = this.root;
        while(node != null) {
            parent = node;
            node = value > node.value ? node.right : node.left;
        }

        if(value > parent.value) {
            parent.right = toInsert;
            toInsert.parent = parent;
        } else {
            parent.left = toInsert;
            toInsert.parent = parent;
        }

        splay(toInsert);
    }

    public Node search(int value) {
        Node result = this.root;

        while(result != null) {
            if(value == result.value) {
                splay(result);
                return result;
            }

            result = value > result.value ? result.right : result.left;
        }

        return null;
    }

    private static void print(Node node, int space) {
        if(node == null)
            return;

        print(node.right, space+5);

        System.out.print("\n");
        for(int i = 0; i < space; i++)
            System.out.print(" ");
        System.out.print(node.value + "\n");

        print(node.left, space+5);
    }

    public void printTree() {
        System.out.println("------------------------------");
        print(this.root, 0);
    }
}