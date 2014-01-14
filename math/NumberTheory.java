package sheep.math;

import sheep.util.ArrayUtils;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: sheep
 * Date: 14-1-10
 * Time: 下午9:24
 * To change this template use File | Settings | File Templates.
 */
public class NumberTheory {

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
        return null;
    }

    public static void fastFourierTransform(double[] a, double[] b, boolean invert) {

        if (a.length != b.length || Integer.highestOneBit(a.length) != a.length) {
            throw new IllegalArgumentException();
        }

        int count = a.length;
        for (int i = 1, j = 0; i < count; i++) {
            int bit = count >> 1;
            for (; j >= bit; bit >>= 1)
                j -= bit;
            j += bit;
            if (i < j) {
                ArrayUtils.swap(a, i, j);
                ArrayUtils.swap(b, i, j);
            }
        }

        for (int len = 2; len <= count; len <<= 1) {
            int halfLen = len >> 1;
            double angle = 2 * Math.PI / len;
            if (invert)
                angle = -angle;
            double wLenA = Math.cos(angle);
            double wLenB = Math.sin(angle);
            for (int i = 0; i < count; i += len) {
                double wA = 1;
                double wB = 0;
                for (int j = 0; j < halfLen; j++) {
                    double uA = a[i + j];
                    double uB = b[i + j];
                    double vA = a[i + j + halfLen] * wA - b[i + j + halfLen] * wB;
                    double vB = a[i + j + halfLen] * wB + b[i + j + halfLen] * wA;
                    a[i + j] = uA + vA;
                    b[i + j] = uB + vB;
                    a[i + j + halfLen] = uA - vA;
                    b[i + j + halfLen] = uB - vB;
                    double nextWA = wA * wLenA - wB * wLenB;
                    wB = wA * wLenB + wB * wLenA;
                    wA = nextWA;
                }
            }
        }
        if (invert) {
            for (int i = 0; i < count; i++) {
                a[i] /= count;
                b[i] /= count;
            }
        }
    }

    public static void fastFourierTransform(Complex[] numbers, boolean invert) {
        double[] a = new double[numbers.length];
        double[] b = new double[numbers.length];
        for (int i = 0; i < numbers.length; ++i) {
            a[i] = numbers[i].real();
            b[i] = numbers[i].imag();
        }

        fastFourierTransform(a, b, invert);
        for (int i = 0; i < numbers.length; ++i) {
            numbers[i] = new Complex(a[i], b[i]);
        }
    }

    public static long[] convolution(long[] a, long[] b) {
        int n = 1;
        while (n < (a.length + b.length - 1))
            n <<= 1;
        double[] aReal = new double[n];
        double[] aImaginary = new double[n];
        double[] bReal = new double[n];
        double[] bImaginary = new double[n];
        for (int i = 0; i < a.length; i++)
            aReal[i] = a[i];
        for (int i = 0; i < b.length; i++)
            bReal[i] = b[i];
        fastFourierTransform(aReal, aImaginary, false);
        if (a == b) {
            System.arraycopy(aReal, 0, bReal, 0, aReal.length);
            System.arraycopy(aImaginary, 0, bImaginary, 0, aImaginary.length);
        } else
            fastFourierTransform(bReal, bImaginary, false);
        for (int i = 0; i < n; i++) {
            double real = aReal[i] * bReal[i] - aImaginary[i] * bImaginary[i];
            aImaginary[i] = aImaginary[i] * bReal[i] + bImaginary[i] * aReal[i];
            aReal[i] = real;
        }
        fastFourierTransform(aReal, aImaginary, true);
        long[] result = new long[a.length + b.length - 1];
        for (int i = 0; i < a.length + b.length - 1; i++)
            result[i] = Math.round(aReal[i]);
        return result;
    }

    public static long[] convolution(int[] a, int[] b) {
        return convolution(ArrayUtils.toLongArray(a), ArrayUtils.toLongArray(b));
    }
}
