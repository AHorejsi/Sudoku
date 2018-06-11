package local_game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

class SuccessScreen implements Runnable {
	private static StackPane screen;
	
	public static StackPane getSuccessScreen() {
		return SuccessScreen.screen;
	}
	
	@Override
	public void run() {
		StackPane sp = new StackPane();
		sp.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
		sp.setAlignment(Pos.CENTER);
		
		Label label = new Label("Success!");
		sp.getChildren().add(label);
		SuccessScreen.screen = sp;
	}
}