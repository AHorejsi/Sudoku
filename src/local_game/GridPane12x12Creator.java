package local_game;

import javafx.scene.layout.StackPane;

/**
 * Creates a sixteen-by-sixteen
 * {@code GridPane}
 * @author Alex Horejsi
 */
class GridPane12x12Creator extends GridPaneCreator {
	/**
	 * Defines the dimensions
	 * and size of the text
	 * fields
	 */
	GridPane12x12Creator() {
		super(12, 40);
	}
	
	@Override
	public StackPane createStackPane(int row, int col) {
		StackPane sp = new StackPane();
		
		if (row == 3 || row == 6 || row == 9) {
			if (col == 4 || col == 8)
				sp.getStyleClass().add("topLeftPadding");
			else
				sp.getStyleClass().add("topPadding");
		}
		else if (col == 4 || col == 8)
			sp.getStyleClass().add("leftPadding");
		
		return sp;
	}
}