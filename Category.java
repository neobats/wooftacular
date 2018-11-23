package scoringSheet;

public class Category {
	private int entries;
	private String name;
	private double baseWeight;
	private double adjustedWeight;
	public Category() {
		name = "Default";
		entries = 1;
		baseWeight = 1.0;
		adjustedWeight = 0.6;
	}//default
	public Category(String categoryName, int numberOfEntrants, double categoryWeight, double adjustedWeight) {
		setName(categoryName);
		setEntrants(numberOfEntrants);
		setBaseWeight(categoryWeight);
		setAdjustedWeight(adjustedWeight);
	}//overloaded
	
	public String getCategoryName() {
		return name;
	}
	public int getEntrants() {
		return entries;
	}
	public double getWeight() {
		return baseWeight;
	}
	public double getAdjustedWeight() {
		return adjustedWeight;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	public void setEntrants(int newNumber) {
		entries = newNumber;
	}
	public void setBaseWeight(double newWeight) {
		baseWeight = newWeight;
	}
	public void setAdjustedWeight(double newWeight) {
		adjustedWeight = newWeight;
	}
	
}//Category
