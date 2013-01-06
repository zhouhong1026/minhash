package pl.poznan.put.hied;

import pl.poznan.put.hied.util.Timer;
import pl.poznan.put.hied.util.permutation.ExactIndexPermutator;
import pl.poznan.put.hied.util.permutation.IndexPermutator;
import pl.poznan.put.hied.util.permutation.RevertIndexPermutator;
import pl.poznan.put.hied.util.permutation.ShuffleIndexPermutator;

/**
 * Application entry point.
 * 
 * @author pmendelski
 * 
 */
public final class EntryPoint {

	private EntryPoint() {

	}

	public static void main(String... args) throws Exception {
		String fileName = args[0];
		int permutationCount = Integer.parseInt(args[1]);
		boolean usePermutator = args.length >= 3
				&& "true".equalsIgnoreCase(args[2]);

		Timer timer = new Timer().start();
		Problem problem = new ProblemParser().parse(fileName);
		print("Problem parsed in: " + timer.stop().getFormattedResult());

		timer = new Timer().start();
		IndexPermutator permutator = getPermutator(usePermutator,
				problem.getAttributes().length);
		float[][] similarityMatrix = problem.computeSimilarityMatrix(
				permutationCount, permutator);
		print("Problem computed in: " + timer.stop().getFormattedResult());

		print(similarityMatrix);
	}

	private static IndexPermutator getPermutator(boolean usePermutator,
			int length) {
		IndexPermutator permutator;
		if (usePermutator) {
			permutator = new ExactIndexPermutator(length);
			permutator = new RevertIndexPermutator(permutator);
		} else {
			permutator = new ShuffleIndexPermutator(length);
		}
		return permutator;
	}

	private static void print(float[][] matrix) {
		for (int i = 0; i < matrix.length; ++i) {
			for (int j = 0; j < matrix.length; ++j) {
				System.out.printf("%.2f  ", matrix[i][j]);
			}
			print("");
		}
	}

	private static void print(String msg) {
		System.out.println(msg);
	}
}
