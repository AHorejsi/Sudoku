package local_game;

import javafx.scene.layout.StackPane;

/**
 * Creates a nine-by-nine
 * {@code GridPane}
 * @author Alex Horejsi
 */
public class GridPane9x9Creator extends GridPaneCreator {
	/**
	 * Defines the dimensions
	 * and size of the text
	 * fields
	 */
	GridPane9x9Creator() {
		super(9, 60);
	}
	
	@Override
	public StackPane createStackPane(int row, int col) {
		StackPane sp = new StackPane();
		
		if (row == 3 || row == 6) {
			if (col == 3 || col == 6)
				sp.getStyleClass().add("topLeftPadding");
			else
				sp.getStyleClass().add("topPadding");
		}
		else if (col == 3 || col == 6)
			sp.getStyleClass().add("leftPadding");
		
		return sp;
	}
}