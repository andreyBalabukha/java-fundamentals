package org.example.Factorial;

import java.math.BigInteger;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkFactorial extends RecursiveTask<BigInteger> {
    private final int n;

    public ForkFactorial(int n) {
        this.n = n;
    }

    @Override
    protected BigInteger compute() {
        if (n <= 1) {
            return BigInteger.ONE;
        }
        // Split the task into two subtasks
        ForkFactorial subTask1 = new ForkFactorial(n - 1);
        subTask1.fork(); // Asynchronously execute subTask1
        BigInteger subTask2Result = new ForkFactorial(n - 2).fork().join(); // Compute subTask2 and wait for its result
        return BigInteger.valueOf(n).multiply(subTask1.join()).multiply(subTask2Result);
    }

    // Method to use Fork/Join to calculate factorial
    public static BigInteger parallelFactorial(int n) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ForkFactorial(n));
    }
}
