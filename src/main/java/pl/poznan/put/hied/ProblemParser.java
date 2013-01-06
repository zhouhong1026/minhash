package pl.poznan.put.hied;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProblemParser {
	public Problem parse(String fileName) throws FileNotFoundException {
		String[] attributes;
		int[][] attributeExistence;
		String[] instances;

		int attributeCount = lineCount(fileName) - 1;
		Scanner scanner = new Scanner(new File(fileName));
		String firstLine = scanner.nextLine();
		String[] headerChunks = firstLine.trim().split("\\s+");
		for (int i = 0; i < headerChunks.length; ++i) {
			headerChunks[i] = headerChunks[i].substring(1,
					headerChunks[i].length() - 2);
		}

		attributes = new String[attributeCount];
		instances = new String[headerChunks.length];
		attributeExistence = new int[attributeCount][headerChunks.length];

		int attributeNumber = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			String[] chunks = line.split("\\s+");
			attributes[attributeNumber] = chunks[0].substring(1,
					chunks[0].length() - 2);
			for (int i = 1; i <= headerChunks.length; ++i) {
				attributeExistence[attributeNumber][i - 1] = Integer
						.parseInt(chunks[i]);
			}
			attributeNumber++;
		}
		scanner.close();

		int[] wordIndexes = new int[headerChunks.length];
		for (int i = 0; i < wordIndexes.length; ++i) {
			wordIndexes[i] = i;
		}
		return new Problem(attributes, attributeExistence, instances);
	}

	private int lineCount(String fileName) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File(fileName));
		int wordCount = 0;
		while (scanner.hasNextLine()) {
			scanner.nextLine();
			wordCount++;
		}
		scanner.close();
		return wordCount;
	}
}
