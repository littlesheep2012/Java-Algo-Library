package sheep.util;

/**
 * Bitset
 */
public class Bitset {

    private int len, vlen;
    private int bit[];

    public Bitset(int bits) {
        len = bits;
        vlen = (bits + 31) >> 5;
        bit = new int[vlen];
    }

    //a[x] = y
    public void set(int x, int y) {
        if (((bit[x >> 5] >>> (x & 31)) & 1) != y) {
            bit[x >> 5] ^= 1 << (x & 31);
        }
    }

    public int get(int x) {
        return (bit[x >> 5] >>> (x & 31)) & 1;
    }

    public Bitset xor(Bitset o) {

        Bitset ret = new Bitset(len);
        for (int i = 0; i < vlen; ++i) {
            ret.bit[i] = bit[i] ^ o.bit[i];
        }

        return ret;
    }

    public Bitset and(Bitset o) {

        Bitset ret = new Bitset(len);
        for (int i = 0; i < vlen; ++i) {
            ret.bit[i] = bit[i] & o.bit[i];
        }

        return ret;
    }

    public Bitset or(Bitset o) {

        Bitset ret = new Bitset(len);
        for (int i = 0; i < vlen; ++i) {
            ret.bit[i] = bit[i] | o.bit[i];
        }

        return ret;
    }

    public Bitset not() {

        Bitset ret = new Bitset(len);
        for (int i = 0; i < vlen; ++i) {
            ret.bit[i] = ~bit[i];
        }

        return ret;
    }
}
