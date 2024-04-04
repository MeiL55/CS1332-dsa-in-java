import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author Akshat Shenoi
 * @version 1.0
 */
public class MaxHeapStudentTest1 {

    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
                heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        /*
                 2
               /   \
              1     4
             / \
            6   8
            ->
                 8
               /   \
              6     4
             / \
            2   1
        */

        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(1);
        data.add(4);
        data.add(6);
        data.add(8);
        heap = new MaxHeap<>(data);

        assertEquals(5, heap.size());

        Integer[] expected = new Integer[11];
        expected[1] = 8;
        expected[2] = 6;
        expected[3] = 4;
        expected[4] = 2;
        expected[5] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap2() {
        /*
                 2
               /   \
              1     4
             / \   /  \
            8   6  9   7
           / \
          5   0
            ->
                 9
               /   \
              8     7
             / \   /  \
            5   6  4   2
           / \
          1   0
        */

        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(1);
        data.add(4);
        data.add(8);
        data.add(6);
        data.add(9);
        data.add(7);
        data.add(5);
        data.add(0);
        heap = new MaxHeap<>(data);

        assertEquals(9, heap.size());

        Integer[] expected = new Integer[19];
        expected[1] = 9;
        expected[2] = 8;
        expected[3] = 7;
        expected[4] = 5;
        expected[5] = 6;
        expected[6] = 4;
        expected[7] = 2;
        expected[8] = 1;
        expected[9] = 0;
        assertArrayEquals(expected, heap.getBackingArray());
    }


    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapNullList() {
        ArrayList<Integer> data = null;
        heap = new MaxHeap<>(data);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testBuildHeapNullElementInList() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(2);
        data.add(null);
        heap = new MaxHeap<>(data);
    }



    @Test(timeout = TIMEOUT)
    public void testAdd() {
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
    public void testAddDuplicate() {
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
        /*
                 8
               /   \
              6     2
             / \   /
            4   1 1
        */
        heap.add(1);

        assertEquals(6, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 8;
        expected[2] = 6;
        expected[3] = 2;
        expected[4] = 4;
        expected[5] = 1;
        expected[6] = 1;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddNull() {
        heap.add(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
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

        assertEquals((Integer) 2, heap.remove());
        assertEquals((Integer) 1, heap.remove());
        assertEquals(0, heap.size());

        Integer[] expected2 = new Integer[MaxHeap.INITIAL_CAPACITY];
        assertArrayEquals(expected2, heap.getBackingArray());
    }
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveEmpty() {
        heap.remove();
    }

    @Test(timeout = TIMEOUT)
    public void testGetMax() {
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
        assertEquals(5, heap.size()); //If you have an error here it means a problem with add method not getMax method

        assertSame(5, heap.getMax());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetMaxEmpty() {
        heap.getMax();
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
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
    public void testClear() {
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
        assertEquals(5, heap.size()); //If you have an error here it means a problem with add method not clear method

        heap.clear();

        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
                heap.getBackingArray());
    }
}
