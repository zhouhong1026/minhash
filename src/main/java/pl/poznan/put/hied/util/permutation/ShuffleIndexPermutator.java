package pl.poznan.put.hied.util.permutation;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Random;

public class ShuffleIndexPermutator implements IndexPermutator {

	private final int size;
	private final int[] permutationIndexes;
	private final Random random = new Random();

	private final boolean next = true;

	public ShuffleIndexPermutator(int length) {
		this.size = length;
		this.permutationIndexes = new int[size];
		for (int i = 0; i < size; ++i) {
			permutationIndexes[i] = i;
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

	@Override
	public int[] next() throws NoSuchElementException {
		shuffle(permutationIndexes);
		return permutationIndexes;
	}

	private void shuffle(int[] input) {
		for (int i = 0; i < input.length; ++i) {
			int ia = random.nextInt(input.length);
			int ib = random.nextInt(input.length);
			swap(input, ia, ib);
		}
	}

	private void swap(int array[], int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
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
		return Integer.MAX_VALUE;
	}

	public static void main(String[] args) {
		ShuffleIndexPermutator iter = new ShuffleIndexPermutator(5);
		while (iter.hasNext()) {
			final int[] a = iter.next();
			System.out.println(Arrays.toString(a));
		}

	}
}