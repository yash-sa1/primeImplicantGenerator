package qnmc;

import qnmc.src.com.qnmc.model.MinTerm;
import qnmc.src.com.qnmc.service.MinTermService;
import qnmc.src.com.qnmc.utils.ExceptionQuine;

public class Quine {
	// macro
	protected static final int MAX_TERMS = 0xff;// 0xff=255
	// attribute
	public MinTerm[] terms = new MinTerm[MAX_TERMS];
	public int count = 0;

	// adding minterms
	public void addTerm(String str) throws ExceptionQuine {
		if (count == MAX_TERMS)
			throw ExceptionQuine.getInstance("Quine::addTerm()");
		terms[count++] = new MinTerm(str);
	}

	// converted to string
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < count; i++) {
			buf.append(terms[i] + "\n");
		}
		return buf.toString();
	}

	// see whether the element already exists
	public boolean hasTerm(MinTerm a) throws ExceptionQuine {
		for (int i = 0; i < count; i++) {
			if (new MinTermService().equalsTo(a, terms[i]))
				return true;
		}
		return false;
	}

	// verification of the function
	public void simplify() throws ExceptionQuine {
		while (reduce() > 0)
			;
	}

	// reduction of the minterm
	private int reduce() throws ExceptionQuine {
		int reducedCount = 0;
		MinTerm[] reducedTerms = new MinTerm[MAX_TERMS];
		boolean[] used = new boolean[MAX_TERMS];

		for (int i = 0; i < count; i++) {
			for (int j = i + 1; j < count; j++) {
				if (new MinTermService().countingDifferencesBetweenMinterms(terms[i], terms[j]) == 1) {
					reducedTerms[reducedCount++] = MinTerm.mergeMinterms(terms[i], terms[j]);
					used[i] = true;
					used[j] = true;
				}
			}
		}

		int totalReduced = reducedCount;
		for (int i = 0; i < count; i++) {
			if (!used[i]) {
				reducedTerms[totalReduced++] = terms[i];
			}
		}

		count = 0;
		for (int i = 0; i < totalReduced; i++) {
			if (!hasTerm(reducedTerms[i])) {
				terms[count++] = reducedTerms[i];
			}
		}

		return reducedCount;
	}
}