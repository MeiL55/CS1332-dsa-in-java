import java.util.List;
import java.util.Comparator;
import java.util.Random;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Your implementation of various sorting algorithms.
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
public class Sorting {
    private static <T> void swap(T[] arr, int m, int n) { //why T m, T n does not work.
        T temp = arr[m];
        arr[m] = arr[n];
        arr[n] = temp;
    }
    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        int start = 0;
        int end = arr.length - 1;
        int mostRecentSwapIndex = 0;
        while (start < end) {
            for (int i = start; i < end; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    mostRecentSwapIndex = i;
                }
            }
            end = mostRecentSwapIndex; //the last one is already sorted
            for (int j = end; j > start; j--) {
                if (comparator.compare(arr[j], arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                    mostRecentSwapIndex = j;
                }
            }
            start = mostRecentSwapIndex;
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        if (arr.length == 0 || arr.length == 1) {
            return;
        }
        T[] left = (T[]) new Object[arr.length / 2];
        T[] right = (T[]) new Object[arr.length - arr.length / 2];
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length / 2) {
                left[i] = arr[i];
            } else {
                right[j] = arr[i];
                j++;
            }
        }
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        merge(left, right, arr, comparator);
    }
    /**
     * Helper method that helps with merging.
     * @param left the left sub-array.
     * @param right the right sub-array.
     * @param arr the original array.
     * @param comparator the comparator used to compare data.
     * @param <T> data type.
     */
    private static <T> void merge(T[] left, T[] right, T[] arr, Comparator<T> comparator) {
        int leftTracker = 0;
        int rightTracker = 0;
        for (int i = 0; i < arr.length; i++) {
            if (rightTracker >= right.length || (leftTracker < left.length
                    && comparator.compare(left[leftTracker], right[rightTracker]) <= 0)) {
                arr[i] = left[leftTracker++];
            } else {
                arr[i] = right[rightTracker++];
            }
        }
    }

    /**
     * Implement kth select.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * You may assume that the array doesn't contain any null elements.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param k          the index to retrieve data from + 1 (due to
     *                   0-indexing) if the array was sorted; the 'k' in "kth
     *                   select"; e.g. if k == 1, return the smallest element
     *                   in the array
     * @param arr        the array that should be modified after the method
     *                   is finished executing as needed
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @return the kth smallest element
     * @throws java.lang.IllegalArgumentException if the array or comparator
     *                                            or rand is null or k is not
     *                                            in the range of 1 to arr
     *                                            .length
     */
    public static <T> T kthSelect(int k, T[] arr, Comparator<T> comparator, Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Input is null");
        }
        if (k < 1 || k > arr.length) {
            throw new IllegalArgumentException("k is our of bound.");
        }
        return helper(k, arr, comparator, rand, 0, arr.length);
    }

    /**
     * Helper method that actually perform kth select.
     * @param k the index to retrieve data from.
     * @param arr the original array containing every data.
     * @param comparator the comparator used to compare data.
     * @param rand the random object used to generate random number.
     * @param left the index representing the start of sub-array.
     * @param right the index representing the end of sub-array.
     * @return the kth smallest element.
     * @param <T> data type.
     */
    private static <T> T helper(int k, T[] arr, Comparator<T> comparator, Random rand, int left, int right) {
        //right is exclusive
        int pivotIndex = rand.nextInt(right - left) + left;
        swap(arr, pivotIndex, left);
        int i = left + 1;
        int j = right - 1; //right is equal to the length
        while (i <= j) {
            while (i <= j && comparator.compare(arr[i], arr[left]) <= 0) {
                i++;
            }
            while (i <= j && comparator.compare(arr[j], arr[left]) >= 0) {
                j--;
            }
            if (i <= j) {
                swap(arr, i, j);
                i++;
                j--;
            }
        }
        swap(arr, left, j);
        if (j - left == k - 1) {
            return arr[j];
        } else if (j - left < k - 1) {
            return helper(k - (j - left) - 1, arr, comparator, rand, j + 1, right);
        } else {
            return helper(k, arr, comparator, rand, left, j);
        }
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        if (arr.length == 0) {
            return;
        }
        int base = 1;
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < 19; i++) {
            buckets[i] = new LinkedList<>();
        }
        int maxNum = Math.abs(arr[0]);
        int maxNumLength = 1;
        for (int i : arr) {
            if (i == Integer.MIN_VALUE) {
                maxNumLength = String.valueOf(Integer.MAX_VALUE).length();
                maxNum = Math.abs(i);
                break;
            } else if (Math.abs(i) > maxNum) {
                maxNum = Math.abs(i);
            }
        }
        while (maxNum >= 10 && maxNumLength != String.valueOf(Integer.MAX_VALUE).length()) {
            maxNumLength++;
            maxNum /= 10;
        }
        while (maxNumLength > 0) {
            int arrIndex = 0;
            for (int num : arr) {
                if (num == Integer.MIN_VALUE) {
                    buckets[0].add(num);
                } else {
                    buckets[num / base % 10 + 9].add(num);
                }
            }
            for (int j = 0; j < buckets.length; j++) {
                for (Integer item : buckets[j]) {
                    arr[arrIndex++] = item;
                }
                buckets[j].clear();
            }
            base *= 10;
            maxNumLength--;
        }
    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Input is null.");
        }
        int[] copy = new int[data.size()];
        PriorityQueue<Integer> hey = new PriorityQueue<>(data);
        for (int i = 0; i < data.size(); i++) {
            copy[i] = hey.remove();
        }
        return copy;
    }
}
