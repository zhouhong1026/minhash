package pl.poznan.put.hied.util.permutation;

import java.util.Arrays;

public class RevertIndexPermutator implements IndexPermutator {

	private final IndexPermutator baseIndexPermutator;

	public RevertIndexPermutator(IndexPermutator baseIndexPermutator) {
		super();
		this.baseIndexPermutator = baseIndexPermutator;
	}

	@Override
	public boolean hasNext() {
		return baseIndexPermutator.hasNext();
	}

	@Override
	public int[] next() {
		int[] perm = baseIndexPermutator.next();
		for (int i = 0; i < perm.length / 2; ++i) {
			swap(perm, i, perm.length - i - 1);
		}
		return perm;
	}

	@Override
	public void remove() {
		baseIndexPermutator.remove();
	}

	@Override
	public int getPermutationCount() {
		return baseIndexPermutator.getPermutationCount();
	}

	private void swap(int array[], int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static void main(String[] args) {
		IndexPermutator iter = new ExactIndexPermutator(5);
		iter = new RevertIndexPermutator(iter);
		while (iter.hasNext()) {
			final int[] a = iter.next();
			System.out.println(Arrays.toString(a));
		}

	}

}