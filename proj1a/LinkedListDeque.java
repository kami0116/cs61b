public class LinkedListDeque<T> {
    private class Node {
        T value;
        Node prev;
        Node next;

        Node(T value, Node prev, Node next) {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private final Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        Node node = new Node(item, sentinel, sentinel.next);
        node.next.prev = node;
        sentinel.next = node;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        Node node = new Node(item, sentinel.prev, sentinel);
        node.prev.next = node;
        sentinel.prev = node;
        size++;
    }

    /**
     * Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque.
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    public void printDeque() {
        for (Node p = sentinel.next; p != sentinel; p = p.next) {
            System.out.println(p.value);
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            throw new RuntimeException("deque is empty");
        }
        Node node = sentinel.next;
        sentinel.next = node.next;
        node.next.prev = sentinel;
        size--;
        return node.value;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            throw new RuntimeException("deque is empty");
        }
        Node node = sentinel.prev;
        node.prev.next = sentinel;
        sentinel.prev = node.prev;
        size--;
        return node.value;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index > size) {
            throw new RuntimeException("index is out of range");
        }
        Node p = sentinel.next;
        for (int i = 0; i != index; i++) {
            p = p.next;
        }
        return p.value;
    }

    /**
     * Same as get, but uses recursion.
     */
    public T getRecursive(int index) {
        if (index > size) {
            throw new RuntimeException("index is out of range");
        }
        return getHelper(index, sentinel.next).value;
    }

    private Node getHelper(int index, Node node) {
        if (index == 0) {
            return node;
        } else {
            return getHelper(index - 1, node.next);
        }
    }
}
