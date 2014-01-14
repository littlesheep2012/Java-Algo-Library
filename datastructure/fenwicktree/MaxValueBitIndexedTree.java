package sheep.datastructure.fenwicktree;

import java.util.Arrays;

/**
 * MaxValue Fenwick Tree
 */
public class MaxValueBitIndexedTree {

    private int arr[];
    private int size;

    public MaxValueBitIndexedTree(int size) {
        this(size, Integer.MIN_VALUE);
    }

    public MaxValueBitIndexedTree(int size, int defaultValue) {
        this.size = size;
        arr = new int[size + 1];
        Arrays.fill(arr, defaultValue);
    }

    private int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, int y) {
        while (x <= size) {
            arr[x] = Math.max(arr[x], y);
            x += lowbit(x);
        }
    }

    public int getMax(int x) {
        int ret = 0;
        while (x > 0) {
            ret = Math.max(ret, arr[x]);
            x -= lowbit(x);
        }

        return ret;
    }
}