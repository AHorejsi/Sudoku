package local_game;

import javafx.scene.layout.StackPane;

/**
 * Creates a sixteen-by-sixteen
 * {@code GridPane}
 * @author Alex Horejsi
 */
public class GridPane16x16Creator extends GridPaneCreator {
	/**
	 * Defines the dimensions
	 * and size of the text
	 * fields
	 */
	GridPane16x16Creator() {
		super(16, 40);
	}
	
	@Override
	public StackPane createStackPane(int row, int col) {
		StackPane sp = new StackPane();
		
		if (row == 4 || row == 8 || row == 12) {
			if (col == 4 || col == 8 || col == 12)
				sp.getStyleClass().add("topLeftPadding");
			else
				sp.getStyleClass().add("topPadding");
		}
		else if (col == 4 || col == 8 || col == 12)
			sp.getStyleClass().add("leftPadding");
		
		return sp;
	}
}