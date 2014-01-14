package sheep.common.pair;

/**
 * Long Pair
 */
public class LongPair extends ComparablePair<Long, Long> {

    public static LongPair of(Long first, Long second) {
        return new LongPair(first, second);
    }

    public LongPair() {
        super();
    }

    public LongPair(Long first, Long second) {
        super(first, second);
    }
}
