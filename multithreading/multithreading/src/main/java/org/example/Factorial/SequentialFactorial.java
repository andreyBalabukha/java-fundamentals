package org.example.Factorial;

import java.math.BigInteger;

// Sequential implementation of factorial
public class SequentialFactorial {

    public static BigInteger sequentialFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }
}
