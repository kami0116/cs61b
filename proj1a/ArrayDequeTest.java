import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    private ArrayDeque<Integer> deque;

    @Before
    public void setUp() {
        deque = new ArrayDeque<>();
    }

    @Test
    public void testInit() {
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testAddLast() {
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);

        assertEquals(3, deque.size());
        assertEquals(7, (int) deque.get(2));
    }

    @Test
    public void testAddFront() {
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        assertEquals(3, deque.size());
        assertEquals(1, (int) deque.get(0));
    }

    @Test
    public void testRemoveLast() {
        deque.addFirst(4);
        deque.addFirst(5);
        deque.addFirst(6);

        assertEquals(4, (int) deque.removeLast());
        assertEquals(2, deque.size());
    }

    @Test
    public void testRemoveFront() {
        deque.addFirst(7);
        deque.addFirst(8);
        deque.addFirst(9);

        assertEquals(9, (int) deque.removeFirst());
        assertEquals(8, (int) deque.removeFirst());
        assertEquals(7, (int) deque.removeFirst());
        assertEquals(0, deque.size());
    }

    @Test
    public void testDequeUnderflowRemoveFirst() {
        assertNull(deque.removeFirst());
    }

    @Test
    public void testDequeUnderflowRemoveLast() {
        assertNull(deque.removeLast());
    }

    @Test
    public void testDequeOverflowAddLast() {
        for (int i = 0; i < 8; i++) {
            deque.addLast(i);
        }
        deque.removeFirst();
        deque.addLast(8);
        deque.addLast(9);
        deque.printDeque();

    }
}
