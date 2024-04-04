public class Driver {

    public static void main(String[] args) {
        //testAdd();
        //testRemove();
        testLastOccurrence();
    }
    public static void testAdd() {
        CircularSinglyLinkedList<Integer> list = new CircularSinglyLinkedList<>();
        list.addToFront(2);
        list.addToFront(1);
        list.addAtIndex(0, 0);
        list.addAtIndex(2, 3);
        list.addAtIndex(4, 5);
        list.addToBack(10);
        printElements(list.toArray());
    }
    public static void testRemove() {
        CircularSinglyLinkedList<Integer> list = new CircularSinglyLinkedList<>();
        list.addToFront(2);
        list.addToFront(1);
        list.addAtIndex(0, 0);
        list.addAtIndex(2, 3);
        list.addAtIndex(4, 5);
        list.addToBack(10);
        list.removeFromFront();
        list.removeFromBack();
        list.removeAtIndex(2);
        printElements(list.toArray());
    }
    public static void testLastOccurrence() {
        CircularSinglyLinkedList<Integer> list = new CircularSinglyLinkedList<>();
        list.addToFront(2);
        list.addToFront(1);
        list.addAtIndex(0, 0);
        list.addAtIndex(2, 3);
        list.addAtIndex(4, 5);
        list.addToBack(10);
        list.removeFromFront();
        list.removeFromBack();
        list.removeAtIndex(2);
        list.addAtIndex(2, 1);
        list.addAtIndex(3, 1);
        list.removeLastOccurrence(1);
        printElements(list.toArray());
    }
    private static void printElements(Object[] arr) {
        for (Object o : arr) {
            System.out.println(o);
        }
    }
}
