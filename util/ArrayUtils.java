package sheep.util;

import java.util.Arrays;

/**
 * Array Utilities
 */
public class ArrayUtils {

    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void swap(boolean[] array, int i, int j) {
        boolean temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /** reverse **/
    public static void reverse(char[] array) {
        for (int i = 0; i < array.length - 1 - i; ++i) {
            swap(array, i, array.length - 1 - i);
        }
    }

    public static <T> void reverse(T[] array) {
        for (int i = 0; i < array.length - 1 - i; ++i) {
            swap(array, i, array.length - 1 - i);
        }
    }

    public static void reverse(int[] array) {
        for (int i = 0; i < array.length - 1 - i; ++i) {
            swap(array, i, array.length - 1 - i);
        }
    }

    public static void reverse(long[] array) {
        for (int i = 0; i < array.length - 1 - i; ++i) {
            swap(array, i, array.length - 1 - i);
        }
    }

    public static void reverse(double[] array) {
        for (int i = 0; i < array.length - 1 - i; ++i) {
            swap(array, i, array.length - 1 - i);
        }
    }

    public static void reverse(boolean[] array) {
        for (int i = 0; i < array.length - 1 - i; ++i) {
            swap(array, i, array.length - 1 - i);
        }
    }

    public static <T> void fill(T[][] array, T value) {
        for (int i = 0; i < array.length; ++i) {
            Arrays.fill(array[i], value);
        }
    }

    public static <T> void fill(T[][][] array, T value) {
        for (int i = 0; i < array.length; ++i) {
            fill(array[i], value);
        }
    }

    public static void fill(int[][] array, int value) {
        for (int i = 0; i < array.length; ++i) {
            Arrays.fill(array[i], value);
        }
    }

    public static void fill(int[][][] array, int value) {
        for (int i = 0; i < array.length; ++i) {
            fill(array[i], value);
        }
    }
    public static void fill(long[][] array, long value) {
        for (int i = 0; i < array.length; ++i) {
            Arrays.fill(array[i], value);
        }
    }

    public static void fill(long[][][] array, long value) {
        for (int i = 0; i < array.length; ++i) {
            fill(array[i], value);
        }
    }
    public static void fill(double[][] array, double value) {
        for (int i = 0; i < array.length; ++i) {
            Arrays.fill(array[i], value);
        }
    }

    public static void fill(double[][][] array, double value) {
        for (int i = 0; i < array.length; ++i) {
            fill(array[i], value);
        }
    }
    public static void fill(boolean[][] array, boolean value) {
        for (int i = 0; i < array.length; ++i) {
            Arrays.fill(array[i], value);
        }
    }

    public static void fill(boolean[][][] array, boolean value) {
        for (int i = 0; i < array.length; ++i) {
            fill(array[i], value);
        }
    }

    public static int[] reversePermutation(int[] permutation) {
        int[] result = new int[permutation.length];
        for (int i = 0; i < permutation.length; i++)
            result[permutation[i]] = i;
        return result;
    }

    public static Integer[] reversePermutation(Integer[] permutation) {
        Integer[] result = new Integer[permutation.length];
        for (int i = 0; i < permutation.length; i++)
            result[permutation[i]] = i;
        return result;
    }

    public static long[] toLongArray(int[] arr) {
        long[] result = new long[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            result[i] = arr[i];
        }
        return result;
    }

    public static int[] toIntArray(long[] arr) {
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] > Integer.MAX_VALUE) {
                throw new NumberFormatException();
            }
            result[i] = (int) arr[i];
        }

        return result;
    }
}
