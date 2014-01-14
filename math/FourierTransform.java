package sheep.math;

import sheep.util.ArrayUtils;

/**
 * Fourier Transform related methods
 */
public class FourierTransform {

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
