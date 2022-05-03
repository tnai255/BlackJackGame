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
		String botStrategyString = getBotStrategy(); // UNCOMMENT THIS
		// create and set Bots strategy here
		BotStrategy chosenStrategy = StrategyFactory.chooseStrategy(botStrategyString);
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

			// CHECK THIS THOROUGHLY
			if (players.get(i).getHand().isBlackJack() && !dealer.getHand().isBlackJack()) {
				result = "won";
			}

			if ((players.get(i).getHand().getScore() > dealer.getHand().getScore())
					&& !players.get(i).getHand().isBust()) {
				result = "won";
			}

			if (!players.get(i).getHand().isBust() && dealer.getHand().isBust()) {
				result = "won";
			}

			if (result == "won") {
				roundsWon[i]++;
			} else {
				roundsLost[i]++;
			}

			System.out.println("Round " + round + ": " + players.get(i).getName() + " " + result + " "
					+ players.get(i).getHand().getBet() + " chips");

		}

		calculateNetWins(roundsWon, roundsLost);
		decideIfChangeStrategy();
	}

	private void calculateNetWins(int[] roundsWon, int[] roundsLost) {

		netWins.clear();

		for (int i = 0; i < 3; i++) {
			netWins.add(roundsWon[i] - roundsLost[i]);
		}
	}

	private void decideIfChangeStrategy() {

		boolean changeStrategyToTopWinner = false;

		for (int i = 0; i < 3; i++) {
			if (netWins.get(i) > 1) {
				changeStrategyToTopWinner = true;
			}
		}

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

		int i = 0;

		for (Player player : players) {
			System.out
					.println(player.getName() + " won " + roundsWon[i] + " times and lost " + roundsLost[i] + " times");
			i++;
		}
	}

}
