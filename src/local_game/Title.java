package local_game;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

class Title implements Runnable {
	private static HBox title = new HBox();
	
	public static HBox getTitle() {
		return Title.title;
	}
	
	@Override
	public void run() {
		HBox box = new HBox();
		Label label = new Label("Sudoku");
		label.getStyleClass().addAll("titleText", "centered", "whiteBack");
		box.getStyleClass().addAll("whiteBack", "centered");
		box.getChildren().add(label);
		
		Title.title = box;
	}
}