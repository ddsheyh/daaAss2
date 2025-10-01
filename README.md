# Insertion Sort Algorithm Implementation

## Project Overview

This project implements an optimized Insertion Sort algorithm with performance tracking and benchmarking capabilities. Includes both standard and optimized versions for nearly-sorted data.

## Algorithm Specifications

**Time Complexity:**
- Best Case: O(n) - already sorted arrays
- Average Case: O(n²) - random data
- Worst Case: O(n²) - reverse sorted arrays

**Space Complexity:** O(1) - in-place sorting
**Stability:** Stable algorithm

## Key Optimizations

1. Early termination for sorted arrays
2. Binary search insertion for nearly-sorted data
3. Gap sequence support for Shell Sort preparation
4. Comprehensive performance metrics tracking

## Project Structure

**src/main/java/com/dcalgorithms/**

├── algorithms/InsertionSort.java

├── metrics/PerformanceTracker.java

├── metrics/ComplexityMetrics.java

└── cli/BenchmarkRunner.java

**src/test/java/com/dcalgorithms/**

└── algorithms/InsertionSortTest.java

## Quick Start

**Prerequisites:** Java 11+, Maven 3.6+

**Build and Test:**
```bash
mvn compile
mvn test
mvn package
```
**Run Benchmarks:**
```bash
java -cp target/classes com.dcalgorithms.cli.BenchmarkRunner benchmark 1000 random
java -cp target/classes com.dcalgorithms.cli.BenchmarkRunner test
java -cp target/classes com.dcalgorithms.cli.BenchmarkRunner compare 5000
```

## Performance Metrics
- Comparisons count
- Swaps count
- Array access operations
- Execution time (nanoseconds)
- Memory usage

- Empty arrays and single elements
- Sorted and reverse sorted arrays
- Arrays with duplicate elements
- Nearly sorted arrays
- Random data distributions
- Performance tracking validation
- Edge case handling

## Usage Examples
```markdown
**Basic Sorting:**

    int[] array = {5, 2, 8, 1, 9};
    PerformanceTracker tracker = new PerformanceTracker();
    InsertionSort.sort(array, tracker);

**Optimized Version:**

    InsertionSort.sortOptimized(array, tracker);

**Gap-Based Sorting:**

    InsertionSort.sortWithGap(array, 3, tracker);
```

## Features
- Standard Insertion Sort implementation
- Optimized version for nearly-sorted data
- Gap sequence support for Shell Sort
- Detailed performance metrics collection
- Comprehensive test suite
- Command-line benchmarking interface
- CSV results export for analysis
- Memory-efficient in-place operations

## Output Format
**Benchmark results are saved in CSV format:**
```text
size,type,comparisons,swaps,array_accesses,time_ns
1000,random,250000,125000,500000,1500000
1000,sorted,999,0,1998,50000
1000,reverse,500000,250000,1000000,3000000
```

## Algorithm Details
Insertion Sort works by iterating through the array and inserting each element into its correct position in the sorted portion. The algorithm maintains a sorted subarray and expands it one element at a time.

**Optimization Strategies:**
- Early termination when array is already sorted
- Binary search for faster insertion in nearly-sorted data
- Minimal memory usage with O(1) auxiliary space