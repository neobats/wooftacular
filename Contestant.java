package scoringSheet;

public class Contestant {
	private String humanName;
	private String dogName;
	private int dogNumber;
	private int numOfEntries;
	private double[] scoreArray;
	private int compositeScore;// might need to be local to the client
	private static int count;
	private boolean entered = false;
	
////////// Constructors //////////
public Contestant() {
	humanName = "John";
	dogName = "Buddy";
	dogNumber = 01110;
	count++;
}//defaultContstructor

public Contestant(String person, String dog, int number) {
	setHumanName(person);
	setDogName(dog);
	setDogNumber(number);
	count++;
}//overloadedConstuctor

////////// Accessors ////////////

public String getHumanName() {
	return humanName;
}//getHumanName

public String getDogName() {
	return dogName;
}//getDogName

public int getDogNumber() {
	return dogNumber;
}//getDogNumber

public int getEntries() {
	return numOfEntries;
}//getEntries

public int getContestants() {
	return count;
}
public int getCompositeScore() {
	return compositeScore;
}

////////// Mutators  ////////////

public void setHumanName(String joe) {
	humanName = joe;
}//setHumanName

public void setDogName(String lassie) {
	dogName = lassie;
}//setDogName

public void setDogNumber(int newNumber) {
	dogNumber = newNumber;
}//setDogNumber

public void setNumOfEntries(int entry) {
	numOfEntries = entry;
}//setNumOfEntries

//public void setCompositeScore(Category score) {
	//set this after the competition class is made
//}

////////////// Misc ////////////////
public void addEntry(int additionalEntry) {
	numOfEntries = numOfEntries + additionalEntry;
}//addEntry

public boolean isEntered() {
	if (numOfEntries > 0) {
		entered = true;
	}//if has an entry
	return entered;
}//isEntered

}//Contestant