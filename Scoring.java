package scoringSheet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Scoring {

	public static void main(String[] args) throws IOException {
		File categories = new File("CategoryNames.txt");
		Scanner scan = new Scanner(categories);
		File scoreFile = new File("AllScores.txt");
		Scanner scan2 = new Scanner(scoreFile);
		final int numOfCategories = CountLines.linesInFile(categories);// 6
		// arrays needed for calculations
		String[] fileNames = new String[numOfCategories];
		for (int i = 0; i < fileNames.length; i++)// creates an array with the names of categories
			fileNames[i] = scan.nextLine();

		File[] categoryNames = new File[numOfCategories];
		for (int i = 0; i < categoryNames.length; i++)// makes an array of the category input files
			categoryNames[i] = new File(fileNames[i] + ".txt");

		int[] entryNumbers = new int[numOfCategories];
		for (int i = 0; i < entryNumbers.length; i++) {// counts number of entries in each category
			entryNumbers[i] = CountLines.linesInFile(categoryNames[i]) / 3;
		}

		File[] scoreFiles = createFileArray(scan2, numOfCategories);
		for (int i = 0; i < scoreFiles.length; i++) {
		}

		// creates arrays with the contestant info
		String[] cute = contestantInfo(categoryNames[0], 3);
		String[] ugly = contestantInfo(categoryNames[1], 3);
		String[] costume = contestantInfo(categoryNames[2], 3);
		String[] agility = contestantInfo(categoryNames[3], 3);
//		String[] likeOwner = contestantInfo(categoryNames[4], 3);
		String[] tricks = contestantInfo(categoryNames[4], 3);

		String[] cuteScores = contestantInfo(scoreFiles[0], 2);
		String[] uglyScores = contestantInfo(scoreFiles[1], 2);
		String[] costumeScores = contestantInfo(scoreFiles[2], 2);
		String[] agilityScores = contestantInfo(scoreFiles[3], 2);
//		String[] likeOwnerScores = contestantInfo(scoreFiles[4], 2);
		String[] tricksScores = contestantInfo(scoreFiles[4], 2);

		// calculated weights based upon total entries
		double cuteWeight = Weights.newWeight(entryNumbers, cute);
		double uglyWeight = Weights.newWeight(entryNumbers, ugly);
		double costumeWeight = Weights.newWeight(entryNumbers, costume);
		double agilityWeight = Weights.newWeight(entryNumbers, agility);
//		double ownerWeight = Weights.newWeight(entryNumbers, likeOwner);
		double trickWeight = Weights.newWeight(entryNumbers, tricks);

		Category cutestDog = new Category(fileNames[0], entryNumbers[0], 1.0, cuteWeight);
		Category ugliestDog = new Category(fileNames[1], entryNumbers[1], 1.0, uglyWeight);
		Category costumeContest = new Category(fileNames[2], entryNumbers[2], 1.25, costumeWeight);
		Category agilityContest = new Category(fileNames[3], entryNumbers[3], 1.5, agilityWeight);
//		Category dogMostLikeOwner = new Category(fileNames[4], entryNumbers[4], 1.25, ownerWeight);
		Category mostTricks = new Category(fileNames[4], entryNumbers[4], 1.5, trickWeight);

		String[] cuteScoresWeighted = storeWeightedScores(cuteScores, cuteWeight);
		String[] uglyScoresWeighted = storeWeightedScores(uglyScores, uglyWeight);
		String[] costumeScoresWeighted = storeWeightedScores(costumeScores, costumeWeight);
		String[] agilityScoresWeighted = storeWeightedScores(agilityScores, agilityWeight);
//		String[] likeOwnerScoresWeighted = storeWeightedScores(likeOwnerScores, ownerWeight);
		String[] tricksScoresWeighted = storeWeightedScores(tricksScores, trickWeight);

		for (int i = 0; i < uglyScoresWeighted.length; i++) {
			System.out.println(uglyScoresWeighted[i]);
		}

		try (PrintWriter save = new PrintWriter("cuteWeight.txt")) {
			save.println(presentCategory(cutestDog.getCategoryName(), cutestDog.getEntrants(), cutestDog.getWeight(),
					cutestDog.getAdjustedWeight()));
			for (int i = 0; i < cuteScoresWeighted.length; i++) {
				save.println(cuteScoresWeighted[i]);
			}
		}
		try (PrintWriter save = new PrintWriter("uglyWeight.txt")) {
			save.println(presentCategory(ugliestDog.getCategoryName(), ugliestDog.getEntrants(), ugliestDog.getWeight(),
					ugliestDog.getAdjustedWeight()) + "\n");
			for (int i = 0; i < uglyScoresWeighted.length; i++) {
				save.println("This is a test.");
				save.println(uglyScoresWeighted[i]);
			}
		}
		try (PrintWriter save = new PrintWriter("costumeWeight.txt")) {
			save.println(presentCategory(costumeContest.getCategoryName(), costumeContest.getEntrants(),
					costumeContest.getWeight(), costumeContest.getAdjustedWeight()));
			for (int i = 0; i < costumeScoresWeighted.length; i++) {
				save.println(costumeScoresWeighted[i]);
			}
		}
		try (PrintWriter save = new PrintWriter("agilityWeight.txt")) {
			save.println(presentCategory(agilityContest.getCategoryName(), agilityContest.getEntrants(),
					agilityContest.getWeight(), agilityContest.getAdjustedWeight()));
			for (int i = 0; i < agilityScoresWeighted.length; i++) {
				save.println(agilityScoresWeighted[i]);
			}
		}
		try (PrintWriter save = new PrintWriter("mostTricksWeight.txt")) {
			save.println(presentCategory(mostTricks.getCategoryName(), mostTricks.getEntrants(), mostTricks.getWeight(),
					mostTricks.getAdjustedWeight()));
			for (int i = 0; i < tricksScoresWeighted.length; i++) {
				save.println(tricksScoresWeighted[i]);
			}
		}
		JOptionPane.showMessageDialog(null, "All scores are saved. Check the files!", "Finished",
				JOptionPane.INFORMATION_MESSAGE);
		scan.close();
	}// main
		//////////////// HELPER METHODS \\\\\\\\\\\\\\\\
	   ////////////////                  \\\\\\\\\\\\\\\\

	private static void numberError(String[] array, int divisor) {
		if (array.length % divisor > 0) {
			JOptionPane.showMessageDialog(null,
					"It looks like you're missing a line or two, "
							+ "please check that you have an owner's name, dog's name, and dog's number.",
					"Password", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
	}// numberError

	private static String[] contestantInfo(File fileName, int divisor) throws IOException {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(fileName);
		int length = CountLines.linesInFile(fileName);

		String[] array = new String[length]; // creates array length of file
		numberError(array, divisor);
		for (int j = 0; j <= length - 1; j++) {
			array[j] = input.nextLine();
		} // fills array with strings from file
		return array;
	}// contestantInfo

	private static int[] sortMax(int[] input) {
		for (int i = 0; i < input.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < input.length; j++)
				if (input[j] > input[index])
					index = j;
			int largerNumber = input[index];
			input[index] = input[i];
			input[i] = largerNumber;
		}
		return input;
	}// sortMax

	private static String presentContestant(String person, String doggyName, String number) {
		String presentation = "Owner: " + person.toUpperCase() + " Dog: " + doggyName.toUpperCase() + " #" + number;
		return presentation;
	}// present

	private static String presentCategory(String name, int entries, double baseWeight, double calWeight) {
		String presentation = "Category Name: " + name + " Number of Entries: " + entries + " Base Weight " + baseWeight
				+ " Weight from Total " + calWeight;
		return presentation;
	}

	private static File[] createFileArray(Scanner scan, int size) {
		String[] fileNames = new String[size];
		for (int i = 0; i < fileNames.length; i++)// creates an array with the names of categories
			fileNames[i] = scan.nextLine();
		File[] array = new File[fileNames.length];
		for (int i = 0; i < array.length; i++)// makes an array of the category input files
			array[i] = new File(fileNames[i] + ".txt");
		return array;
	}

	private static String[] storeWeightedScores(String[] scoresFromCat, double calcedWeight) {
		Double[] array = new Double[scoresFromCat.length];
		for (int i = 1; i < scoresFromCat.length; i += 2) {
			String str = scoresFromCat[i];
			double doubleStr = Double.parseDouble(str);
			array[i] = doubleStr * calcedWeight;
		} // store weighted scores into double array

		for (int i = 1; i < scoresFromCat.length; i += 2) {
			scoresFromCat[i] = "" + array[i];
		} // add weighted score back into string array
		return scoresFromCat;
	}
}// Scoring
