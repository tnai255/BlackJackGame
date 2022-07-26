package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class HighRiskStrategy implements BotStrategy {

	/**
	 * decides action of the bot based on whether the current hand has a score of at
	 * least 19 (HOLD) or not (HIT)
	 * 
	 * @param hand (hand of the bot to access key methods)
	 */
	@Override
	public Action decideAction(Hand hand) {
		// checks if the score of the hand is at least 19 in which case it holds
		// otherwise it hits
		if (hand.getScore() > 18) {
			return Action.HOLD;
		}

		return Action.HIT;

	}

	/**
	 * Makes a bet randomly by generating a number between 50 and 100 which is a
	 * high risk bet
	 */
	@Override
	public int makeABet() {
		// code adapted from
		// https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
		return new Random().nextInt(51) + 50;
	}

}
