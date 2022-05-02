package nz.ac.auckland.se281.a3;

public class StrategyFactory {

	public static BotStrategy chooseStrategy(String chosenStrategy) {
		switch (chosenStrategy) {
		case "R":
			return new RandomStrategy();
		case "LR":
			return new LowRiskStrategy();
//		case "HR":
//			return new HighRiskStrategy();
		default:
			System.err.println("Wrong strategy type chosen");
			System.exit(0);
		}
		return null;
	}

}
