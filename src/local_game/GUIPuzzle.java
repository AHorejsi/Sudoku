package local_game;

import java.util.Collection;
import java.util.Random;
import javafx.scene.layout.Pane;
import sudoku_game.Mixer;

public abstract class GUIPuzzle extends Pane {	
	public abstract void generatePuzzle();
	
	public abstract void setRandomNumberGenerator(Random rng);
	
	public abstract void addMixer(Mixer mixer);
	
	public abstract void removeMixer(Mixer mixer);
	
	public abstract Collection<Mixer> getMixers();
}