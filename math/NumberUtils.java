package sheep.math;

import java.util.Arrays;

/**
 * Number (Theory) related methods
 */
public class NumberUtils {

    public static long[] extendedGcd(long x, long y){
        long a0 = 1, a1 = 0, b0 = 0, b1 = 1, t;
        while (y != 0) {
            t = a0 - x / y * a1; a0 = a1; a1 = t;
            t = b0 - x / y * b1; b0 = b1; b1 = t;
            t = x % y; x = y; y = t;
        }
        if (x < 0) {
            a0 = -a0; b0 = -b0; x = -x;
        }
        return new long[] {a0, b0, x};
    }

    public static long solveModularEquations(long b[], long w[]) {
        if (b.length != w.length) {
            throw new IllegalArgumentException();

        }

        long a = 0, m, n = 1;
        for (int i = 0; i < b.length; i++) {
            n *= w[i];
        }

        for (int i = 0; i < b.length; i++) {
            m = n / w[i];
            a = (a + extendedGcd(w[i], m)[1] * m % n * b[i]) % n;
        }

        return a < 0 ? a + n : a;
    }

    public static long powMod(long x, long y, long mod) {
        long res = 1;
        for (; y != 0; y >>= 1L) {
            if (y % 2 == 1) res = res * x % mod;
            x = x * x % mod;
        }
        return res;
    }

    public static long[] generateFactorial(int count, long module) {
        long[] result = new long[count];
        if (module == -1) {
            if (count != 0)
                result[0] = 1;
            for (int i = 1; i < count; i++)
                result[i] = result[i - 1] * i;
        } else {
            if (count != 0)
                result[0] = 1 % module;
            for (int i = 1; i < count; i++)
                result[i] = (result[i - 1] * i) % module;
        }
        return result;
    }

    public static long[] generateReverse(int upTo, long module) {
        long[] result = new long[upTo];
        if (upTo > 1)
            result[1] = 1;
        for (int i = 2; i < upTo; i++)
            result[i] = (module - module / i * result[((int) (module % i))] % module) % module;
        return result;
    }

    public static long[] generateReverseFactorials(int upTo, long module) {
        long[] result = generateReverse(upTo, module);
        if (upTo > 0)
            result[0] = 1;
        for (int i = 1; i < upTo; i++)
            result[i] = result[i] * result[i - 1] % module;
        return result;
    }

    public static int[] generatePrimes(int upTo) {
        boolean[] isPrime = new boolean[upTo + 1];
        int[] primes = new int[upTo + 1];

        int count = 0;
        Arrays.fill(isPrime, true);
        for (int i = 2; i <= upTo; ++i) {
            if (isPrime[i]) {
                ++count;
                for (int j = 0; j < count && i * primes[j] <= upTo; ++j) {
                    isPrime[i * primes[j]] = false;
                    if (i % primes[j] == 0) {
                        break;
                    }
                }
            }
        }

        int[] result = new int[count];
        System.arraycopy(primes, 0, result, 0, count);

        return result;
    }
}
