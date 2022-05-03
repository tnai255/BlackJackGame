package nz.ac.auckland.se281.a3.dealer;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;

// interface for dealer strategy
public interface DealerStrategy {

	// dealer strategy needs to only decide action does not place bet
	public Action decideAction(Hand hand);

}
