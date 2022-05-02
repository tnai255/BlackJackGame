package nz.ac.auckland.se281.a3;

import java.util.Random;

import nz.ac.auckland.se281.a3.Participant.Action;

public class RandomStrategy implements BotStrategy {

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

	@Override
	public int makeABet() {
		// generates a random integer between 1 and 100 (the plus one ensures it is not
		// 0)
		// code adapted from
		// https://www.codegrepper.com/code-examples/java/Java+random+number+between+1+and+100
		return new Random().nextInt(100) + 1;
	}

}
