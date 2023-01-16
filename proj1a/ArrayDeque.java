public class ArrayDeque<T> {
    private static final int INIT_ARRAY_SIZE = 8;
    private T[] array;
    private int ARRAY_SIZE;
    private int firstIndex;
    private int size;

    public ArrayDeque() {
        array = (T[]) new Object[INIT_ARRAY_SIZE];
        ARRAY_SIZE = INIT_ARRAY_SIZE;
        firstIndex = 0;
        size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == ARRAY_SIZE) {
            extend();
        }
        int newFirstIndex = (firstIndex + ARRAY_SIZE - 1) % ARRAY_SIZE;
        array[newFirstIndex] = item;
        firstIndex = newFirstIndex;
        size++;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == ARRAY_SIZE) {
            extend();
        }
        array[(firstIndex + size) % ARRAY_SIZE] = item;
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
        for (int i = 0; i < size; i++) {
            System.out.println(array[(firstIndex + i) % ARRAY_SIZE]);
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     */
    public T removeFirst() {
        T first = array[firstIndex];
        firstIndex = (firstIndex + 1) % ARRAY_SIZE;
        size--;
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        T result = get(size - 1);
        size--;
        return result;
    }

    /**
     * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new RuntimeException("index is out of range");
        }
        return array[(firstIndex + index) % ARRAY_SIZE];
    }

    private void extend() {
        int newArraySize = ARRAY_SIZE + ARRAY_SIZE / 2;
        T[] newArray = (T[]) new Object[newArraySize];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        ARRAY_SIZE = newArraySize;
    }
}
