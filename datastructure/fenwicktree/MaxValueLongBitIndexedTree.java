package sheep.datastructure.fenwicktree;

import java.util.Arrays;

/**
 * Long type {@link MaxValueBitIndexedTree}
 */
public class MaxValueLongBitIndexedTree {

    private long arr[];
    private int size;

    public MaxValueLongBitIndexedTree(int size) {
        this(size, Long.MIN_VALUE);
    }

    public MaxValueLongBitIndexedTree(int size, long defaultValue) {
        this.size = size;
        arr = new long[size + 1];
        Arrays.fill(arr, defaultValue);
    }

    private int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, long y) {
        while (x <= size) {
            arr[x] = Math.max(arr[x], y);
            x += lowbit(x);
        }
    }

    public long getMax(int x) {
        long ret = 0;
        while (x > 0) {
            ret = Math.max(ret, arr[x]);
            x -= lowbit(x);
        }

        return ret;
    }
}
