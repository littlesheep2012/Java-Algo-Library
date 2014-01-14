package sheep.common.pair;

/**
 * Pair
 */
public class Pair<F, S> {
    public F first;
    public S second;

    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<F, S>(first, second);
    }

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
        this(null, null);
    }

    @Override
    public boolean equals(Object o) {
        Pair<F, S> to = (Pair<F, S>) o;
        return first == to.first && second == to.second;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + first.toString() + " " + second.toString() + "]";
    }
}
