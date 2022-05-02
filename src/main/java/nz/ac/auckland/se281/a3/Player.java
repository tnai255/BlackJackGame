package nz.ac.auckland.se281.a3;

/**
 * 
 * You can (and should) add new fields and/or methods
 *
 */
public abstract class Player extends Participant {

	public Player(String name) {
		super(name);
	}

	public abstract int makeABet();

}
