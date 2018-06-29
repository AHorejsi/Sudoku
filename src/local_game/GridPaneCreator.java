package local_game;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

abstract class GridPaneCreator implements Runnable {
	protected GridPane gp;
	protected TextField[][] cells;
	protected int dimensions;
	protected int sizeOfTextField;
	
	GridPaneCreator(int dimensions, int sizeOfTextField) {
		this.dimensions = dimensions;
		this.sizeOfTextField = sizeOfTextField;
	}
	
	public GridPane getGridPane() {
		return this.gp;
	}
	
	public TextField[][] getTextFields() {
		return this.cells;
	}
	
	public int getDimensions() {
		return this.dimensions;
	}
	
	@Override
	public void run() {
		GridPane gp = new GridPane();
		TextField[][] cells = new TextField[dimensions][dimensions];
		gp.getStyleClass().addAll("blackBack", "centered", "gridPaneGaps", "bordered");
		
		for (int i = 0 ; i < this.dimensions ; i++) {
			for (int j = 0 ; j < this.dimensions ; j++) {
				TextField tf = this.createTextField();
				cells[i][j] = tf;
				gp.add(tf, j, i);
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
				if (newValue.length() > 1)
					tf.setText(oldValue);
			}
		});
		
		return tf;
	}
}