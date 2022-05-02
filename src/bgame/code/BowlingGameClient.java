package bgame.code;

import java.util.Random;

public class BowlingGameClient {
	
	static int rollNum = 0;
	static int bowlRollLength = 20;
	public static void main(String[] args) {
		BowlingGame bowlingGame = new BowlingGame();
		int score = bowlingGame.generateAMatch();
		System.out.println("The score of the match is: "+ score);
		
	}


}
