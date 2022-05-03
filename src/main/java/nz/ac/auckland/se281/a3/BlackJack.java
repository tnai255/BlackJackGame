package nz.ac.auckland.se281.a3;

import java.util.ArrayList;
import java.util.List;

import nz.ac.auckland.se281.a3.bot.Bot;
import nz.ac.auckland.se281.a3.bot.BotStrategy;
import nz.ac.auckland.se281.a3.bot.StrategyFactory;
import nz.ac.auckland.se281.a3.dealer.Dealer;
import nz.ac.auckland.se281.a3.dealer.DealerStrategy;
import nz.ac.auckland.se281.a3.dealer.TargetHighestBidder;
import nz.ac.auckland.se281.a3.dealer.TargetTopWinner;

/**
 * Unless it is specified in the JavaDoc, you cannot change any methods.
 * 
 * You can add new methods and/or new instance fields
 */
public class BlackJack {

	private List<Player> players;
	private Dealer dealer;
	private Deck deck;
	// declaring roundsWon, roundsLost and netWins arrays
	private int[] roundsWon = { 0, 0, 0 };
	private int[] roundsLost = { 0, 0, 0 };
	private List<Integer> netWins = new ArrayList<>();

	public BlackJack(Deck deck) {
		this.deck = deck;
		players = new ArrayList<>();
		players.add(new Human("Player1")); // add the Human player first.
	}

	/**
	 * This constructor is for testing reasons
	 * 
	 * @param cards
	 */
	protected BlackJack(Card... cards) {
		this(new Deck(cards));

	}

	public BlackJack() {
		this(new Deck());
	}

	public List<Player> getPlayers() {
		return players;
	}

	private String getBotStrategy() {
		System.out.println("Choose Bot strategy: random (R) - low risk (LR) - high risk (HR)");
		String result = Main.scanner.next();
		while (!result.equals("R") && !result.equals("LR") && !result.equals("HR") && !result.equals("A")) {
			System.out.println("please type \"R\", \"LR\",  \"HR\"");
			result = Main.scanner.next();
		}
		return result;
	}

	// do not change this method
	public void start() {
		initBots();
		initDealer();
		String res;
		int round = 0;
		do {
			round++;
			for (Participant p : players) {
				p.play(deck, round);
			}
			dealer.play(deck, round);
			printAndUpdateResults(round); // after each game print result and update scoreboard
			System.out.println("Do you want to play again?");
			res = Main.scanner.next();
			while (!res.equals("yes") && !res.equals("no")) {
				System.out.println("please type either \"yes\" or \"no\"");
				res = Main.scanner.next();
			}
		} while (res.equals("yes"));
		printGameStatistics(); // when the user terminates the game print the statistics
	}

	/**
	 * TODO This method initializes the Bots, you should change this method for
	 * Task1
	 */
	protected void initBots() {
		String botStrategyString = getBotStrategy();
		// create chosen strategy from factory pattern based on input string
		BotStrategy chosenStrategy = StrategyFactory.chooseStrategy(botStrategyString);
		// creates the two bots to take in and use that strategy
		Bot bot1 = new Bot("Bot1", chosenStrategy);
		Bot bot2 = new Bot("Bot2", chosenStrategy);
		players.add(bot1);
		players.add(bot2);
	}

	/**
	 * TODO This method initializes the Dealer, you should change this method for
	 * Task2
	 */
	protected void initDealer() {
		// set the initial strategy using the Strategy pattern
		DealerStrategy strategy = new TargetHighestBidder(players);
		dealer = new Dealer("Dealer", strategy);

		// sets roundsWon and lost arrays back to zero
		for (int i = 0; i < 3; i++) {
			roundsWon[i] = 0;
			roundsLost[i] = 0;
		}
	}

	/**
	 * TODO This method prints and updates the results (wins and losses) you should
	 * change this method for Task 2 and Task 3
	 */
	protected void printAndUpdateResults(int round) {

		for (int i = 0; i < 3; i++) {

			String result = "lost";

			// changing the result for the players if they win if these specific scenarios
			// are met

			// checking if player has blackjack and dealer doesn't player wins
			if (players.get(i).getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
				result = "won";
			}
			// checking if player has a bigger score compared to the dealer and is not a
			// bust
			if ((players.get(i).getHand().getScore() > dealer.getHand().getScore())
					&& !players.get(i).getHand().isBust()) {
				result = "won";
			}

			// checking if player is not a bust and dealer is
			if (!players.get(i).getHand().isBust() && dealer.getHand().isBust()) {
				result = "won";
			}

			// creates an array of the wins and losts for the all players
			if (result == "won") {
				roundsWon[i]++;
			} else {
				roundsLost[i]++;
			}

			// prints out the round, result and the bet of each player
			System.out.println("Round " + round + ": " + players.get(i).getName() + " " + result + " "
					+ players.get(i).getHand().getBet() + " chips");

		}

		// calculates the net wins based on the rounds won and rounds lost
		calculateNetWins(roundsWon, roundsLost);
		// decides if dealer should change strategy
		decideIfChangeStrategy();
	}

	/**
	 * Calculates Net Wins based on the roundsWon and roundsLost array
	 * 
	 * @param roundsWon
	 * @param roundsLost
	 */
	private void calculateNetWins(int[] roundsWon, int[] roundsLost) {

		// clears array before creating new netWins
		netWins.clear();

		// calculates net wins for each player
		for (int i = 0; i < 3; i++) {
			netWins.add(roundsWon[i] - roundsLost[i]);
		}
	}

	/**
	 * Decide if strategy needs to be changed
	 */
	private void decideIfChangeStrategy() {

		// initialises change strategy to false
		boolean changeStrategyToTopWinner = false;

		// loops through the net wins and checks if theres a net win 2 or more
		for (int i = 0; i < 3; i++) {
			if (netWins.get(i) > 1) {
				// changes it to true
				changeStrategyToTopWinner = true;
			}
		}

		// if change strategy to top winner is true then dealer changes strategy to top
		// winner otherwise changes to top bidder
		if (changeStrategyToTopWinner) {
			dealer.setStrategy(new TargetTopWinner(players, netWins));
		} else {
			dealer.setStrategy(new TargetHighestBidder(players));
		}

	}

	/**
	 * TODO This method should print the statistic of the game when it ends
	 */
	protected void printGameStatistics() {

		// sets index to 0
		int i = 0;

		// loops through all the players and prints out their results
		for (Player player : players) {
			System.out
					.println(player.getName() + " won " + roundsWon[i] + " times and lost " + roundsLost[i] + " times");
			// increments index to change to other player
			i++;
		}
	}

}
