package local_game;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * Creates a {@code GridPane} with
 * instances of {@code TextField} 
 * that will serve as part of the GUI
 * for the Sudoku game
 * @author Alex Horejsi
 */
public abstract class GridPaneCreator implements Runnable {
	protected GridPane gp;
	protected TextField[][] cells;
	protected int dimensions;
	protected int sizeOfTextField;
	
	/**
	 * Defines the dimensions and
	 * size of the text fields for
	 * the {@code GridPane}
	 * @param dimensions The dimensions
	 * of this {@code GridPane}
	 * @param sizeOfTextField The size
	 * of the {@code TextField}s
	 */
	GridPaneCreator(int dimensions, int sizeOfTextField) {
		this.dimensions = dimensions;
		this.sizeOfTextField = sizeOfTextField;
	}
	
	/**
	 * Returns the {@code GridPane}
	 * @return The {@code GridPane}
	 */
	public GridPane getGridPane() {
		return this.gp;
	}
	
	/**
	 * Returns a 2D array of {@code TextField} 
	 * @return A 2D array of {@code TextField}
	 */
	public TextField[][] getTextFields() {
		return this.cells;
	}
	
	/**
	 * Returns the dimensions of the {@code GridPane}
	 * @return The dimensions of the {@code GridPane}
	 */
	public int getDimensions() {
		return this.dimensions;
	}
	
	/**
	 * Creates a {@code StackPane} with the
	 * appropriate padding based on the way
	 * the Sudoku puzzle's boxes are
	 * separated
	 * @param row The row of the cell
	 * @param col The column of the cell
	 * @return A {@code StackPane} with the
	 * appropriate padding
	 */
	public abstract StackPane createStackPane(int row, int col);
	
	@Override
	public void run() {
		GridPane gp = new GridPane();
		TextField[][] cells = new TextField[this.dimensions][this.dimensions];
		gp.getStyleClass().addAll("blackBack", "centered", "gridPaneGaps", "bordered");
		
		for (int i = 0 ; i < this.dimensions ; i++) {
			for (int j = 0 ; j < this.dimensions ; j++) {
				TextField tf = this.createTextField();	
				StackPane sp = this.createStackPane(i, j);
				sp.getChildren().add(tf);
				
				cells[i][j] = tf;
				gp.add(sp, j, i);
			}
		}
		
		this.gp = gp;
		this.cells = cells;
	}
	
	private TextField createTextField() {
		TextField tf = new TextField();
		tf.setMaxSize(this.sizeOfTextField, this.sizeOfTextField);
		tf.setMinSize(this.sizeOfTextField, this.sizeOfTextField);
		tf.getStyleClass().addAll("centered", "textField");
		
		tf.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> obs, String oldValue, String newValue) {
				if (newValue != null) {
					if (newValue.length() > 1)
						tf.setText(oldValue);
					if (Character.isLetter(tf.getText().charAt(0)))
						tf.setText(tf.getText().toUpperCase());
				}
			}
		});
		
		return tf;
	}
}