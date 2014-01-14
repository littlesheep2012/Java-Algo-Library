package sheep.datastructure.sparsetable;

import sheep.datastructure.LongRangeMinimunQuery;

/**
 * Long {@link SparseTable}
 */
public class LongSparseTable implements LongRangeMinimunQuery {
    private int min[][];
    private int orig[];
    private int log[];
    private int size, maxPw;

    public LongSparseTable(int[] seq) {
        this.orig = seq;
        this.size = seq.length;
        construct();
    }

    void construct() {
        int power = 1;
        while ((1 << power) < size) {
            ++power;
        }
        maxPw = power + 1;
        min = new int[maxPw][size];
        log = new int[size + 1];
        log[0] = 0;
        for (int i = 1; i <= size; ++i) {
            log[i] = 0;
            while ((1 << (log[i] + 1)) < i) {
                ++log[i];
            }
        }

        for (int i = 0; i < size; ++i) {
            min[0][i] = orig[i];
        }

        for (int i = 1; i < maxPw; ++i) {
            for (int j = 0; j < size; ++j) {
                min[i][j] = min[i - 1][j];
                if (j + (1 << (i - 1)) < size) {
                    min[i][j] = Math.min(min[i][j], min[i - 1][j + (1 << (i - 1))]);
                }
            }
        }
    }

    @Override
    public long get(int left, int right) {
        if (right < left) {
            return Integer.MAX_VALUE;
        }
        int step = log[right - left + 1];
        return Math.min(min[step][left], min[step][right - (1 << step) + 1]);
    }
}
