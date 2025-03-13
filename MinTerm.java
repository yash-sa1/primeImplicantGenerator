package qnmc;

public class MinTerm {
	// input data representation
	public static final char NOT_CH = '0';
	public static final char SET_CH = '1';
	public static final char ANY_CH = '_';
	// internal data representation
	protected static final int NOT = 0;
	protected static final int SET = 1;
	protected static final int ANY = -1;
	// attribute
	protected int count;
	protected int[] term;

	// constructing & reading
	public MinTerm(String str) {
		term = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			switch (str.charAt(i)) {
			case NOT_CH:
				term[count++] = NOT;
				break;
			case SET_CH:
				term[count++] = SET;
				break;
			case ANY_CH:
				term[count++] = ANY;
				break;
			}
		}
	}

	// converted to string

	public String toString() {
		StringBuffer buf = new StringBuffer(count);
		for (int i = 0; i < count; i++) {
			switch (term[i]) {
			case NOT:
				buf.append(NOT_CH);
				break;
			case SET:
				buf.append(SET_CH);
				break;
			case ANY:
				buf.append(ANY_CH);
				break;
			}
		}
		return buf.toString();
	}

	// comparing minterm

	public boolean isSame(MinTerm a) throws ExceptionQuine {
		if (count != a.count)
			throw new ExceptionQuine("MinTerm::isSame()");
		for (int i = 0; i < count; i++) {
			if (term[i] != a.term[i])
				return false;

		}
		return true;
	}

	// number of the difference

	public int resolutionCount(MinTerm a) throws ExceptionQuine {
		if (count != a.count)
			throw new ExceptionQuine("MinTerm::resolutionCount()");
		int resCount = 0;
		for (int i = 0; i < count; i++) {
			if (term[i] != a.term[i])
				resCount++;
		}
		return resCount;
	}

	// position of the first difference

	public int resolutionPos(MinTerm a) throws ExceptionQuine {
		if (count != a.count)
			throw new ExceptionQuine("MinTerm::resoutionPos()");
		for (int i = 0; i < count; i++) {
			if (term[i] != a.term[i])
				return i;
		}

		return -1;
	}

	// combining two minterms

	public static MinTerm combine(MinTerm a, MinTerm b) throws ExceptionQuine {
		if (a.count != b.count)
			throw new ExceptionQuine("MinTerm::combine()");
		StringBuffer buf = new StringBuffer(a.count);
		for (int i = 0; i < a.count; i++) {
			if (a.term[i] != b.term[i])
				buf.append(ANY_CH);
			else
				buf.append(a.toString().charAt(i));
		}
		return new MinTerm(buf.toString());
	}
}
