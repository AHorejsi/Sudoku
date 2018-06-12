package local_game;

import java.util.Collection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sudoku_game.LocalFactory;
import sudoku_game.Mixer;
import sudoku_game.SimpleMixerFactory;

public class Main extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		Collection<Mixer> mixers = SimpleMixerFactory.getInstance().getFactory("linkedlist rotate flip flipbox swap");
		GUIPuzzle gui = new SimpleGUIPuzzle(LocalFactory.getInstance(), mixers, null);
		Scene sc = new Scene(gui);
		primaryStage.setScene(sc);
		primaryStage.show();
	}
}