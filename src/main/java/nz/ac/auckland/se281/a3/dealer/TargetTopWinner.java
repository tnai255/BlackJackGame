package nz.ac.auckland.se281.a3.dealer;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetTopWinner implements DealerStrategy {
	private List<Player> players;
	private List<Integer> roundsWon = new ArrayList<>();
	private List<Integer> roundsLost = new ArrayList<>();
	private int indexOfTopWinner = 0;

	public TargetTopWinner(List<Player> players, List<Integer> roundsWon, List<Integer> roundsLost) {
		this.players = players;
		this.roundsWon = roundsWon;
		this.roundsLost = roundsLost;
	}

	@Override
	public Action decideAction(Hand hand) {

		int netWins = roundsWon.get(0) - roundsLost.get(0);

		for (int i = 1; i < 3; i++) {
			if ((roundsWon.get(i) - roundsLost.get(i)) > netWins) {
				netWins = roundsWon.get(i) - roundsLost.get(i);
				indexOfTopWinner = i;
			}
		}

		if (players.get(indexOfTopWinner).getHand().isBlackJack() && !hand.isBlackJack()) {
			if (hand.getScore() > 16) {
				return Action.HOLD;
			} else {
				return Action.HIT;
			}
		}

		if (players.get(indexOfTopWinner).getHand().getScore() <= hand.getScore()
				|| players.get(indexOfTopWinner).getHand().isBust()) {
			return Action.HOLD;
		}

		return Action.HIT;
	}

}
