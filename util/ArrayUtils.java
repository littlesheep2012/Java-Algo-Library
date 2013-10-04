package sheep.util;

import java.util.Arrays;

/**
 * Array Util
 *
 * @author sheep
 */
public class ArrayUtils {

    /** swap **/
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

    /** fill **/
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

    /** lower bound **/
    public static int lowerBound(int[] array, int value) {
        if (array.length > 0 && array[array.length - 1] < value) {
            return array.length;
        }

        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array[mid] >= value) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static long lowerBound(long[] array, long value) {
        if (array.length > 0 && array[array.length - 1] < value) {
            return array.length;
        }

        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array[mid] >= value) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static <T extends Comparable<T>> int lowerBound(T[] array, T value) {
        if (array.length > 0 && array[array.length - 1].compareTo(value) < 0) {
            return array.length;
        }

        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array[mid].compareTo(value) >= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    /** upper bound **/
    public static int upperBound(int[] array, int value) {
        if (array.length > 0 && array[array.length - 1] <= value) {
            return array.length;
        }

        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array[mid] > value) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static long upperBound(long[] array, long value) {
        if (array.length > 0 && array[array.length - 1] <= value) {
            return array.length;
        }

        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array[mid] > value) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    public static <T extends Comparable<T>> int upperBound(T[] array, T value) {
        if (array.length > 0 && array[array.length - 1].compareTo(value) <= 0) {
            return array.length;
        }

        int low = 0, high = array.length - 1;
        while (low < high) {
            int mid = (low + high) >> 1;
            if (array[mid].compareTo(value) > 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    /** max element **/
    public static int maxElement(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > array[index]) {
                index = i;
            }
        }

        return index;
    }

    public static int maxElement(long[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > array[index]) {
                index = i;
            }
        }

        return index;
    }

    public static int maxElement(double[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] > array[index]) {
                index = i;
            }
        }

        return index;
    }

    public static <T extends Comparable<T>> int maxElement(T[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i].compareTo(array[index]) > 0) {
                index = i;
            }
        }

        return index;
    }

    /** min element **/
    public static int minElement(int[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[index]) {
                index = i;
            }
        }

        return index;
    }

    public static int minElement(long[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[index]) {
                index = i;
            }
        }

        return index;
    }

    public static int minElement(double[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] < array[index]) {
                index = i;
            }
        }

        return index;
    }

    public static <T extends Comparable<T>> int minElement(T[] array) {
        int index = 0;
        for (int i = 1; i < array.length; ++i) {
            if (array[i].compareTo(array[index]) < 0) {
                index = i;
            }
        }

        return index;
    }

    /** reverse permutation **/
    public static int[] reversePermutation(int[] permutation) {
        int[] result = new int[permutation.length];
        for (int i = 0; i < permutation.length; i++)
            result[permutation[i]] = i;
        return result;
    }

    /** reverse permutation **/
    public static Integer[] reversePermutation(Integer[] permutation) {
        Integer[] result = new Integer[permutation.length];
        for (int i = 0; i < permutation.length; i++)
            result[permutation[i]] = i;
        return result;
    }
}
