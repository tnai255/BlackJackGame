package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Player;

/**
 * you should change this class for TASK 1
 */
public class Bot extends Player {
	private BotStrategy strategy;

	/**
	 * Bot constructor that sets the strategy instance field to the input strategy
	 * and sets the name
	 * 
	 * @param name           (name of the Bot)
	 * @param chosenStrategy (strategy chosen by the user for the bot to play)
	 */
	public Bot(String name, BotStrategy chosenStrategy) {
		super(name);
		strategy = chosenStrategy;
	}

	/**
	 * Decides the action of the bot i.e. to hit or hold by returning the decide
	 * action for the chosen strategy
	 * 
	 * @param hand (hand of the bot)
	 */
	@Override
	public Action decideAction(Hand hand) {
		// decides action based on strategy
		return strategy.decideAction(hand);
	}

	/**
	 * Bot makes a bet by returning the bet specifications given by the chosen
	 * strategy
	 */
	@Override
	public int makeABet() {
		// makes bet based on strategy
		return strategy.makeABet();
	}

}
