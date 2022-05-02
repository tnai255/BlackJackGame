package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.BotStrategy;
import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {
	BotStrategy strategy;

	public Bot(String name, BotStrategy chosenStrategy) {
		super(name);
		strategy = chosenStrategy;
	}

	@Override
	public Action decideAction(Hand hand) {
		return strategy.decideAction(hand);
	}

	@Override
	public int makeABet() {
		return strategy.makeABet();
	}

}
