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

	public Dealer(String name, DealerStrategy strategy) {
		super(name);
		this.strategy = strategy;
	}

	@Override
	public Action decideAction(Hand hand) {
		return strategy.decideAction(hand);
	}

}
