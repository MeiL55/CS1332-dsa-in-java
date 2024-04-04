
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 * This is a basic tests + Test Exceptions + Other Tests
 *
 *
 * @author CS 1332 TAs (for basic tests) & Richard Hartnett
 * @version 1.0
 */
public class ArrayListStudentTest3 {

    private static final int TIMEOUT = 200;
    private ArrayList<String> list;

    @Before
    public void setUp() {
        list = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a


        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";

        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToIndexOverCapacity() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        list.addAtIndex(6, "6a");   // 0a, 1a, 2a, 3a, 4a, ... 6a
        list.addAtIndex(7, "7a");   // 0a, 1a, 2a, 3a, 4a, ... 7a
        list.addAtIndex(8, "9a");   // 0a, 1a, 2a, 3a, 4a, ... 8a
        list.addAtIndex(8, "8a");   // 0a, 1a, 2a, 3a, 4a, ... 9a
        list.addAtIndex(10, "10a"); // 0a, 1a, 2a, 3a, 4a, ... 10a
        list.addAtIndex(11, "11a"); // 0a, 1a, 2a, 3a, 4a, ... 11a
        list.addAtIndex(12, "12a"); // 0a, 1a, 2a, 3a, 4a, ... 12a
        list.addAtIndex(13, "13a"); // 0a, 1a, 2a, 3a, 4a, ... 13a
        list.addAtIndex(14, "14a"); // 0a, 1a, 2a, 3a, 4a, ... 14a
        list.addAtIndex(15, "15a"); // 0a, 1a, 2a, 3a, 4a, ... 15a
        list.addAtIndex(16, "16a"); // 0a, 1a, 2a, 3a, 4a, ... 16a
        list.addAtIndex(17, "17a"); // 0a, 1a, 2a, 3a, 4a, ... 17a
        list.addAtIndex(18, "19a"); // 0a, 1a, 2a, 3a, 4a, ... 17a, 19a
        list.addAtIndex(18, "18a"); // 0a, 1a, 2a, 3a, 4a, ... 17a, 18a, 19a

        assertEquals(20, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        expected[10] = "10a";
        expected[11] = "11a";
        expected[12] = "12a";
        expected[13] = "13a";
        expected[14] = "14a";
        expected[15] = "15a";
        expected[16] = "16a";
        expected[17] = "17a";
        expected[18] = "18a";
        expected[19] = "19a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToIndexNull() {
        list.addAtIndex(0,null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddToIndexBelowBounds() {
        list.addToFront("1a");
        list.addAtIndex(-2,"6a");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddToIndexAboveBounds() {
        list.addToFront("1a");
        list.addAtIndex(2,"6a");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontOverCapacity() {
        list.addToFront("19a");  // 19a
        list.addToFront("18a");  // 19a, 18a
        list.addToFront("17a");  // 19a, 18a, 17a
        list.addToFront("16a");  // 19a, 18a, 17a, 16a
        list.addToFront("15a");  // 19a, 18a, 17a, ... 15a
        list.addToFront("14a");  // 19a, 18a, 17a, ... 14a
        list.addToFront("13a");  // 19a, 18a, 17a, ... 13a
        list.addToFront("12a");  // 19a, 18a, 17a, ... 12a
        list.addToFront("11a");  // 19a, 18a, 17a, ... 11a
        list.addToFront("10a");  // 19a, 18a, 17a, ... 10a
        list.addToFront("9a");   // 19a, 18a, 17a, ... 9a
        list.addToFront("8a");   // 19a, 18a, 17a, ... 8a
        list.addToFront("7a");   // 19a, 18a, 17a, ... 7a
        list.addToFront("6a");   // 19a, 18a, 17a, ... 6a
        list.addToFront("5a");   // 19a, 18a, 17a, ... 5a
        list.addToFront("4a");   // 19a, 18a, 17a, ... 4a
        list.addToFront("3a");   // 19a, 18a, 17a, ... 3a
        list.addToFront("2a");   // 19a, 18a, 17a, ... 2a
        list.addToFront("1a");   // 19a, 18a, 17a, ... 1a
        list.addToFront("0a");   // 19a, 18a, 17a, ... 0a

        assertEquals(20, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        expected[10] = "10a";
        expected[11] = "11a";
        expected[12] = "12a";
        expected[13] = "13a";
        expected[14] = "14a";
        expected[15] = "15a";
        expected[16] = "16a";
        expected[17] = "17a";
        expected[18] = "18a";
        expected[19] = "19a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToFrontNull() {
        list.addToFront(null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackOverCapacity() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a
        list.addToBack("5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        list.addToBack("6a");   // 0a, 1a, 2a, 3a, 4a, ... 6a
        list.addToBack("7a");   // 0a, 1a, 2a, 3a, 4a, ... 7a
        list.addToBack("8a");   // 0a, 1a, 2a, 3a, 4a, ... 8a
        list.addToBack("9a");   // 0a, 1a, 2a, 3a, 4a, ... 9a
        list.addToBack("10a");  // 0a, 1a, 2a, 3a, 4a, ... 10a
        list.addToBack("11a");  // 0a, 1a, 2a, 3a, 4a, ... 11a
        list.addToBack("12a");  // 0a, 1a, 2a, 3a, 4a, ... 12a
        list.addToBack("13a");  // 0a, 1a, 2a, 3a, 4a, ... 13a
        list.addToBack("14a");  // 0a, 1a, 2a, 3a, 4a, ... 14a
        list.addToBack("15a");  // 0a, 1a, 2a, 3a, 4a, ... 15a
        list.addToBack("16a");  // 0a, 1a, 2a, 3a, 4a, ... 16a
        list.addToBack("17a");  // 0a, 1a, 2a, 3a, 4a, ... 17a
        list.addToBack("18a");  // 0a, 1a, 2a, 3a, 4a, ... 18a
        list.addToBack("19a");  // 0a, 1a, 2a, 3a, 4a, ... 19a

        assertEquals(20, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY * 4];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        expected[10] = "10a";
        expected[11] = "11a";
        expected[12] = "12a";
        expected[13] = "13a";
        expected[14] = "14a";
        expected[15] = "15a";
        expected[16] = "16a";
        expected[17] = "17a";
        expected[18] = "18a";
        expected[19] = "19a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToBackNull() {
        list.addToBack(null);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexBelowBounds() {
        list.addToFront("1a");
        list.removeAtIndex(-2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveAtIndexAboveBounds() {
        list.addToFront("1a");
        list.removeAtIndex(1);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a"; // For equality checking.

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "1a";
        expected[1] = "2a";
        expected[2] = "3a";
        expected[3] = "4a";
        expected[4] = "5a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromFrontEmpty() {
        list.removeFromFront();
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a"; // For equality checking.

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        // assertSame checks for reference equality whereas assertEquals checks
        // value equality.
        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        Object[] expected = new Object[ArrayList.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.getBackingArray());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromBackEmpty() {
        list.removeFromBack();
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertEquals("0a", list.get(0));
        assertEquals("1a", list.get(1));
        assertEquals("2a", list.get(2));
        assertEquals("3a", list.get(3));
        assertEquals("4a", list.get(4));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetBelowBounds() {
        list.addToFront("1a");
        list.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetAboveBounds() {
        list.addToFront("1a");
        list.get(2);
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        // Should be empty at initialization
        assertTrue(list.isEmpty());

        // Should not be empty after adding elements
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        assertFalse(list.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        // Clearing the list should empty the array and reset size
        list.clear();

        assertEquals(0, list.size());
        assertArrayEquals(new Object[ArrayList.INITIAL_CAPACITY],
                list.getBackingArray());
    }
}
