package sheep.common.pair;

/**
 * Integer Pair
 */
public class IntPair extends ComparablePair<Integer, Integer> {

    public static IntPair of(Integer first, Integer second) {
        return new IntPair(first, second);
    }

    public IntPair() {
        super();
    }

    public IntPair(Integer first, Integer second) {
        super(first, second);
    }
}
