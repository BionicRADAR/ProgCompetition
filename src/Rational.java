package model;

public class Rational extends Number implements Comparable<Rational> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final int num;
	public final int den;
	
	public Rational(int n, int d) {
		if (d == 0) {
			throw new IllegalArgumentException("Denominator cannot be zero");
		}
		IntPair p = simplify(n, d);
		if (p.last < 0) {
			num = -p.first;
			den = -p.last;
		}
		else {
			num = p.first;
			den = p.last;
		}
	}
	
	public Rational reciprocal() {
		return new Rational(den, num);
	}
	
	public Rational add(Rational addend) {
		return new Rational(num * addend.den + den * addend.num, den*addend.den);
	}
	
	public Rational sub(Rational subtrahend) {
		return new Rational(num * subtrahend.den - den * subtrahend.num, den*subtrahend.den);
	}
	
	public Rational times(Rational factor) {
		return new Rational(num * factor.num, den * factor.den);
	}
	
	public Rational div(Rational divisor) {
		return new Rational(num * divisor.den, den * divisor.num);
	}
	
	public Rational add(int addend) {
		return new Rational(num + addend * den, den);
	}
	
	public Rational sub(int subtrahend) {
		return new Rational(num - subtrahend * den, den);
	}
	
	public Rational times(int factor) {
		return new Rational(num * factor, den);
	}
	
	public Rational div(int divisor) {
		return new Rational(num, den * divisor);
	}
	
	@Override
	public int compareTo(Rational arg0) {
		return new Integer(num * arg0.den).compareTo(arg0.num * den);
	}
	
	public int compareTo(int arg0) {
		return new Integer(num).compareTo(arg0 * den);
	}
	
	private int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}
	
	private IntPair simplify(int numer, int denom) {
		int gcf = gcd(numer, denom);
		return new IntPair(numer/gcf, denom/gcf);
	}

	@Override
	public double doubleValue() {
		return (double) num/(double) den;
	}

	@Override
	public float floatValue() {
		return (float) num/(float) den;
	}

	@Override
	public int intValue() {
		return num/den;
	}

	@Override
	public long longValue() {
		return num/den;
	}

	public String toString() {
		return num + "/" + den;
	}
	
}
