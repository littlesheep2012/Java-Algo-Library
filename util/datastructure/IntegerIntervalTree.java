package sheep.util.datastructure;

/**
 * Abstruct Integer Interval Tree
 *
 * @author sheep
 */
public abstract class IntegerIntervalTree {
    protected int[] values;
    protected int[] delta;
    protected int size;

    protected IntegerIntervalTree(int length) {
        this.size = length;
        values = new int[length * 4 + 100];
        delta = new int[length * 4 + 100];
    }

    protected abstract void pushDown(int x);
    protected abstract void pushUp(int x);
    protected abstract int mergeValue(int x, int y);
    protected abstract int mergeDelta(int x, int y);

    protected int mergeValueAtLeave(int x, int y) {
        return mergeValue(x, y);
    }

    protected int query(int left, int right, int l, int r, int x) {
        if (l <= left && r >= right) {
            return values[x];
        }
        int mid = (left + right) >> 1;
        int ret = Integer.MIN_VALUE;
        pushDown(x);
        if (l <= mid) ret = query(left, mid, l, r, x * 2);
        if (r > mid) ret = mergeValue(ret, query(mid + 1, right, l, r, x * 2 + 1));
        return ret;
    }

    protected int query(int left, int right) {
        return query(0, size - 1, left, right, 1);
    }

    protected void updatePoint(int left, int right, int pos, int x, int value) {
        if (left == right) {
            values[x] = mergeValueAtLeave(values[x], value);
            return;
        }
        int mid = (left + right) >> 1;
        pushDown(x);
        if (pos <= mid) {
            updatePoint(left, mid, pos, x * 2, value);
        } else {
            updatePoint(mid + 1, right, pos, x * 2 + 1, value);
        }
        pushUp(x);
    }

    protected void updatePoint(int pos, int value) {
        updatePoint(0, size - 1, pos, 1, value);
    }

    protected void updateInterval(int left, int right, int l, int r, int x, int value) {
        if (left == right) {
            delta[x] = mergeDelta(delta[x], value);
            values[x] = mergeValueAtLeave(values[x], value);
            return;
        }
        int mid = (left + right) >> 1;
        pushDown(x);
        if (l <= mid) {
            updateInterval(left, mid, l, r, x * 2, value);
        }
        if (r > mid) {
            updateInterval(mid + 1, right, l, r, x * 2 + 1, value);
        }
        pushUp(x);
    }

    protected void updateInterval(int l, int r, int value) {
        updateInterval(0, size - 1, l, r, 1, value);
    }

}
