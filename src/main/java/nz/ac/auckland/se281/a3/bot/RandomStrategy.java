package nz.ac.auckland.se281.a3.bot;

import java.util.Random;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

public class RandomStrategy implements BotStrategy {

	/**
	 * decides action by randomly choosing whether to hit or hold
	 * 
	 * @param hand (hand of the bot to access key methods)
	 */
	@Override
	public Action decideAction(Hand hand) {
		// generates a random boolean either true or false
		boolean randomBoolean = new Random().nextBoolean();
		// if true it will return the action hit otherwise will return action hold
		if (randomBoolean) {
			return Action.HIT;
		} else {
			return Action.HOLD;
		}
	}

	/**
	 * Randomly makes a bet by generating an integer between 1 and 100
	 */
	@Override
	public int makeABet() {
		// the plus one ensures it is not 0
		// code adapted from
		// https://www.codegrepper.com/code-examples/java/Java+random+number+between+1+and+100
		return new Random().nextInt(100) + 1;
	}

}
