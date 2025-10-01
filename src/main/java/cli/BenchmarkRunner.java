package cli;

import algorithms.InsertionSort;
import metrics.PerformanceTracker;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    public static void main(String[] args) {
        if (args.length == 0) {
            printUsage();
            return;
        }

        String command = args[0];

        switch (command) {
            case "benchmark":
                runBenchmark(args);
                break;
            case "test":
                runCorrectnessTest();
                break;
            case "compare":
                compareVersions(args);
                break;
            default:
                printUsage();
        }
    }

    private static void runBenchmark(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java BenchmarkRunner benchmark <size> [type]");
            System.out.println("Types: random, sorted, reverse, nearly-sorted");
            return;
        }

        int size = Integer.parseInt(args[1]);
        String type = args.length > 2 ? args[2] : "random";

        int[] array = generateArray(size, type);
        PerformanceTracker tracker = new PerformanceTracker();

        System.out.println("Running Insertion Sort benchmark...");
        System.out.println("Size: " + size + ", Type: " + type);

        InsertionSort.sort(array, tracker);

        if (!InsertionSort.isSorted(array)) {
            System.err.println("ERROR: Array not sorted correctly!");
            return;
        }

        System.out.println("✓ Sorting verified successfully");
        System.out.println(tracker);

        saveToCSV("insertion_sort_benchmark.csv", size, type, tracker);
    }

    private static void runCorrectnessTest() {
        System.out.println("Running correctness tests...");

        int[][] testArrays = {
                {},
                {1},
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {3, 1, 4, 1, 5, 9, 2, 6},
                {1, 2, 3, 5, 4}
        };

        for (int i = 0; i < testArrays.length; i++) {
            int[] array = testArrays[i].clone();
            PerformanceTracker tracker = new PerformanceTracker();

            InsertionSort.sort(array, tracker);

            boolean sorted = InsertionSort.isSorted(array);
            System.out.printf("Test %d: %s - %s%n",
                    i + 1, Arrays.toString(testArrays[i]),
                    sorted ? "✓ PASS" : "✗ FAIL");

            if (!sorted) {
                System.out.println("  Result: " + Arrays.toString(array));
            }
        }
    }

    private static void compareVersions(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java BenchmarkRunner compare <size>");
            return;
        }

        int size = Integer.parseInt(args[1]);
        int[] array1 = generateArray(size, "random");
        int[] array2 = array1.clone();

        PerformanceTracker standardTracker = new PerformanceTracker();
        PerformanceTracker optimizedTracker = new PerformanceTracker();

        InsertionSort.sort(array1, standardTracker);

        InsertionSort.sortOptimized(array2, optimizedTracker);

        System.out.println("=== COMPARISON RESULTS ===");
        System.out.println("Standard: " + standardTracker);
        System.out.println("Optimized: " + optimizedTracker);

        long timeImprovement = standardTracker.getElapsedTime() - optimizedTracker.getElapsedTime();
        double improvementPercent = (double) timeImprovement / standardTracker.getElapsedTime() * 100;

        System.out.printf("Time improvement: %d ns (%.2f%%)%n",
                timeImprovement, improvementPercent);
    }

    private static int[] generateArray(int size, String type) {
        int[] array = new int[size];
        Random random = new Random();

        switch (type) {
            case "sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                break;
            case "reverse":
                for (int i = 0; i < size; i++) array[i] = size - i;
                break;
            case "nearly-sorted":
                for (int i = 0; i < size; i++) array[i] = i;
                for (int i = 0; i < size / 10; i++) {
                    int idx1 = random.nextInt(size);
                    int idx2 = random.nextInt(size);
                    int temp = array[idx1];
                    array[idx1] = array[idx2];
                    array[idx2] = temp;
                }
                break;
            case "random":
            default:
                for (int i = 0; i < size; i++) array[i] = random.nextInt(size * 10);
                break;
        }

        return array;
    }

    private static void saveToCSV(String filename, int size, String type, PerformanceTracker tracker) {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write(String.format("%d,%s,%d,%d,%d,%d%n",
                    size, type, tracker.getComparisons(), tracker.getSwaps(),
                    tracker.getArrayAccesses(), tracker.getElapsedTime()));
            System.out.println("Results saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving to CSV: " + e.getMessage());
        }
    }

    private static void printUsage() {
        System.out.println("Insertion Sort Benchmark CLI");
        System.out.println("Usage:");
        System.out.println("  benchmark <size> [type]  - Run performance benchmark");
        System.out.println("  test                     - Run correctness tests");
        System.out.println("  compare <size>           - Compare standard vs optimized");
        System.out.println();
        System.out.println("Array types: random, sorted, reverse, nearly-sorted");
        System.out.println();
        System.out.println("Examples:");
        System.out.println("  java BenchmarkRunner benchmark 1000 random");
        System.out.println("  java BenchmarkRunner test");
        System.out.println("  java BenchmarkRunner compare 5000");
    }
}