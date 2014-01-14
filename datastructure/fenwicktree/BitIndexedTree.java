package sheep.datastructure.fenwicktree;

/**
 * Integer Fenwick Tree
 */

public class BitIndexedTree {
    private int arr[];
    private int size;

    public BitIndexedTree(int size) {
        this.size = size;
        arr = new int[size + 2];
    }

    private int lowbit(int x) {
        return x & -x;
    }

    public void update(int x, int y) {
        ++x;
        while (x < arr.length) {
            arr[x] += y;
            x += lowbit(x);
        }
    }

    public int getSum(int x) {
        ++x;
        int ret = 0;
        while (x > 0) {
            ret += arr[x];
            x -= lowbit(x);
        }

        return ret;
    }

    public int locate(int x) {
        int current = 0;
        for (int i = 20; i >= 0; --i) {
            if ((current + (1 << i)) < arr.length) {
                if (arr[current + (1 << i)] < x) {
                    x -= arr[current + (1 << i)];
                    current += (1 << i);
                }
            }
        }
        if (current >= arr.length) throw new RuntimeException();
        return current;
    }
}
