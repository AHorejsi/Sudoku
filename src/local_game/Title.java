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
		label.setStyle("-fx-font-family: Verdana; -fx-font-weight: bolder; -fx-font-size: 50px; "
					   + "-fx-text-fill: #4169E1; -fx-alignment: center; -fx-background-color: white");
		box.setStyle("-fx-background-color: white; -fx-alignment: center");
		box.getChildren().add(label);
		
		Title.title = box;
	}
}