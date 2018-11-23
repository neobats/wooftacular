package scoringSheet;

public class Weights {
	private static double total = 0;
	
	
	public static double newWeight(int[] entriesSorted, String[] unweighted) {
		double weight = 0;
		int temp;
		for (int i = 0; i < entriesSorted.length; i++) {
			temp = entriesSorted[i];
			total += temp;
		}//create total
		double category = unweighted.length / 3;
		double percent = category / total;
		if (percent <= 0.1) {
			weight = 0.6;
		}else if (percent <= 0.2) {
			weight = 0.8;
		}else if (percent <= 0.3) {
			weight = 1.0;
		}else if (percent <= 0.5) {
			weight = 1.2;
		}else if (percent <= 0.65) {
			weight = 1.4;
		}else if (percent <= 0.8) {
			weight = 1.6;
		}else {
			weight = 1.8;
		}
		resetTotal();
		return weight;

	}//newWeight
	public static double calculateTotal(int[] entriesSorted) {
		int temp;
		for (int i = 0; i < entriesSorted.length; i++) {
			temp = entriesSorted[i];
			total += temp;
		}
		return total;
	}//getTotal
	public static void resetTotal() {
		total = 0;
	}
}//Weights
