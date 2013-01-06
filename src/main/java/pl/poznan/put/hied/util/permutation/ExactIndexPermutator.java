package pl.poznan.put.hied.util.permutation;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ExactIndexPermutator implements IndexPermutator {

	private final int size;
	private final int[] permutationIndexes;
	private final int[] outputPermutationIndexes;

	private boolean next = true;

	public ExactIndexPermutator(int length) {
		this.size = length;
		this.permutationIndexes = new int[size + 1];
		this.outputPermutationIndexes = new int[size];
		for (int i = 0; i < size + 1; i++) {
			this.permutationIndexes[i] = i;
		}
	}

	@Override
	public boolean hasNext() {
		return next;
	}

	@Override
	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	private void swap(int array[], int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	private void formNextPermutation() {
		for (int i = 0; i < size; i++) {
			outputPermutationIndexes[i] = permutationIndexes[i + 1] - 1;
		}
	}

	@Override
	public int[] next() throws NoSuchElementException {
		formNextPermutation();
		int i = size - 1;
		while (permutationIndexes[i] > permutationIndexes[i + 1]) {
			i--;
		}
		if (i == 0) {
			next = false;
			for (int j = 0; j < size + 1; j++) {
				permutationIndexes[j] = j;
			}
			return outputPermutationIndexes;
		}
		int j = size;
		while (permutationIndexes[i] > permutationIndexes[j]) {
			j--;
		}
		swap(permutationIndexes, i, j);
		int r = size;
		int s = i + 1;
		while (r > s) {
			swap(permutationIndexes, r, s);
			r--;
			s++;
		}
		return outputPermutationIndexes;
	}

	@Override
	public String toString() {
		final int n = permutationIndexes.length;
		final StringBuffer sb = new StringBuffer("[");
		for (int j = 0; j < n; j++) {
			sb.append(permutationIndexes[j]);
			if (j < n - 1)
				sb.append(",");
		}
		sb.append("]");
		return new String(sb);
	}

	@Override
	public int getPermutationCount() {
		int result = 1;
		for (int i = 1; i < size + 1; ++i) {
			result *= i;
		}
		return (result == 0) ? Integer.MAX_VALUE : result;
	}

	public static void main(String[] args) {
		ExactIndexPermutator iter = new ExactIndexPermutator(5);
		while (iter.hasNext()) {
			final int[] a = iter.next();
			System.out.println(Arrays.toString(a));
		}

	}
}