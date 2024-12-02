package org.example.Factorial;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelFactorial extends RecursiveTask<BigInteger> {
    private final int n;

    public ParallelFactorial(int n) {
        this.n = n;
    }

    @Override
    protected BigInteger compute() {
        if (n <= 1) {
            return BigInteger.ONE;
        }
        // Split the task into two subtasks
        ParallelFactorial subTask1 = new ParallelFactorial(n - 1);
        subTask1.fork(); // Asynchronously execute subTask1
        BigInteger subTask2Result = new ParallelFactorial(n - 2).fork().join(); // Compute subTask2 and wait for its result
        return BigInteger.valueOf(n).multiply(subTask1.join()).multiply(subTask2Result);
    }

    // Method to use Fork/Join to calculate factorial
    public static BigInteger parallelFactorial(int n) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ParallelFactorial(n));
    }
}
