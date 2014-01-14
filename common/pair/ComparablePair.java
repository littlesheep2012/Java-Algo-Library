package sheep.common.pair;

/**
 * Comparable Pair
 */
public class ComparablePair<F extends Comparable<F>, S extends Comparable<S>>
        extends Pair<F, S> implements Comparable<ComparablePair<F, S>> {

    public static <F extends Comparable<F>, S extends Comparable<S>>
            ComparablePair<F, S> of(F first, S second) {
        return new ComparablePair<F, S>(first, second);
    }

    public ComparablePair() {
        super();
    }

    public ComparablePair(F first, S second) {
        super(first, second);
    }

    @Override
    public int compareTo(ComparablePair<F, S> to) {
        if (this.first.compareTo(to.first) != 0) {
            return this.first.compareTo(to.first);
        }
        return this.second.compareTo(to.second);
    }
}
