package ejr09;

import java.util.EmptyStackException;

public class StackLink<T> {
    private static class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> top;

    public StackLink() {
        this.top = null;
    }

    public void push(T item) {
        Node<T> node = new Node<>(item);
        node.next = top;
        top = node;
    }

    public T pop() {
        if (top == null) {
            throw new EmptyStackException();
        }
        T item = top.data;
        top = top.next;
        return item;
    }

    public T peek() {
        if (top == null) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}
