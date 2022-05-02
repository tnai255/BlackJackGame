package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;
import nz.ac.auckland.se281.a3.Participant.Action;

public class LowRiskStrategy implements BotStrategy {

	@Override
	public Action decideAction(Hand hand) {
		// checks if the score of the hand is at least 17 in which case it holds
		// otherwise it hits
		if (hand.getScore() > 16) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}

	}

	@Override
	public int makeABet() {
		// generates random number between 10 and 50
		// code adapted from
		// https://stackoverflow.com/questions/5271598/java-generate-random-number-between-two-given-values
		return new Random().nextInt(40) + 10;
	}

}
