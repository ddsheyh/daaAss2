package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    @Test
    void testSortEmptyArray() {
        int[] array = {};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sort(array, tracker);

        assertTrue(InsertionSort.isSorted(array));
        assertEquals(0, tracker.getComparisons());
    }

    @Test
    void testSortSingleElement() {
        int[] array = {42};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sort(array, tracker);

        assertTrue(InsertionSort.isSorted(array));
        assertArrayEquals(new int[]{42}, array);
    }

    @Test
    void testSortAlreadySorted() {
        int[] array = {1, 2, 3, 4, 5};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sort(array, tracker);

        assertTrue(InsertionSort.isSorted(array));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortReverseSorted() {
        int[] array = {5, 4, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sort(array, tracker);

        assertTrue(InsertionSort.isSorted(array));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, array);
    }

    @Test
    void testSortWithDuplicates() {
        int[] array = {3, 1, 4, 1, 5, 9, 2, 6};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sort(array, tracker);

        assertTrue(InsertionSort.isSorted(array));
        assertArrayEquals(new int[]{1, 1, 2, 3, 4, 5, 6, 9}, array);
    }

    @Test
    void testSortRandomArray() {
        int[] array = {64, 34, 25, 12, 22, 11, 90};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sort(array, tracker);

        assertTrue(InsertionSort.isSorted(array));
        assertArrayEquals(new int[]{11, 12, 22, 25, 34, 64, 90}, array);
    }

    @Test
    void testOptimizedSortNearlySorted() {
        int[] array = {1, 2, 4, 3, 5, 6}; // nearly sorted
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sortOptimized(array, tracker);

        assertTrue(InsertionSort.isSorted(array));
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6}, array);
    }

    @Test
    void testSortWithGap() {
        int[] array = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sortWithGap(array, 3, tracker);

        assertArrayEquals(new int[]{3, 2, 1, 6, 5, 4, 9, 8, 7}, array);
    }

    @Test
    void testIsSortedMethod() {
        assertTrue(InsertionSort.isSorted(new int[]{1, 2, 3}));
        assertTrue(InsertionSort.isSorted(new int[]{1}));
        assertTrue(InsertionSort.isSorted(new int[]{}));
        assertFalse(InsertionSort.isSorted(new int[]{3, 2, 1}));
        assertFalse(InsertionSort.isSorted(new int[]{1, 3, 2}));
    }

    @Test
    void testPerformanceTracking() {
        int[] array = {5, 4, 3, 2, 1};
        PerformanceTracker tracker = new PerformanceTracker();

        InsertionSort.sort(array, tracker);

        assertTrue(tracker.getComparisons() > 0);
        assertTrue(tracker.getSwaps() > 0);
        assertTrue(tracker.getArrayAccesses() > 0);
        assertTrue(tracker.getElapsedTime() > 0);
    }
}