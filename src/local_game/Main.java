package local_game;

import java.util.Collection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudoku_game.FlipBoxMixer;
import sudoku_game.FlipMixer;
import sudoku_game.Mixer;
import sudoku_game.RotateMixer;
import sudoku_game.SwapMixer;

/**
 * Starting point of 
 * local Sudoku game
 * @author Alex Horejsi
 */
public class Main extends Application {
	/**
	 * Empty public 
	 * constructor
	 */
	public Main() {}
	
	/**
	 * Main method
	 * to initiate
	 * program
	 * @param args Unused
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		GUIPuzzle gui = new SimpleGUIPuzzle();
		Collection<Mixer> mixers = gui.getMixers();
		mixers.add(RotateMixer.getInstance());
		mixers.add(FlipMixer.getInstance());
		mixers.add(FlipBoxMixer.getInstance());
		mixers.add(SwapMixer.getInstance());
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(new Scene(gui));
		primaryStage.show();
	}
}