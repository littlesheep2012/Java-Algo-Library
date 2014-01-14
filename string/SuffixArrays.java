package sheep.string;

/**
 * Suffix Arrays (O(n log n) implementation)
 */

public class SuffixArrays {

    private static boolean cmp(int[] r, int a, int b, int l, int n) {
        int la = r[a], lb = r[b], ra, rb;
        ra = a + l < n ? r[a + l] : -1;
        rb = b + l < n ? r[b + l] : -1;
        return la == lb && ra == rb;
    }

    public static int[] makeSA(int s[]) {
        int n = s.length;
        int alphaSize = 1;
        for (int i = 0; i < n; ++i) {
            alphaSize = Math.max(alphaSize, s[i] + 1);
        }

        int[] wa = new int[Math.max(n, alphaSize) + 10];
        int[] wb = new int[Math.max(n, alphaSize) + 10];
        int[] wc = new int[Math.max(n, alphaSize) + 10];
        int[] wd = new int[Math.max(n, alphaSize) + 10];
        int[] sa = new int[n];

        int[] x = wa, y = wb;
        for (int i = 0; i < n; ++i)
            ++wc[x[i] = s[i]];
        for (int i = 1; i < alphaSize; ++i)
            wc[i] += wc[i - 1];
        for (int i = n - 1; i >= 0; --i)
            sa[--wc[x[i]]] = i;
        for (int tot = 0, p = 1; tot + 1 < n; p <<= 1, alphaSize = tot + 1) {
            tot = 0;
            for (int i = n - p; i < n; ++i)
                y[tot++] = i;
            for (int i = 0; i < n; ++i) {
                if (sa[i] >= p) {
                    y[tot++] = sa[i] - p;
                }
            }
            for (int i = 0; i < n; ++i)
                wd[i] = x[y[i]];
            for (int i = 0; i < alphaSize; ++i)
                wc[i] = 0;
            for (int i = 0; i < n; ++i)
                ++wc[wd[i]];
            for (int i = 1; i < alphaSize; ++i)
                wc[i] += wc[i - 1];
            for (int i = n - 1; i >= 0; --i)
                sa[--wc[wd[i]]] = y[i];
            int[] t = x; x = y; y = t;
            x[sa[0]] = tot = 0;
            for (int i = 1; i < n; ++i)
                x[sa[i]] = cmp(y, sa[i - 1], sa[i], p, n) ? tot : ++tot;
        }

        return sa;
    }

    public static int[] makeRank(int sa[]) {
        int rank[] = new int[sa.length];
        for (int i = 0; i < sa.length; ++i) {
            rank[sa[i]] = i;
        }

        return rank;
    }

    public static int[] makeHeight(int s[], int sa[], int rank[]) {
        int n = s.length;
        int height[] = new int[n];
        for (int i = 0; i < n; ++i) {

            if (0 == rank[i]) {
                continue;
            }

            if (i > 0) {
                height[rank[i]] = Math.max(0, height[rank[i - 1]] - 1);
            }

            for (; s[i + height[rank[i]]] == s[sa[rank[i] - 1] + height[rank[i]]]; ++ height[rank[i]]);
        }

        return height;
    }
}

