package bgame.code;

import java.util.Random;

public class BowlingGame implements BowlingGameInterface {

	private static int[] bowlRoll = new int[22]; // array of size - total rolls (20) + maximum bonus rolls(2)
	private static int rollNum = 0; // intial value indicating the roll number.
	private static int bowlRollLength = 20; // 2 rolls in each frame so 2 multipled by 10 frames - 20
	/*
	 * If the total rolls is 20 , then 18 and 19 would be the indexes of the last
	 * frame i.e Array length minus 1 and Array length minus 2 This index is needed
	 * in case the last frame is a strike or spare.
	 */
	private static int lastFrameIndex = 18;

	@Override
	public void roll(int noOfPins) {
		bowlRoll[rollNum] = noOfPins;
		rollNum++;
	}

	@Override
	public int score() {
		int score = 0;
		/*
		 * Increment by 2 for each frame
		 */
		for (int i = 0; i < bowlRollLength; i = i + 2) {
			int pinsInRoll1 = bowlRoll[i];
			if (pinsInRoll1 == 10) {
				score = score + 10;
				if (i < lastFrameIndex) {
					/*
					 * Score when a frame is a strike Check less than 18 to accomodate for the last
					 * frame which is handled separately
					 */
					score = score + bowlRoll[i + 2] + bowlRoll[i + 3];
				}
			} else if (pinsInRoll1 < 10) {
				int pinsInRoll2 = bowlRoll[i + 1];
				int frameScore = pinsInRoll1 + pinsInRoll2;
				score = score + frameScore;
				if (i < lastFrameIndex && frameScore == 10) {
					/*
					 * Score when a frame is a spare
					 */
					score = score + bowlRoll[i + 2];
				}
			}
		}

		/*
		 * If the last frame is a strike or a spare, calculate score:
		 */
		int bonus = bowlRoll[bowlRollLength] + bowlRoll[bowlRollLength + 1];
		score = score + bonus;
		/*
		 * If the 10th frame is a strike and the 2 bonus rolls are also each a strike,
		 * the score is 300. In the question it merely says that "their score is 300"
		 * Does not specifically say that the score of the last frame alone is 300. So I
		 * am assuming that it means the total score becomes 300.
		 * 
		 * Note: Need to get below value from the rolls array to check if the last frame
		 * is a strike. bowlRoll[bowlRollLength - 2] = bowlRoll[20 - 2] = bowlRoll[18]
		 */

		if (bonus == 20 && bowlRoll[bowlRollLength - 2] == 10) {
			score = 300;
		}
		return score;
	}

	@Override
	public int generateAMatch() {
		Random random = new Random();
		/*
		 * Increment by 2 to generate a random number of pins for each roll in a frame
		 * 10 frames in a match so lenth to be less than 20
		 */
		for (int i = 0; i < bowlRollLength - 1; i = i + 2) {
			generateFrames(random);
		}

		/*
		 * Uncomment below 2 lines to test the 10th frame Scenario 1 - If 10th frame is
		 * a strike
		 */

		// bowlRoll[bowlRollLength - 1] = 0;
		// bowlRoll[bowlRollLength - 2] = 10;

		/*
		 * Uncomment below 2 lines to test the 10th frame Scenario 2 - If 10th frame is
		 * a spare
		 */

		// bowlRoll[bowlRollLength - 1] = 3;
		// bowlRoll[bowlRollLength - 2] = 7;

		/*
		 * If the 10th frame is a spare or a strike
		 */
		int lastBowl = bowlRoll[bowlRollLength - 1];
		int secondLastBowl = bowlRoll[bowlRollLength - 2];
		rollNum = 20;
		if (secondLastBowl == 10) {
			System.out.println("Bonus 2 bowls");
			generateFrames(random);
		} else if ((lastBowl + secondLastBowl) == 10) {
			System.out.println("Bonus 1 bowl");
			int randomNum = random.nextInt(11);// max - min +1 so 10-0+1
			roll(randomNum);
		}

		/*
		 * Below code is only to see the value of each frame. Frame 11 is the bonus
		 * frame which will be 0|0 by default. It will have values if the 10th frame is
		 * a spare or a strike
		 */

		for (int j = 0; j < 22; j = j + 2) {
			int m = (j / 2) + 1;
			System.out.println("Frame " + m + " :" + bowlRoll[j] + "|" + bowlRoll[j + 1]);
		}
		System.out.println("Frame 11 will have non zero values only if the 10 the frame is a spare or a strike");
		return score();
	}

	private void generateFrames(Random random) {
		/*
		 * Randomly generate numbers between 0 and 10 indicating number of pins in each
		 * roll
		 */
		int randomNum = random.nextInt(11);// max - min +1 so 10 - 0 + 1
		roll(randomNum);
		if (randomNum == 10) {
			/*
			 * If 1st roll is a 10, this means that the frame is a strike and the 2nd roll
			 * is obviously 0
			 */
			roll(0);

		} else {
			/*
			 * The 2nd roll in a frame can have a maximum value of 10 minus value of the
			 * first roll Hence 10 - randomeNum
			 */
			int randomNum2 = random.nextInt(11 - randomNum);// (10 - randomNum) - 0 + 1
			roll(randomNum2);
		}
	}

}
