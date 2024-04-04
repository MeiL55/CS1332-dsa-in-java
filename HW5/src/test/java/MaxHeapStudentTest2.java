import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MaxHeapStudentTest2 {
    public static final int TIMEOUT = 200;
    public MaxHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY], heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeapRoot() {
        /**
         0
         /   \
         */
        ArrayList<Integer> data = new ArrayList<>();
        data.add(0);
        heap = new MaxHeap<>(data);
        assertEquals(1, heap.size());
        Integer[] expected = new Integer[3];
        expected[0] = null;
        expected[1] = 0;
        assertArrayEquals(expected, heap.getBackingArray());
    }
    /**

    @Test(timeout = TIMEOUT)
    public void testBuildHeapNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            heap = new MaxHeap<>(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ArrayList<Integer> data = new ArrayList<>();
            data.add(15);
            data.add(21);
            data.add(null);
            heap = new MaxHeap<>(data);
        });
    }
     **/

    @Test(timeout = TIMEOUT)
    public void testBuildHeapNormal() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(15);
        data.add(21);
        data.add(25);
        data.add(4);
        data.add(7);
        data.add(28);
        data.add(14);
        data.add(13);
        data.add(11);
        data.add(10);
        data.add(6);
        data.add(1);
        heap = new MaxHeap<>(data);

        assertEquals(12, heap.size());

        Integer[] expected = new Integer[25];
        expected[0] = null;
        expected[1] = 28;
        expected[2] = 21;
        expected[3] = 25;
        expected[4] = 13;
        expected[5] = 10;
        expected[6] = 15;
        expected[7] = 14;
        expected[8] = 4;
        expected[9] = 11;
        expected[10] = 7;
        expected[11] = 6;
        expected[12] = 1;

        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddProvided() {
        /*
                 4
        */
        heap.add(4);

        /*
                 6
               /
              4
        */
        heap.add(6);

        /*
                 6
               /   \
              4     2
        */
        heap.add(2);

        /*
                 8
               /   \
              6     2
             /
            4
        */
        heap.add(8);

        /*
                 8
               /   \
              6     2
             / \
            4   1
        */
        heap.add(1);

        assertEquals(5, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 8;
        expected[2] = 6;
        expected[3] = 2;
        expected[4] = 4;
        expected[5] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddResize() {
        heap.add(8);
        heap.add(33);
        heap.add(19);
        heap.add(45);
        heap.add(12);
        heap.add(27);
        heap.add(3);
        heap.add(41);
        heap.add(20);
        heap.add(36);
        heap.add(5);
        heap.add(48);
        heap.add(17);
        heap.add(29);

        assertEquals(14, heap.size());

        Integer[] expected = new Integer[26];
        expected[0] = null;
        expected[1] = 48;
        expected[2] = 41;
        expected[3] = 45;
        expected[4] = 33;
        expected[5] = 36;
        expected[6] = 27;
        expected[7] = 29;
        expected[8] = 8;
        expected[9] = 20;
        expected[10] = 12;
        expected[11] = 5;
        expected[12] = 19;
        expected[13] = 17;
        expected[14] = 3;

        assertArrayEquals(expected, heap.getBackingArray());
    }
    /**
    @Test(timeout = TIMEOUT)
    public void testAddNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            heap.add(null);
        });
    }
     **/

    @Test(timeout = TIMEOUT)
    public void testRemoveProvided() {
        /*
                 8
               /   \
              6     4
             / \
            1   2
        */
        heap.add(4);
        heap.add(1);
        heap.add(6);
        heap.add(2);
        heap.add(8);
        assertEquals(5, heap.size());

        /*
                 6
               /   \
              2     4
             /
            1
        */
        assertEquals((Integer) 8, heap.remove());

        /*
                 4
               /   \
              2     1
        */
        assertEquals((Integer) 6, heap.remove());

        /*
                 2
               /
              1
        */
        assertEquals((Integer) 4, heap.remove());

        assertEquals(2, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 2;
        expected[2] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }
    /**
    @Test(timeout = TIMEOUT)
    public void testRemoveNull() {
        assertThrows(NoSuchElementException.class, () -> {
            heap.remove();
        });
    }
     **/

    @Test(timeout = TIMEOUT)
    public void testGetMaxProvided() {
        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        assertSame(5, heap.getMax());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyProvided() {
        assertTrue(heap.isEmpty());

        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        assertFalse(heap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClearProvided() {
        /*
                 5
               /   \
              4     3
             / \
            2   1
         */

        heap.add(5);
        heap.add(4);
        heap.add(3);
        heap.add(2);
        heap.add(1);
        assertEquals(5, heap.size());

        heap.clear();

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
                heap.getBackingArray());
    }

}
