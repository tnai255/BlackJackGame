package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant;

/**
 * 
 * You should change this class for Task 2
 *
 */
public class Dealer extends Participant {

	public Dealer(String name) {
		super(name);
	}

	@Override
	public Action decideAction(Hand hand) {
		return Action.HOLD;
	}

}
