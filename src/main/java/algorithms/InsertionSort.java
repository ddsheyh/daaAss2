package algorithms;

import metrics.PerformanceTracker;

public class InsertionSort {
    public static void sort(int[] array, PerformanceTracker tracker) {
        if (array == null || array.length <= 1) {
            return;
        }

        tracker.startTimer();

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            tracker.incrementArrayAccesses(2);

            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                tracker.incrementComparisons();

                array[j + 1] = array[j];
                tracker.incrementArrayAccesses(2);
                tracker.incrementSwaps();

                j--;
            }
            tracker.incrementComparisons();

            array[j + 1] = key;
            tracker.incrementArrayAccesses(2);
        }

        tracker.stopTimer();
    }

    public static void sortOptimized(int[] array, PerformanceTracker tracker) {
        if (array == null || array.length <= 1) {
            return;
        }

        tracker.startTimer();

        boolean sorted = true;

        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            tracker.incrementArrayAccesses(2);

            if (array[i - 1] <= key) {
                tracker.incrementComparisons();
                continue;
            }

            sorted = false;
            int j = i - 1;

            int pos = binarySearchPosition(array, key, 0, j, tracker);

            while (j >= pos) {
                array[j + 1] = array[j];
                tracker.incrementArrayAccesses(2);
                tracker.incrementSwaps();
                j--;
            }

            array[pos] = key;
            tracker.incrementArrayAccesses(2);
        }

        if (sorted) {
            System.out.println("Array was already sorted - early termination");
        }

        tracker.stopTimer();
    }

    private static int binarySearchPosition(int[] array, int key, int left, int right, PerformanceTracker tracker) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            tracker.incrementComparisons();

            if (array[mid] == key) {
                return mid + 1;
            } else if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void sortWithGap(int[] array, int gap, PerformanceTracker tracker) {
        if (array == null || array.length <= 1) {
            return;
        }

        for (int i = gap; i < array.length; i++) {
            int temp = array[i];
            tracker.incrementArrayAccesses(2);

            int j;
            for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                tracker.incrementComparisons();
                array[j] = array[j - gap];
                tracker.incrementArrayAccesses(2);
                tracker.incrementSwaps();
            }
            tracker.incrementComparisons();

            array[j] = temp;
            tracker.incrementArrayAccesses(2);
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
}