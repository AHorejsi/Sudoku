package local_game;

import java.util.Collection;
import java.util.Random;
import javafx.scene.layout.StackPane;
import sudoku_game.Mixer;

/**
 * Instances of this class are meant
 * to act as a JavaFX GUI representation
 * of a Sudoku puzzle
 * @author Alex Horejsi
 */
public abstract class GUIPuzzle extends StackPane {
	/**
	 * Generates a Sudoku puzzle and constructs
	 * the GUI elements used to represent it
	 */
	public abstract void generatePuzzle();
	
	/**
	 * Swaps out the random 
	 * number generator that
	 * this {@code GUIPuzzle}
	 * will use
	 * @param rng The new 
	 * random number generator
	 * for this {@code GUIPuzzle}
	 */
	public abstract void setRandomNumberGenerator(Random rng);
	
	/**
	 * Adds a mixer to
	 * this {@code GUIPuzzle}'s
	 * collection of mixers
	 * @param mixer The mixer
	 * to be added
	 */
	public abstract void addMixer(Mixer mixer);
	
	/**
	 * Removes a mixer from
	 * this {@code GUIPuzzle}'s
	 * collection of mixers
	 * @param mixer The mixer to be removed
	 */
	public abstract void removeMixer(Mixer mixer);
	
	/**
	 * Returns this {@code GUIPuzzle}'s
	 * collection of mixers
	 * @return The collection of mixers
	 * associated with this {@code GUIPuzzle}
	 */
	public abstract Collection<Mixer> getMixers();
}