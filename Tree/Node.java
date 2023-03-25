public class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node n) {
        right = n;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node n) {
        left = n;
    }
}
