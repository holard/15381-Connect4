package ai;
public class IntPair {
	private int A;
	private int B;
	public IntPair(int a, int b) {
		A = a;
		B = b;
	}
	public int first() {
		return A;
	}
	
	public int second() {
		return B;
	}
	
	@Override
	public int hashCode() {
		return 53*((Integer)A).hashCode() + ((Integer)B).hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof IntPair)) {
			return false;
		}
		IntPair oth = (IntPair) other;
		return (A == (oth.first()) && (B == oth.second()));
	}
}