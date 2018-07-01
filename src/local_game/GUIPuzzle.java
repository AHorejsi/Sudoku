package local_game;

import java.util.Collection;
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
	 * Returns this {@code GUIPuzzle}'s
	 * collection of mixers
	 * @return The collection of mixers
	 * associated with this {@code GUIPuzzle}
	 */
	public abstract Collection<Mixer> getMixers();
}