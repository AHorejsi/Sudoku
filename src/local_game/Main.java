package local_game;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudoku_game.FlipBoxMixer;
import sudoku_game.FlipMixer;
import sudoku_game.LocalFactory;
import sudoku_game.Mixer;
import sudoku_game.RotateMixer;
import sudoku_game.SwapMixer;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Settings settings = new Settings(9, "medium");
		Collection<Mixer> mixers = new LinkedList<Mixer>();
		Collections.addAll(mixers, RotateMixer.getInstance(), FlipMixer.getInstance(), FlipBoxMixer.getInstance(), SwapMixer.getInstance());
		GUIPuzzle gui = new SimpleGUIPuzzle(LocalFactory.getInstance(), mixers, settings, null);
		primaryStage.setScene(new Scene(gui));
		primaryStage.show();
	}
}