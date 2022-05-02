package nz.ac.auckland.se281.a3;

import nz.ac.auckland.se281.a3.Participant.Action;

public class LowRiskStrategy implements BotStrategy {

	@Override
	public Action decideAction(Hand hand) {
		// checks if the score of the hand is at least 17 in which case it holds
		// otherwise it hits
		if (hand.getScore() > 16) {
			return Action.HOLD;
		} else {
			return Action.HIT;
		}

	}

	@Override
	public int makeABet() {
		// TODO Auto-generated method stub
		return 1;
	}

}
