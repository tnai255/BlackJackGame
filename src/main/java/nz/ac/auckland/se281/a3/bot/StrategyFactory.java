package nz.ac.auckland.se281.a3.bot;

public class StrategyFactory {

	// decides which instance strategy to create based on input string given by user
	public static BotStrategy chooseStrategy(String chosenStrategy) {
		switch (chosenStrategy) {
		// if input R it creates random strategy
		case "R":
			return new RandomStrategy();
		// if inputs LR it creates low risk strategy
		case "LR":
			return new LowRiskStrategy();
		// if inputs HR it creates high risk strategy
		case "HR":
			return new HighRiskStrategy();
		default:
			// if none of those inputs wrong type has been chosen
			System.err.println("Wrong strategy type chosen");
			System.exit(0);
		}
		return null;
	}

}
