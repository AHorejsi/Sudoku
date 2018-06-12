package local_game;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

class SuccessScreen implements Runnable {
	private static StackPane screen;
	
	public static StackPane getSuccessScreen() {
		return SuccessScreen.screen;
	}
	
	@Override
	public void run() {
		StackPane sp = new StackPane();
		sp.getStyleClass().addAll("greenBack", "centered");
		
		Label label = new Label("Success!");
		sp.getChildren().add(label);
		SuccessScreen.screen = sp;
	}
}