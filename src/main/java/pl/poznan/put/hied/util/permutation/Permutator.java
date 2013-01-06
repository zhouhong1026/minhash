package pl.poznan.put.hied.util.permutation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class Permutator<T> implements Iterator<T[]> {

	private final T[] elements;
	private final T[] outputElements;
	private final ExactIndexPermutator indexPermutator;

	public Permutator(T[] elements) {
		this.elements = elements;
		outputElements = Arrays.copyOf(elements, elements.length);
		this.indexPermutator = new ExactIndexPermutator(elements.length);
	}

	@Override
	public boolean hasNext() {
		return indexPermutator.hasNext();
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public T[] next() throws NoSuchElementException {
		int[] permutatedIndexes = indexPermutator.next();
		int j = 0;
		for (int i = 0; i < outputElements.length; ++i) {
			outputElements[i] = elements[permutatedIndexes[j++]];
		}
		return outputElements;
	}

	@Override
	public String toString() {
		final int n = outputElements.length;
		final StringBuffer sb = new StringBuffer("[");
		for (int j = 0; j < n; j++) {
			sb.append(outputElements[j].toString());
			if (j < n - 1)
				sb.append(",");
		}
		sb.append("]");
		return new String(sb);
	}

	public int getPermutationCount() {
		return indexPermutator.getPermutationCount();
	}

	public static void main(String[] args) {
		args = new String[] { "a", "b", "c", "d" };
		Permutator<String> iter = new Permutator<String>(args);
		int i = 1;
		while (iter.hasNext()) {
			final String[] a = iter.next();
			System.out.println(i++ + ": " + Arrays.toString(a));
		}
		System.out.println("Perm count: " + iter.getPermutationCount());
	}
}