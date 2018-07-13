package local_game;

import javafx.scene.layout.StackPane;

/**
 * Creates a six-by-six
 * {@code GridPane}
 * @author Alex Horejsi
 */
class GridPane6x6Creator extends GridPaneCreator {
	/**
	 * Defines the dimensions
	 * and size of the text
	 * fields
	 */
	GridPane6x6Creator() {
		super(6, 60);
	}
	
	@Override
	public StackPane createStackPane(int row, int col) {
		StackPane sp = new StackPane();
		
		if (row == 2 || row == 4) {
			if (col == 3)
				sp.getStyleClass().add("topLeftPadding");
			else
				sp.getStyleClass().add("topPadding");
		}
		else if (col == 3)
			sp.getStyleClass().add("leftPadding");
		
		return sp;
	}
}