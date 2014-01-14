package sheep.graph.flow;

import java.util.Arrays;

/**
 * MaxFlow Solver (Naive SAP implementation)
 *
 * TODO: update to use Dinic algorithm
 */
public class MaxFlow
{
    public class Edge
    {
        public int u,v,c;
        public Edge next,r;

        public Edge(int u,int v,int c)
        {
            this.u = u;
            this.v = v;
            this.c = c;
            next = r = null;
        }
    }

    public Edge vertex[];
    public int height[], nheight[];
    public int s, t, n;

    public void init(int n,int s,int t)
    {
        height = new int[n+1];
        nheight = new int[n+1];
        vertex = new Edge[n+1];
        this.s = s;
        this.t = t;
        this.n = n;
    }

    public MaxFlow(int n,int s,int t)
    {
        init(n,s,t);
    }

    public void insert(int u,int v,int c)
    {
        Edge e = new Edge(u,v,c);
        Edge r = new Edge(v,u,0);
        e.r = r;
        r.r = e;
        e.next = vertex[u];
        vertex[u] = e;
        r.next = vertex[v];
        vertex[v] = r;
    }

    public int augPath(int u,int push)
    {
        if (u == t) return push;
        int flow = push, minheight = n-1;
        for (Edge e = vertex[u]; e != null; e = e.next)
            if (e.c != 0)
            {
                if (height[e.v] + 1 == height[e.u])
                {
                    int delta = flow < e.c ? flow : e.c;
                    if (delta > 0)
                        delta = augPath(e.v,delta);
                    e.c -= delta;
                    e.r.c += delta;
                    flow -= delta;
                    if (0 == flow || height[s] >= n) return push - flow;
                }
                minheight = Math.min(minheight, height[e.v]);
            }
        if (push == flow)
        {
            --nheight[height[u]];
            if (0 == nheight[height[u]]) height[s] = n;
            height[u] = ++minheight;
            ++nheight[height[u]];
        }
        return push - flow;
    }

    public int maxFlow()
    {
        Arrays.fill(height, 0);
        Arrays.fill(nheight, 0);
        nheight[0] = n;
        int flow = 0;
        while (height[s] < n)
            flow += augPath(s,Integer.MAX_VALUE);
        return flow;
    }
}