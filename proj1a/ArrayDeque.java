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
        if (size == 0) {
            return null;
        }
        T first = array[firstIndex];
        firstIndex = (firstIndex + 1) % ARRAY_SIZE;
        size--;
        checkAndShrink();
        return first;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T result = array[(firstIndex + size - 1) % ARRAY_SIZE];
        size--;
        checkAndShrink();
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
        resize(ARRAY_SIZE / 2 * 3);
    }

    private void checkAndShrink() {
        int newArraySize = ARRAY_SIZE / 3;
        if (size < ARRAY_SIZE / 5 && newArraySize > INIT_ARRAY_SIZE) {
            resize(newArraySize);
        }
    }

    private void resize(int newArraySize) {
        T[] newArray = (T[]) new Object[newArraySize];
        if (firstIndex + size > ARRAY_SIZE) {
            System.arraycopy(array, firstIndex, newArray, 0, size - firstIndex);
            System.arraycopy(array, 0, newArray, size - firstIndex, firstIndex);
        } else {
            System.arraycopy(array, firstIndex, newArray, 0, size);
        }
        array = newArray;
        firstIndex = 0;
        ARRAY_SIZE = newArraySize;
    }
}
