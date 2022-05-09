package nz.ac.auckland.se281.a3;

import nz.ac.auckland.se281.a3.dealer.Dealer;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {
	private Dealer dealer;
	private int roundsWon = 0;
	private int roundsLost = 0;

	public Player(String name) {
		super(name);
	}

	public abstract int makeABet();

	/**
	 * Checks if player has won based on the hand of the player
	 * 
	 * @param dealer (takes in the dealer to access dealers hand)
	 * @return string of whether player "won" or "lost" to easily input in the
	 *         results output
	 */
	public String didPlayerWin(Dealer dealer) {

		this.dealer = dealer;
		// changing the result for the players if they win if these specific scenarios
		// are met

		// checking if player has blackjack and dealer doesn't then player wins
		if (this.getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
			return "won";
		}

		// checking if player has a bigger score compared to the dealer and is not a
		// bust
		if ((this.getHand().getScore() > dealer.getHand().getScore()) && !this.getHand().isBust()) {
			return "won";
		}

		// checking if player is not a bust and dealer is
		if (!this.getHand().isBust() && dealer.getHand().isBust()) {
			return "won";
		}

		return "lost";
	}

	/**
	 * increments roundsWon or roundsLost depending on if the player won or lost
	 * respectively
	 */
	public void setRoundsWonAndLost() {
		if (didPlayerWin(dealer) == "won") {
			roundsWon++;
		} else {
			roundsLost++;
		}
	}

	public int getRoundsWon() {
		return roundsWon;
	}

	public int getRoundsLost() {
		return roundsLost;
	}
}
