package ejr09;

public class Node<E> {
    private E data;
    private Node<E> left, right;

    public Node(E data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public E getData() {
        return this.data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getLeft() {
        return this.left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return this.right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
}


