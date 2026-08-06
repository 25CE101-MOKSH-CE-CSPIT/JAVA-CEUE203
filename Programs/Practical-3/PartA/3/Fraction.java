import java.util.Objects;
public class Fraction {
    private int num, den;
    public Fraction(int n, int d) {
        int g = gcd(Math.abs(n), Math.abs(d));
        num = n / g;
        den = d / g;
        if (den < 0) { num = -num; den = -den; }
    }
    private int gcd(int a, int b) { return b == 0 ? a : gcd(b, a % b); }
    @Override public String toString() { return num + "/" + den; }
    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fraction f = (Fraction) o;
        return num == f.num && den == f.den;
    }
    @Override public int hashCode() { return Objects.hash(num, den); }
}
