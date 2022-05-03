package nz.ac.auckland.se281.a3.dealer;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetTopWinner implements DealerStrategy {
	// declares instance fields
	private List<Player> players;
	private List<Integer> netWins = new ArrayList<>();
	private int indexOfTopWinner = 0;

	// takes in players array and net wins
	public TargetTopWinner(List<Player> players, List<Integer> netWins) {
		this.players = players;
		this.netWins = netWins;
	}

	@Override
	public Action decideAction(Hand hand) {

		// initialises top winner as human player
		int topWinner = netWins.get(0);

		// loops through the rest of the players and checks if any have a higher net win
		for (int i = 1; i < 3; i++) {
			if (netWins.get(i) > topWinner) {
				// updates top winner net wins and index
				topWinner = netWins.get(i);
				indexOfTopWinner = i;
			}
		}

		// if the highest bid player has blackjack and dealer doesn't then if it dealer
		// score is at least 17 it holds otherwise it hits
		if (players.get(indexOfTopWinner).getHand().isBlackJack() && !hand.isBlackJack()) {
			if (hand.getScore() > 16) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		}

		// if dealer already has a higher or equal score to highest bid player or if the
		// highest bid player is a bust it holds
		if (players.get(indexOfTopWinner).getHand().getScore() <= hand.getScore()
				|| players.get(indexOfTopWinner).getHand().isBust()) {
			return Action.HOLD;
		}

		// if these scenarios are not met it will hit
		return Action.HIT;
	}

}
