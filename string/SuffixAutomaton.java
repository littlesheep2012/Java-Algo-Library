package sheep.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Suffix Automaton
 */
public class SuffixAutomaton {

    public final int ALPHA;
    public int totalNodes;

    public class SuffixAutomatonNode{
        public SuffixAutomatonNode suffixNode;
        public SuffixAutomatonNode ch[];

        public int tag, sum;
        public int deg;
        public int maximumLength;
        public int positions;
        public int id;

        SuffixAutomatonNode() {
            suffixNode = null;
            ch = new SuffixAutomatonNode[ALPHA];
            maximumLength = 0;
            positions = 0;
            tag = 0;
            deg = 0;
            id = list.size();

            list.add(this);
        }
    }

    public SuffixAutomatonNode root;
    public SuffixAutomatonNode lastNode;

    public ArrayList<SuffixAutomatonNode> list;

    public SuffixAutomaton(int ALPHA) {
        this.ALPHA = ALPHA;
        totalNodes = 0;
        list = new ArrayList<SuffixAutomatonNode>();
        root = new SuffixAutomatonNode();
        lastNode = root;
    }

    public void append(char[] s) {
        for (int i = 0; i < s.length; ++i) {
            this.extend(s[i] - 'a');
        }
    }

    public SuffixAutomatonNode extend(int c, SuffixAutomatonNode rear) {
        SuffixAutomatonNode p = rear;
        SuffixAutomatonNode np = new SuffixAutomatonNode();
        np.maximumLength = p.maximumLength + 1;
        np.positions = 1;
        while (null != p && null == p.ch[c]) {
            p.ch[c] = np;
            p = p.suffixNode;
        }

        if (p == null) {
            np.suffixNode = root;
        } else {
            SuffixAutomatonNode q = p.ch[c];
            if (q.maximumLength == p.maximumLength + 1) {
                np.suffixNode = q;
            } else {
                SuffixAutomatonNode nq = new SuffixAutomatonNode();
                nq.ch = q.ch.clone();
                nq.maximumLength = p.maximumLength + 1;
                nq.suffixNode = q.suffixNode;
                q.suffixNode = nq;
                np.suffixNode = nq;

                while (null != p && p.ch[c] == q) {
                    p.ch[c] = nq;
                    p = p.suffixNode;
                }
            }
        }

        lastNode = np;
        return np;
    }

    public SuffixAutomatonNode extend(int c) {
        return extend(c, lastNode);
    }

    public void refresh() {
        Queue<SuffixAutomatonNode> q = new LinkedList<SuffixAutomatonNode>();

        for (SuffixAutomatonNode node : list) {
            if (node.suffixNode != null) {
                ++node.suffixNode.deg;
            }
            node.sum = 0;
        }

        for (SuffixAutomatonNode node : list) {
            if (node.deg == 0) {
                q.add(node);
            }
        }

        while (!q.isEmpty()) {
            SuffixAutomatonNode node = q.poll();
            if (node.suffixNode != null) {
                --node.suffixNode.deg;
                node.suffixNode.sum += node.sum + node.tag;
                if (0 == node.suffixNode.deg) {
                    q.offer(node.suffixNode);
                }
            }
        }
    }
}