package local_game;

import javafx.scene.layout.StackPane;

/**
 * Creates a four-by-four
 * {@code GridPane}
 * @author Alex Horejsi
 */
class GridPane4x4Creator extends GridPaneCreator {
	/**
	 * Defines the dimensions
	 * and size of the text
	 * fields
	 */
	GridPane4x4Creator() {
		super(4, 60);
	}
	
	@Override
	public StackPane createStackPane(int row, int col) {
		StackPane sp = new StackPane();
		
		if (row == 2) {
			if (col == 2)
				sp.getStyleClass().add("topLeftPadding");
			else
				sp.getStyleClass().add("topPadding");
		}
		else if (col == 2)
			sp.getStyleClass().add("leftPadding");
		
		return sp;
	}
}