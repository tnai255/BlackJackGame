package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {
	BotStrategy strategy;

	// takes in strategy based on factory pattern
	public Bot(String name, BotStrategy chosenStrategy) {
		super(name);
		strategy = chosenStrategy;
	}

	@Override
	public Action decideAction(Hand hand) {
		// decides action based on strategy
		return strategy.decideAction(hand);
	}

	@Override
	public int makeABet() {
		// makes bet based on strategy
		return strategy.makeABet();
	}

}
