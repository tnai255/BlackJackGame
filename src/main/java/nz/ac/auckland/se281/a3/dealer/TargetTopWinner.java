package nz.ac.auckland.se281.a3.dealer;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.Hand;
import nz.ac.auckland.se281.a3.Participant.Action;
import nz.ac.auckland.se281.a3.Player;

public class TargetTopWinner implements DealerStrategy {
	private List<Player> players;
	private List<Integer> netWins = new ArrayList<>();
	private int indexOfTopWinner = 0;

	public TargetTopWinner(List<Player> players, List<Integer> netWins) {
		this.players = players;
		this.netWins = netWins;
	}

	@Override
	public Action decideAction(Hand hand) {

		int topWinner = netWins.get(0);

		for (int i = 1; i < 3; i++) {
			if (netWins.get(i) > topWinner) {
				topWinner = netWins.get(i);
				indexOfTopWinner = i;
			}
		}

		System.out.println("TopWinner: " + indexOfTopWinner);

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
