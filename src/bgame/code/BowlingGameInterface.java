package bgame.code;

public interface BowlingGameInterface {
	/*
	 * To record the number of pins in each roll
	 */
	void roll(int noOfPins);
	/*
	 * To calculate the score, given the value of each frame of a match is available
	 */
	int score();
	/*
	 * To generate random rolls for each frame to test the 
	 * logic written for calculating score. 
	 * Below method calls roll and score methods. 
	 * But still the roll and score methods are in this interface so that, we can 
	 * test a match with another logic if written in another class. 
	 */
	int generateAMatch();
}
