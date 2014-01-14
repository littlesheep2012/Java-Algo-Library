package sheep.tree;

/**
 * Lowest Common Ancestor
 */
public class LowestCommonAncestor {

    private final int maxDepth;

    private Edge first[];

    private int father[][];
    private int depth[];
    private long length[];

    public LowestCommonAncestor(int n) {
        int log = 0;
        while ((1 << log) <= n) {
            ++log;
        }

        first = new Edge[n];
        maxDepth = log + 1;
    }

    public void insert(int u, int v, int w) {
        new Edge(u, v, w);
        new Edge(v, u, w);
    }

    public void construct(int root) {
        father = new int[first.length][maxDepth];
        depth = new int[first.length];
        length = new long[first.length];

        dfs(root, -1, 0, 0);
    }

    private void dfs(int u, int fa, int nowDepth, long sumLength) {

        length[u] = sumLength;
        depth[u] = nowDepth;

        father[u][0] = fa;
        for (int i = 1; i < maxDepth; ++i) {
            father[u][i] = -1;
            if (father[u][i - 1] != -1) {
                father[u][i] = father[father[u][i - 1]][i - 1];
            }
        }

        for (Edge e = first[u]; e != null; e = e.next) {
            if (e.to != fa) {
                dfs(e.to, u, nowDepth + 1, sumLength + e.w);
            }
        }
    }

    public int lca(int u, int v) {

        if (depth[u] > depth[v]) {
            int t = u;
            u = v;
            v = t;
        }

        for (int i = maxDepth - 1; i >= 0; --i) {
            if (father[v][i] != -1 && depth[father[v][i]] >= depth[u]) {
                v = father[v][i];
            }
        }

        if (u == v) {
            return u;
        }

        for (int i = maxDepth - 1; i >= 0; --i) {
            if (father[u][i] != father[v][i])  {
                u = father[u][i];
                v = father[v][i];
            }
        }

        return father[u][0];
    }

    public long dis(int u, int v) {
        return length[u] + length[v] - 2 * length[lca(u, v)];
    }

    public long directDis(int u, int v) {
        return Math.abs(length[u] - length[v]);
    }

    public long directDis(int u, int v, int l) {
        return length[u] + length[v] - 2 * length[l];
    }

    private class Edge {
        int to, w;
        Edge next;

        public Edge(int from, int to, int w) {
            this.to = to;
            this.w = w;
            this.next = first[from];
            first[from] = this;
        }
    }
}
