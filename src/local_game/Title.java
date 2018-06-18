package local_game;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * The purpose of this class is
 * only to set up the GUI that
 * serves as the title of the
 * JavaFX GUI
 * @author Alex Horejsi
 */
class Title implements Runnable {
	private static HBox title;
	
	/**
	 * Returns the {@code HBox} representing the title
	 * @return The {@code HBox} representing the title
	 */
	public static HBox getTitle() {
		return Title.title;
	}
	
	@Override
	public void run() {
		HBox box = new HBox();
		Label label = new Label("Sudoku");
		label.getStyleClass().addAll("titleText", "centered", "whiteBack");
		box.getStyleClass().addAll("whiteBack", "centered", "bordered");
		box.getChildren().add(label);
		
		Title.title = box;
	}
}