package nz.ac.auckland.se281.a3.dealer;

import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetTopWinner implements DealerStrategy {
	// declares instance fields
	private List<Player> players;
	private List<Integer> netWins;
	private int indexOfTopWinner = 0;

	/**
	 * Target Top Winner Constructor takes in the necessary information to decide
	 * action
	 * 
	 * @param players (list of players in the current BlackJack game)
	 * @param netWins (list of net wins of all players)
	 */
	public TargetTopWinner(List<Player> players, List<Integer> netWins) {
		this.players = players;
		this.netWins = netWins;
	}

	/**
	 * Dealers action is decided by targeting the top winner (highest net wins)
	 * hence does the appropriate action that will allow dealer to win
	 * 
	 * @param hand (hand of the dealer to access key methods)
	 */
	@Override
	public Action decideAction(Hand hand) {

		// finds the top winner i.e has most net wins
		getTopWinner();

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

	/**
	 * Finds the player with the highest net wins which is the top winner
	 */
	private void getTopWinner() {
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
	}

}
