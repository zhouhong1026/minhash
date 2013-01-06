package pl.poznan.put.hied;

import java.util.Random;

import pl.poznan.put.hied.util.permutation.IndexPermutator;

public class Problem {
	private final Random random = new Random(1);
	private final String[] attributes;
	private final int[][] attributeExistence;
	private final String[] instances;

	public Problem(String[] attributes, int[][] attributeExistence,
			String[] instances) {
		super();
		this.attributes = attributes;
		this.attributeExistence = attributeExistence;
		this.instances = instances;
	}

	public String[] getAttributes() {
		return attributes;
	}

	public int[][] getAttributeExistence() {
		return attributeExistence;
	}

	public String[] getInstances() {
		return instances;
	}

	public float[][] computeSimilarityMatrix(int maxPermutationCount,
			IndexPermutator permutator) {
		int[][] permSimilarities = generateInstanceSimilarityMatrix(
				maxPermutationCount, permutator);
		float[][] similarityMatrix = new float[instances.length][instances.length];
		for (int i = 0; i < instances.length; ++i) {
			for (int j = 0; j < instances.length; ++j) {
				similarityMatrix[i][j] = computeSimilarity(i, j,
						permSimilarities);
			}
		}
		return similarityMatrix;
	}

	private int[][] generateInstanceSimilarityMatrix(int maxPermutationCount,
			IndexPermutator permutator) {
		maxPermutationCount = Math.min(permutator.getPermutationCount(),
				maxPermutationCount);
		int[][] permSimilarities = new int[maxPermutationCount][instances.length];
		for (int i = 0; i < maxPermutationCount; ++i) {
			int[] permIdx = permutator.next();
			generatePermutationSimilarityVector(permSimilarities[i], permIdx);
			// System.out.println(Arrays.toString(permIdx) + ": "
			// + Arrays.toString(permSimilarities[i]));
		}
		return permSimilarities;
	}

	private void generatePermutationSimilarityVector(int[] permSimilarities,
			int[] wordIndexes) {
		for (int instanceIdx = 0; instanceIdx < instances.length; ++instanceIdx) {
			int i = 0;
			for (int permutationIdx : wordIndexes) {
				if (attributeExistence[permutationIdx][instanceIdx] == 1) {
					permSimilarities[instanceIdx] = i;
					break;
				}
				++i;
			}
		}
	}

	private static float computeSimilarity(int instanceA, int instanceB,
			int[][] permSimilarities) {
		int same = 0;
		for (int i = 0; i < permSimilarities.length; ++i) {
			if (permSimilarities[i][instanceA] == permSimilarities[i][instanceB]) {
				same++;
			}
		}
		return same * 1.0f / permSimilarities.length * 1.0f;
	}

}
