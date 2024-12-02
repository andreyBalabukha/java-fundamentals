package org.example;

import org.example.Factorial.ForkFactorial;
import org.example.Factorial.SequentialFactorial;

import java.math.BigInteger;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int number = 20; // Change this value for larger computations

        // Calculate factorial using sequential implementation
        long startTime = System.nanoTime();
        BigInteger sequentialResult = SequentialFactorial.sequentialFactorial(number);
        long sequentialTime = System.nanoTime() - startTime;

        // Calculate factorial using Fork implementation
        startTime = System.nanoTime();
        BigInteger parallelResult = ForkFactorial.parallelFactorial(number);
        long parallelTime = System.nanoTime() - startTime;

        // Output results
        System.out.println("Sequential Result: " + sequentialResult);
        System.out.println("Parallel Result: " + parallelResult);
        System.out.println("Sequential Time (ns): " + sequentialTime);
        System.out.println("Parallel Time (ns): " + parallelTime);
    }
}
