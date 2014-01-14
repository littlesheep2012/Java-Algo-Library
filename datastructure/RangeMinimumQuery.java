package sheep.datastructure;

/**
 * Range Minimum Query
 */
public interface RangeMinimumQuery<T> {
    T get(int left, int right);
}
