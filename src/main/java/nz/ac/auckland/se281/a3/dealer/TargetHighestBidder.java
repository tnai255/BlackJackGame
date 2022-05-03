package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetHighestBidder implements DealerStrategy {
	// sets instance fields
	List<Player> players;
	int indexOfHighestBidder = 0;

	// takes in the players to get the need methods
	public TargetHighestBidder(List<Player> players) {
		this.players = players;
	}

	@Override
	public Action decideAction(Hand hand) {

		// set initial highest bid to the human player (i.e player 0)
		int highestBid = players.get(0).getHand().getBet();

		// loops through players array and check if there is a higher bid
		for (Player player : players) {
			if (player.getHand().getBet() > highestBid) {
				// updates highest bid
				highestBid = player.getHand().getBet();
				// updates index of highest bid
				indexOfHighestBidder = players.indexOf(player);
			}
		}

		// if the highest bid player has blackjack and dealer doesn't then if it dealer
		// score is at least 17 it holds otherwise it hits
		if (players.get(indexOfHighestBidder).getHand().isBlackJack() && !hand.isBlackJack()) {
			if (hand.getScore() > 16) {
				return Action.HOLD;
			}
		}

		// if dealer already has a higher or equal score to highest bid player or if the
		// highest bid player is a bust it holds
		if (players.get(indexOfHighestBidder).getHand().getScore() <= hand.getScore()
				|| players.get(indexOfHighestBidder).getHand().isBust()) {
			return Action.HOLD;
		}

		// if these scenarios are not met it will hit
		return Action.HIT;
	}

}
