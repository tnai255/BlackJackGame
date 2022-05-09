package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;

/**
 * 
 * You should change this class for Task 2
 *
 */
public class Dealer extends Participant {
	DealerStrategy strategy;

	/**
	 * Constructor of dealer which sets initial strategy of dealer and dealer name
	 * 
	 * @param name     (name of dealer)
	 * @param strategy (name of the strategy initially chosen)
	 */
	public Dealer(String name, DealerStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}

	/**
	 * Decides action of dealer by return the implementation of the strategy that is
	 * stored in the instance field
	 * 
	 * @param hand (hand of the dealer to access key methods)
	 */
	@Override
	public Action decideAction(Hand hand) {
		return strategy.decideAction(hand);
	}

	/**
	 * Sets strategy instance field to the input strategy so that decide action
	 * strategy changes
	 * 
	 * @param strategy (strategy to change the current dealer strategy to during run
	 *                 time)
	 */
	public void setStrategy(DealerStrategy strategy) {
		this.strategy = strategy;
	}

}
