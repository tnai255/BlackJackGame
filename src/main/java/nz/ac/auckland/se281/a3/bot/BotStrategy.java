package nz.ac.auckland.se281.a3.bot;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

// three strategies will implement this interface that decides action and bet
public interface BotStrategy {

	/**
	 * Bot decides whether to hold or hit based on the chosen strategy
	 * 
	 * @param hand (gets hand of dealer to access helper methods)
	 * @return Action (whether to hit or hold)
	 */
	public Action decideAction(Hand hand);

	/**
	 * Bot decides the value of bet based on the chosen strategy
	 * 
	 * @return bet value (number of chips to bet)
	 */
	public int makeABet();
}
