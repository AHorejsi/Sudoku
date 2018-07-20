package local_game;

import java.util.Collection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudoku_game.Mixer;
import sudoku_game.SimpleMixerFactory;

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
		Collection<Mixer> mixers = SimpleMixerFactory.getInstance().getMixers("linkedlist rotate flip flipbox swap");
		GUIPuzzle gui = new SimpleGUIPuzzle();
		gui.getMixers().addAll(mixers);
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(new Scene(gui));
		primaryStage.show();
	}
}