import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Your implementation of an AVL.
 *
 * @author Mei Li
 * @version 1.0
 * @userid mli783
 * @GTID 903904935
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Input collection is null.");
        }
        for (T item : data) {
            if (item == null) {
                throw new NoSuchElementException("Element is null.");
            }
            add(item);
        }
    }

    /**
     * Helper method that will perform rotations.
     * @param node the node whose BF indicates that it is not balanced.
     * @return the same node after change.
     */
    private AVLNode<T> balance(AVLNode<T> node) {
        if (node.getBalanceFactor() < -1) {
            if (node.getRight().getBalanceFactor() <= 0) {
                node = rotateLeft(node);
            } else {
                node = rightLeftRotation(node);
            }
        } else if (node.getBalanceFactor() > 1) {
            if (node.getLeft().getBalanceFactor() >= 0) {
                node = rotateRight(node);
            } else {
                node = leftRightRotation(node);
            }
        }
        return node;
    }

    /**
     * Helper method that updates the node's height and balance factor.
     * @param node current node to be updated.
     */
    private void update(AVLNode<T> node) {
        int leftHeight = heightHelper(node.getLeft());
        int rightHeight = heightHelper(node.getRight());
        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
        node.setBalanceFactor(leftHeight - rightHeight);
    }

    /**
     * Helper method that performs left rotation.
     * @param node the node that is imbalanced
     * @return the same node after rotation
     */
    private AVLNode<T> rotateLeft(AVLNode<T> node) {
        AVLNode<T> changed = node.getRight();
        node.setRight(changed.getLeft());
        changed.setLeft(node);
        update(node);
        update(changed);
        return changed;
    }
    /**
     * Helper method that performs right rotation.
     * @param node the node that is imbalanced
     * @return the same node after rotation
     */
    private AVLNode<T> rotateRight(AVLNode<T> node) {
        AVLNode<T> changed = node.getLeft();
        node.setLeft(changed.getRight());
        changed.setRight(node);
        update(node);
        update(changed);
        return changed;
    }

    /**
     * Helper method that performs left right rotation.
     * @param node the node that is imbalanced
     * @return the same node after rotation.
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        node.setLeft(rotateLeft(node.getLeft()));
        return rotateRight(node);
    }
    /**
     * Helper method that performs right left rotation.
     * @param node the node that is imbalanced
     * @return the same node after rotation.
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        node.setRight(rotateRight(node.getRight()));
        return rotateLeft(node);
    }
    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        root = addHelper(data, root);
    }

    /**
     * Helper method that recursively adds.
     * @param data the data to be added.
     * @param curr the current node in the AVL.
     * @return an node to be reinforced.
     */
    private AVLNode<T> addHelper(T data, AVLNode<T> curr) {
        if (curr == null) {
            size++;
            return new AVLNode<>(data);
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        }
        update(curr);
        return balance(curr);
    }
    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        AVLNode<T> dummy1 = new AVLNode<>(null);
        root = removeHelper(data, root, dummy1);
        return dummy1.getData();
    }
    /**
     * Helper method that recursively removes.
     * @param data the data to be removed.
     * @param curr the current node in the AVL.
     * @param dummy1 the dummy node.
     * @return an node to be reinforced.
     */
    private AVLNode<T> removeHelper(T data, AVLNode<T> curr, AVLNode<T> dummy1) {
        if (curr == null) {
            throw new NoSuchElementException("Data does not exist.");
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(data, curr.getLeft(), dummy1));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(data, curr.getRight(), dummy1));
        } else {
            size--;
            dummy1.setData(curr.getData());
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() == null) {
                return curr.getRight();
            } else if (curr.getRight() == null) {
                return curr.getLeft();
            } else {
                AVLNode<T> dummy2 = new AVLNode<>(null);
                curr.setRight(successorHelper(curr.getRight(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Helper method that removes the successor.
     * @param curr current node in the AVL.
     * @param dummy2 dummy node.
     * @return the node to be reinforced.
     */
    private AVLNode<T> successorHelper(AVLNode<T> curr, AVLNode<T> dummy2) {
        if (curr.getLeft() == null) {
            dummy2.setData(curr.getData());
            return curr.getRight();
        }
        curr.setLeft(successorHelper(curr.getLeft(), dummy2));
        update(curr);
        return balance(curr);
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        return getHelper(data, root).getData();
    }

    /**
     * Helper method that gets specific node.
     * @param data the data to be searched.
     * @param curr the current node in the AVL.
     * @return the node obtained.
     */
    private AVLNode<T> getHelper(T data, AVLNode<T> curr) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        if (curr == null) {
            throw new NoSuchElementException("Data does not exist.");
        }
        if (data.compareTo(curr.getData()) < 0) {
            curr = getHelper(data, curr.getLeft());
        } else if (data.compareTo(curr.getData()) > 0) {
            curr = getHelper(data, curr.getRight());
        }
        return curr;
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        try {
            getHelper(data, root);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * Should be O(1).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Helper method that gets height of specific node.
     * @param node the node to get its height.
     * @return the height of the current node.
     */
    private int heightHelper(AVLNode<T> node) {
        return (node == null) ? -1 : node.getHeight();
    }
    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Find all elements within a certain distance from the given data.
     * "Distance" means the number of edges between two nodes in the tree.
     *
     * To do this, first find the data in the tree. Keep track of the distance
     * of the current node on the path to the data (you can use the return
     * value of a helper method to denote the current distance to the target
     * data - but note that you must find the data first before you can
     * calculate this information). After you have found the data, you should
     * know the distance of each node on the path to the data. With that
     * information, you can determine how much farther away you can traverse
     * from the main path while remaining within distance of the target data.
     *
     * Use a HashSet as the Set you return. Keep in mind that since it is a
     * Set, you do not have to worry about any specific order in the Set.
     *
     * This must be implemented recursively.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *              50
     *            /    \
     *         25      75
     *        /  \     / \
     *      13   37  70  80
     *    /  \    \      \
     *   12  15    40    85
     *  /
     * 10
     * elementsWithinDistance(37, 3) should return the set {12, 13, 15, 25,
     * 37, 40, 50, 75}
     * elementsWithinDistance(85, 2) should return the set {75, 80, 85}
     * elementsWithinDistance(13, 1) should return the set {12, 13, 15, 25}
     *
     * @param data     the data to begin calculating distance from
     * @param distance the maximum distance allowed
     * @return the set of all data within a certain distance from the given data
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   is the data is not in the tree
     * @throws java.lang.IllegalArgumentException if distance is negative
     */
    public Set<T> elementsWithinDistance(T data, int distance) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        if (distance < 0) {
            throw new IllegalArgumentException("Input distance is negative.");
        }
        Set<T> set = new HashSet<>();
        distanceHelper(data, distance, root, set);
        return set;
    }

    /**
     * Helper method that calculates the current distance for every node.
     * @param data the data to be searched.
     * @param max the maximum distance.
     * @param curr the node that is traversed.
     * @param set the set to be added nodes.
     * @return an int representing the distance for a node.
     */
    private int distanceHelper(T data, int max, AVLNode<T> curr, Set<T> set) {
        if (curr == null) {
            throw new NoSuchElementException("Data is not in the tree.");
        }
        int childD = -1;
        if (data.compareTo(curr.getData()) > 0) {
            childD = distanceHelper(data, max, curr.getRight(), set);
        } else if (data.compareTo(curr.getData()) < 0) {
            childD = distanceHelper(data, max, curr.getLeft(), set);
        }
        int currD = childD + 1; //currD will be zero after found
        if (currD <= max) {
            set.add(curr.getData());
        }
        if (currD < max) {
            if (curr.getLeft() != null) {
                exactAdd(curr.getLeft(), max, currD + 1, set);
            }
            if (curr.getRight() != null) {
                exactAdd(curr.getRight(), max, currD + 1, set);
            }
        }
        return currD;
    }

    /**
     * Helper method that add the node in the set in subtree.
     * @param curr the node we are currently at.
     * @param maxD the maximum distance.
     * @param currD the current distance for a node.
     * @param set the set to be added nodes.
     */
    private void exactAdd(AVLNode<T> curr, int maxD, int currD, Set<T> set) {
        if (currD <= maxD) {
            set.add(curr.getData());
        }
        if (currD < maxD) {
            if (curr.getLeft() != null) {
                exactAdd(curr.getLeft(), maxD, currD + 1, set);
            }
            if (curr.getRight() != null) {
                exactAdd(curr.getRight(), maxD, currD + 1, set);
            }
        }
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
