package sheep.datastructure.intervaltree;

import sheep.datastructure.IntRangeMinimumQuery;

import java.util.Arrays;

/**
 * MaxValue Interval Tree
 *
 * @author sheep
 */
public class MaxValueIntegerIntervalTree extends IntegerIntervalTree
    implements IntRangeMinimumQuery {

    public MaxValueIntegerIntervalTree(int size) {
        super(size);
        Arrays.fill(values, Integer.MIN_VALUE);
        Arrays.fill(delta, Integer.MIN_VALUE);
    }

    @Override
    protected void pushDown(int x) {
        if (delta[x] != Integer.MIN_VALUE) {
            delta[x * 2] = Math.max(delta[x * 2], delta[x]);
            delta[x * 2 + 1] = Math.max(delta[x * 2 + 1], delta[x]);
            values[x * 2] = Math.max(values[x * 2], delta[x]);
            values[x * 2 + 1] = Math.max(values[x * 2 + 1], delta[x]);
            delta[x] = Integer.MIN_VALUE;
        }
    }

    @Override
    protected void pushUp(int x) {
        values[x] = Math.max(values[x * 2], values[x * 2 + 1]);
    }

    @Override
    protected int mergeValue(int x, int y) {
        return Math.max(x, y);
    }

    @Override
    protected int mergeDelta(int x, int y) {
        return Math.max(x, y);
    }

    public void update(int pos, int value) {
        super.updatePoint(pos, value);
    }

    public void updateInterval(int l, int r, int value) {
        super.updateInterval(l, r, value);
    }

    public int getMinimumValue(int l, int r) {
        return super.query(l, r);
    }

    @Override
    public int get(int left, int right) {
        return getMinimumValue(left, right);
    }
}
