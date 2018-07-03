package local_game;

import java.util.Collection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
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
	 * Main method call
	 * to initiate
	 * program
	 * @param args Unused
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Collection<Mixer> mixers = SimpleMixerFactory.getInstance().getFactory("linkedlist rotate flip flipbox swap");
		GUIPuzzle gui = new SimpleGUIPuzzle(mixers);
		Scene sc = new Scene(gui);
		primaryStage.setTitle("Sudoku");
		primaryStage.setFullScreen(true);
		primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
		primaryStage.setScene(sc);
		primaryStage.show();
	}
}