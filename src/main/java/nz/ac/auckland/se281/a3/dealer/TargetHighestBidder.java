package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetHighestBidder implements DealerStrategy {
	List<Player> players;
	int indexOfHighestBidder = 0;

	public TargetHighestBidder(List<Player> players) {
		this.players = players;
	}

	@Override
	public Action decideAction(Hand hand) {

		int highestBid = players.get(0).getHand().getBet();

		for (Player player : players) {
			if (player.getHand().getBet() > highestBid) {
				highestBid = player.getHand().getBet();
				indexOfHighestBidder = players.indexOf(player);
			}
		}

		if (players.get(indexOfHighestBidder).getHand().isBlackJack() && hand.is21() && !hand.isBlackJack()) {
			if (hand.getScore() > 16) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		}

		if (players.get(indexOfHighestBidder).getHand().getScore() <= hand.getScore()
				|| players.get(indexOfHighestBidder).getHand().isBust()) {
			return Action.HOLD;
		}

		return Action.HIT;
	}

}
