package org.example;

import org.example.Factorial.ParallelFactorial;
import org.example.Factorial.SequentialFactorial;
import org.example.Sort.ParallelMergeSort;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        int number = 10;

        // Calculate factorial using sequential implementation
        long startTime = System.nanoTime();
        BigInteger sequentialResult = SequentialFactorial.sequentialFactorial(number);
        long sequentialTime = System.nanoTime() - startTime;

        // Calculate factorial using Fork implementation
        startTime = System.nanoTime();
        BigInteger parallelResult = ParallelFactorial.parallelFactorial(number);
        long parallelTime = System.nanoTime() - startTime;

        // Output results
        System.out.println("Sequential Result: " + sequentialResult);
        System.out.println("Parallel Result: " + parallelResult);
        System.out.println("Sequential Time (ns): " + sequentialTime);
        System.out.println("Parallel Time (ns): " + parallelTime);


        ////////////////////////////////////////////////


        // Example: Create a large array of random integers
        int size = 1_000_000; // Adjust size for testing
        int[] array = new int[size];

        // Fill the array with random numbers
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 100_000);
        }

        // Sort the array using parallel merge sort
        long startTimeSort = System.nanoTime();
        ParallelMergeSort.parallelMergeSort(array);
        long finishTimeSort = System.nanoTime() - startTimeSort;

        // Output the elapsed time
        System.out.println("Sorting completed in " + (finishTimeSort / 1_000_000) + " ms");
    }
}