package sheep.math;

/**
 * Complex x + yi
 */
public class Complex implements Cloneable, Comparable<Complex> {

    public static final Complex ZERO = new Complex();
    public static final Complex ONE = new Complex(1);

    public final double x, y;

    public Complex() {
        this(0);
    }

    public Complex(double x) {
        this(x, 0);
    }

    public Complex(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double real() {
        return x;
    }

    public double imag() {
        return y;
    }

    public double mod() {
        return Math.sqrt(squaredMod());
    }

    public double squaredMod() {
        return x * x + y * y;
    }

    public double arg() {
        return Math.atan2(y, x);
    }

    public Complex negate() {
        return new Complex(-x, -y);
    }

    public Complex add(Complex o) {
        return new Complex(x + o.x, y + o.y);
    }

    public Complex subtract(Complex o) {
        return new Complex(x - o.x, y - o.y);
    }

    public Complex multiply(Complex o) {
        return new Complex(x * o.x - y * o.y, x * o.y + y * o.x);
    }

    public Complex divide(Complex o) {
        double coef = 1.0 / squaredMod();
        return new Complex(coef * (x * o.x + y * o.y), coef * (o.x * y - o.y * x));
    }

    @Override
    public int compareTo(Complex o) {
        if (Double.compare(x, o.x) == 0) {
            return Double.compare(y, o.y);
        }

        return Double.compare(x, o.x);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }

    public boolean equals(Complex o) {
        return Double.compare(x, o.x) == 0 && Double.compare(y, o.y) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complex complex = (Complex) o;

        if (Double.compare(complex.x, x) != 0) return false;
        if (Double.compare(complex.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = x != +0.0d ? Double.doubleToLongBits(x) : 0L;
        result = (int) (temp ^ (temp >>> 32));
        temp = y != +0.0d ? Double.doubleToLongBits(y) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
