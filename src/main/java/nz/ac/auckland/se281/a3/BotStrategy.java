package nz.ac.auckland.se281.a3;

import nz.ac.auckland.se281.a3.Participant.Action;

// three strategies will implement this interface that decides action and bet
public interface BotStrategy {

	public Action decideAction(Hand hand);

	public int makeABet();
}