package local_game;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

class Title implements Runnable {
	private static HBox title = new HBox();
	
	public static HBox getTitle() {
		return Title.title;
	}
	
	@Override
	public void run() {
		HBox box = new HBox();
		Label label = new Label("Sudoku");
		label.setAlignment(Pos.CENTER);
		label.setTextFill(Color.ROYALBLUE);
		box.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
		box.setAlignment(Pos.CENTER);
		box.getChildren().add(label);
		
		Title.title = box;
	}
}