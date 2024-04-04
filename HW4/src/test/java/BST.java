import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?//for each loop
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Input is null or any data in input is null.");
        }
        for (T item : data) {
            if (item == null) {
                throw new IllegalArgumentException("Input is null or any data in input is null.");
            }
            add(item);
        }
    }

    /**
     * Adds the data to the tree.
     *
     * This must be done recursively.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null");
        }
        root = addHelper(data, root);
    }

    /**
     * Helper method that traverse and add node to BST.
     * @param data the data to add in the BST.
     * @param curr root of tree/subtree being traversed.
     * @return the node to be added.
     */
    private BSTNode<T> addHelper(T data, BSTNode<T> curr) {
        if (curr == null) {
            size++;
            return new BSTNode<>(data);
        }
        if (curr.getData().compareTo(data) < 0) {
            curr.setRight(addHelper(data, curr.getRight()));
        } else if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(addHelper(data, curr.getLeft()));
        }
        return curr;
    }

    /**
     * Removes and returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        BSTNode<T> removed = new BSTNode<>(null);
        root = removeHelper(data, root, removed);
        return removed.getData();
    }

    /**
     * Helper method that reinforces pointers and change the removed dummy node.
     * @param data the data to be found and searched.
     * @param curr the root of tree/subtree.
     * @param removed the node to be removed.
     * @return the node that should be connected by its parent.
     */
    private BSTNode<T> removeHelper(T data, BSTNode<T> curr, BSTNode<T> removed) {
        if (curr == null) {
            throw new NoSuchElementException("Input data is not found.");
        }
        if (curr.getData().compareTo(data) > 0) {
            curr.setLeft(removeHelper(data, curr.getLeft(), removed));
        } else if (curr.getData().compareTo(data) < 0) {
            curr.setRight(removeHelper(data, curr.getRight(), removed));
        } else {
            size--;
            removed.setData(curr.getData());
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() == null || curr.getRight() == null) {
                if (curr.getLeft() == null) {
                    return curr.getRight();
                }
                if (curr.getRight() == null) {
                    return curr.getLeft();
                }
            } else {
                BSTNode<T> removed2 = new BSTNode<>(null);
                curr.setLeft(predecessorHelper(curr.getLeft(), removed2));
                curr.setData(removed2.getData());
            }
        }
        return curr;
    }

    /**
     * Helper method that removes predecessor of a node.
     * @param curr the left child of the node to be removed.
     * @param removed2 dummy node whose value to be changed.
     * @return a node that the node to be removed should be linked to.
     */
    private BSTNode<T> predecessorHelper(BSTNode<T> curr, BSTNode<T> removed2) {
        if (curr.getRight() == null) {
            removed2.setData(curr.getData());
            return curr.getLeft();
        }
        curr.setRight(predecessorHelper(curr.getRight(), removed2));
        return curr;
    }

    /**
     * Returns the data from the tree matching the given parameter.
     *
     * This must be done recursively.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        return getHelper(data, root);
    }

    /**
     * Helper method that returns the data of the node to be searched.
     * @param data data to be searched.
     * @param curr root of tree/subtree to be traversed.
     * @return data of the node to be searched.
     */
    private T getHelper(T data, BSTNode<T> curr) {
        if (curr == null) {
            throw new NoSuchElementException("Data not found in the tree.");
        }
        if (curr.getData().compareTo(data) > 0) {
            return getHelper(data, curr.getLeft());
        } else if (curr.getData().compareTo(data) < 0) {
            return getHelper(data, curr.getRight());
        }
        return curr.getData();
    }


    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * This must be done recursively.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data the data to search for
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data is null.");
        }
        return containsHelper(data, root);
    }

    /**
     * Helper method that returns whether the tree contains the data.
     * @param data data to be searched in the tree.
     * @param curr root of the tree.
     * @return whether the tree contains the data.
     */

    private boolean containsHelper(T data, BSTNode<T> curr) {
        if (curr == null) {
            throw new NoSuchElementException("Data not found in the tree.");
        }
        return getHelper(data, curr) != null;
    }


    /**
     * Generate a pre-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> list = new ArrayList<>();
        preorderHelper(list, root);
        return list;
    }

    /**
     * Helper method that creates a list of preorder traversal.
     * @param list a lost of preorder traversal.
     * @param curr root of tree/subtree to be traversed.
     */
    private void preorderHelper(List<T> list, BSTNode<T> curr) {
        if (curr == null) {
            return;
        }
        list.add(curr.getData());
        preorderHelper(list, curr.getLeft());
        preorderHelper(list, curr.getRight());
    }

    /**
     * Generate an in-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> list = new ArrayList<>();
        inorderHelper(list, root);
        return list;
    }

    /**
     * Helper method that creates a list of inorder traversal.
     * @param list a list of inorder traversal.
     * @param curr root of tree/subtree to be traversed.
     */
    private void inorderHelper(List<T> list, BSTNode<T> curr) {
        if (curr == null) {
            return;
        }
        inorderHelper(list, curr.getLeft());
        list.add(curr.getData());
        inorderHelper(list, curr.getRight());
    }
    /**
     * Generate a post-order traversal of the tree.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> list = new ArrayList<>();
        postorderHelper(list, root);
        return list;
    }

    /**
     * Helper method that creates a list of postorder traversal.
     * @param list a list of postorder traversal.
     * @param curr root of tree/subtree to be traversed.
     */
    private void postorderHelper(List<T> list, BSTNode<T> curr) {
        if (curr == null) {
            return;
        }
        postorderHelper(list, curr.getLeft());
        postorderHelper(list, curr.getRight());
        list.add(curr.getData());
    }

    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        List<T> list = new ArrayList<>();
        Queue<BSTNode<T>> q = new LinkedList<>();
        if (root != null) {
            q.add(root);
        }
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.poll();
            if (curr.getLeft() != null) {
                q.add(curr.getLeft());
            }
            if (curr.getRight() != null) {

                q.add(curr.getRight());
            }
            list.add(curr.getData());
        }
        return list;
    }

    /**
     * Returns the height of the root of the tree.
     *
     * This must be done recursively.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child has a height of -1.
     *
     * Must be O(n).
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }

    /**
     * Helper method that calculates the height of a BST.
     * @param curr root node for a tree.
     * @return the height of the tree.
     */
    private int heightHelper(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }
        return Math.max(heightHelper(curr.getLeft()), heightHelper(curr.getRight())) + 1;
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Generates a list of the max data per level from the top to the bottom
     * of the tree. (Another way to think about this is to get the right most
     * data per level from top to bottom.)
     *
     * This must be done recursively.
     *
     * This list should contain the last node of each level.
     *
     * If the tree is empty, an empty list should be returned.
     *
     * Ex:
     * Given the following BST composed of Integers
     *      2
     *    /   \
     *   1     4
     *  /     / \
     * 0     3   5
     * getMaxDataPerLevel() should return the list [2, 4, 5] - 2 is the max
     * data of level 0, 4 is the max data of level 1, and 5 is the max data of
     * level 2
     *
     * Ex:
     * Given the following BST composed of Integers
     *               50
     *           /        \
     *         25         75
     *       /    \
     *      12    37
     *     /  \    \
     *   11   15   40
     *  /
     * 10
     * getMaxDataPerLevel() should return the list [50, 75, 37, 40, 10] - 50 is
     * the max data of level 0, 75 is the max data of level 1, 37 is the
     * max data of level 2, etc.
     *
     * Must be O(n).
     *
     * @return the list containing the max data of each level
     */
    public List<T> getMaxDataPerLevel() {
        List<T> list = new ArrayList<>();
        getMaxDataPerLevelHelper(root, 0, list);
        return list;
    }

    /**
     * Helper method that creates a list of largest nodes at each level.
     * @param curr root of the tree.
     * @param level current level.
     * @param list the list of largest nodes at each level.
     */



    private void getMaxDataPerLevelHelper(BSTNode<T> curr, int level, List<T> list) {
        if (curr == null) {
            return;
        }
        if (level == list.size()) {
            list.add(curr.getData());
        } else {
            list.set(level, curr.getData());
        }
        getMaxDataPerLevelHelper(curr.getLeft(), level + 1, list);
        getMaxDataPerLevelHelper(curr.getRight(), level + 1, list);
    }


    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
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
