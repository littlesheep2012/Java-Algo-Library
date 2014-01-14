package sheep.datastructure;

/**
 * Range Minimum Query
 *
 * @author sheep
 */
public interface RangeMinimumQuery<T> {
    T get(int left, int right);
}
