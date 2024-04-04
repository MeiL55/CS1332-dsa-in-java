import java.util.NoSuchElementException;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests include the baisc test provided by the TAs including additoinal tests to
 * test for exceptions
 *
 * @author avarma49 + CS 1332 TAs
 * @version 1.0
 */
public class CircularSinglyLinkedListStudentTest1 {

    private static final int TIMEOUT = 200;
    private CircularSinglyLinkedList<String> list;

    @Before
    public void setUp() {
        list = new CircularSinglyLinkedList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex() {
        list.addAtIndex(0, "2a");   // 2a
        list.addAtIndex(0, "1a");   // 1a, 2a
        list.addAtIndex(2, "4a");   // 1a, 2a, 4a
        list.addAtIndex(2, "3a");   // 1a, 2a, 3a, 4a
        list.addAtIndex(0, "0a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexNoneZeroWhenEmpty() {
        String message = "";
        try {
            list.addAtIndex(1, "2a");
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexNegative() {
        String message = "";
        try {
            list.addAtIndex(-1, "2a");
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexEqualToSize() {
        String message = "";
        list.addAtIndex(0, "2a");
        try {
            list.addAtIndex(1, "33a");
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndexGreaterThanSize() {
        String message = "";
        list.addAtIndex(0, "2a");
        try {
            list.addAtIndex(2, "33a");
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }
    @Test(timeout = TIMEOUT)
    public void testAddAtIndexNullData() {
        String message = "";
        try {
            list.addAtIndex(0, null);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }
    @Test(timeout = TIMEOUT)
    public void testAddToFront() {
        list.addToFront("4a");  // 4a
        list.addToFront("3a");  // 3a, 4a
        list.addToFront("2a");  // 2a, 3a, 4a
        list.addToFront("1a");  // 1a, 2a, 3a, 4a
        list.addToFront("0a");  // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddOnlyHead() {
        list.addToFront("4a");
        CircularSinglyLinkedListNode cur = list.getHead();
        assertEquals((String)cur.getData(), "4a");
        assertEquals(cur, list.getHead());
        assertEquals(cur.getNext(), list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFrontNullData() {
        String message = "";
        try {
            list.addToFront(null);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack() {
        list.addToBack("0a");   // 0a
        list.addToBack("1a");   // 0a, 1a
        list.addToBack("2a");   // 0a, 1a, 2a
        list.addToBack("3a");   // 0a, 1a, 2a, 3a
        list.addToBack("4a");   // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBackNullData() {
        String message = "";
        try {
            list.addToBack(null);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndex() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, temp);   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeAtIndex(2));    // 0a, 1a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexNegativeIndex() {
        String message = "";

        list.addToBack("10");
        list.addToBack("20");
        list.addToBack("30");

        try {
            list.removeAtIndex(-1);
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexGreaterThanSize() {
        String message = "";

        list.addToBack("10");
        list.addToBack("20");
        list.addToBack("30");


        try {
            list.removeAtIndex(4);
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveAtIndexEqualToSize() {
        String message = "";

        list.addToBack("10");
        list.addToBack("20");
        list.addToBack("30");

        try {
            list.removeAtIndex(3);
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront() {
        String temp = "0a";

        list.addAtIndex(0, temp);   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, "5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromFront());   // 1a, 2a, 3a, 4a, 5a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontOneElement() {
        list.addAtIndex(0, "0a");   // 0a
        assertEquals(1, list.size());
        String s = (String) list.removeFromFront();
        assertEquals(0, list.size());
        assertEquals(s, "0a");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackOneElement() {
        list.addAtIndex(0, "0a");   // 0a
        assertEquals(1, list.size());
        String s = (String) list.removeFromBack();
        assertEquals(0, list.size());
        assertEquals(s, "0a");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontTwoElement() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a
        assertEquals(2, list.size());
        String s = (String) list.removeFromFront();
        assertEquals(1, list.size());
        assertEquals(s, "0a");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackTwoElement() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a
        assertEquals(2, list.size());
        String s = (String) list.removeFromBack();
        assertEquals(1, list.size());
        assertEquals(s, "1a");
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromFrontEmptyList() {
        String message = "";

        try {
            list.removeFromFront();
        } catch (NoSuchElementException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBackEmptyList() {
        String message = "";

        try {
            list.removeFromBack();
        } catch (NoSuchElementException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }
    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack() {
        String temp = "5a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        list.addAtIndex(5, temp);   // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, list.size());

        assertSame(temp, list.removeFromBack());    // 0a, 1a, 2a, 3a, 4a

        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
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

    @Test(timeout = TIMEOUT)
    public void testGetOneElement() {
        list.addAtIndex(0, "0a");   // 0a
        assertEquals(1, list.size());

        assertEquals("0a", list.get(0));
    }
    @Test(timeout = TIMEOUT)
    public void testGetIndexSmallerThanZero() {
        String message = "";

        try {
            list.get(-1);
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testGetEqualToSize() {
        String message = "";
        list.addToFront("10");
        try {
            list.get(1);
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testGetEmptyList() {
        String message = "";
        try {
            list.get(1);
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }
    @Test(timeout = TIMEOUT)
    public void testGetGreaterThanSize() {
        String message = "";
        list.addToFront("10");
        try {
            list.get(2);
        } catch (IndexOutOfBoundsException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }
    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(list.isEmpty());

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

        list.clear();

        assertEquals(0, list.size());
        assertNull(list.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrence() {
        String temp = "2a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, temp);   // 0a, 1a, 2a, 2a
        list.addAtIndex(4, "3a");   // 0a, 1a, 2a, 2a, 3a
        list.addAtIndex(5, "4a");   // 0a, 1a, 2a, 2a, 3a, 4a
        assertEquals(6, list.size());

        assertSame(temp, list.removeLastOccurrence("2a")); // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrenceDataIsInTheEnd() {
        String temp = "0a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "0a");   // 0a, 1a, 2a, 2a
        list.addAtIndex(4, "3a");   // 0a, 1a, 2a, 2a, 3a
        list.addAtIndex(5, "4a");   // 0a, 1a, 2a, 2a, 3a, 4a
        list.addAtIndex(6, temp);   // 0a, 1a, 2a, 2a, 3a, 4a
        assertEquals(7, list.size());

        assertSame(temp, list.removeLastOccurrence("0a")); // 0a, 1a, 2a, 3a, 4a
        assertEquals(6, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccurrenceDataOnlyHead() {
        String temp = "0a";

        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "8a");   // 0a, 1a, 2a, 2a
        list.addAtIndex(4, "3a");   // 0a, 1a, 2a, 2a, 3a
        list.addAtIndex(5, "4a");   // 0a, 1a, 2a, 2a, 3a, 4a
        list.addAtIndex(6, "5a");   // 0a, 1a, 2a, 2a, 3a, 4a
        assertEquals(7, list.size());

        assertSame(temp, list.removeLastOccurrence("0a")); // 0a, 1a, 2a, 3a, 4a
        assertEquals(6, list.size());

        CircularSinglyLinkedListNode<String> cur = list.getHead();
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("8a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals(list.getHead(), cur);
    }
    @Test(timeout = TIMEOUT)
    public void testLastOccurangeNullData() {
        String message = "";
        try {
            list.removeLastOccurrence(null);
        } catch (IllegalArgumentException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccuranceDataNotFoundEmptyList() {
        String message = "";
        try {
            list.removeLastOccurrence("10");
        } catch (NoSuchElementException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }

    @Test(timeout = TIMEOUT)
    public void testLastOccuranceDataNotFoundNotEmptyList() {
        String message = "";
        list.addToFront("20");
        list.addToFront("30");
        list.addToFront("20");
        list.addToFront("50");
        try {
            list.removeLastOccurrence("10");
        } catch (NoSuchElementException e) {
            message = e.getMessage();
        }
        assertNotEquals(message, "");
    }
    @Test(timeout = TIMEOUT)
    public void testToArray() {
        list.addAtIndex(0, "0a");   // 0a
        list.addAtIndex(1, "1a");   // 0a, 1a
        list.addAtIndex(2, "2a");   // 0a, 1a, 2a
        list.addAtIndex(3, "3a");   // 0a, 1a, 2a, 3a
        list.addAtIndex(4, "4a");   // 0a, 1a, 2a, 3a, 4a
        assertEquals(5, list.size());

        String[] expected = new String[5];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, list.toArray());
    }
}
