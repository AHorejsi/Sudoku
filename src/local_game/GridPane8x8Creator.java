package local_game;

import javafx.scene.layout.StackPane;

/**
 * Creates an eight-by-eight
 * {@code GridPane}
 * @author Alex Horejsi
 */
public class GridPane8x8Creator extends GridPaneCreator {
	/**
	 * Defines the dimensions
	 * and size of the text
	 * fields
	 */
	GridPane8x8Creator() {
		super(8, 60);
	}
	
	@Override
	public StackPane createStackPane(int row, int col) {
		StackPane sp = new StackPane();
		
		if (row == 2 || row == 4 || row == 6) {
			if (col == 4)
				sp.getStyleClass().add("topLeftPadding");
			else
				sp.getStyleClass().add("topPadding");
		}
		else if (col == 4)
			sp.getStyleClass().add("leftPadding");
		
		return sp;
	}
}