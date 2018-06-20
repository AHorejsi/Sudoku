package local_game;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

abstract class GridPaneCreator implements Runnable {
	protected GridPane gp;
	protected TextField[][] cells;
	
	GridPaneCreator() {}
	
	public GridPane getGridPane() {
		return this.gp;
	}
	
	public TextField[][] getCells() {
		return this.cells;
	}
	
	public void create(Integer dimensions, Integer sizeOfTextField) {
		GridPane gp = new GridPane();
		TextField[][] cells = new TextField[dimensions][dimensions];
		gp.getStyleClass().addAll("blackBack", "centered", "gridPaneGaps", "bordered");
		
		for (Integer i = 0 ; i < dimensions ; i++) {
			for (Integer j = 0 ; j < dimensions ; j++) {
				TextField tf = this.createTextField(sizeOfTextField);
				cells[i][j] = tf;
				gp.add(tf, j, i);
			}
		}
		
		this.gp = gp;
		this.cells = cells;
	}
	
	private TextField createTextField(Integer sizeOfTextField) {
		TextField tf = new TextField();
		tf.setMaxSize(sizeOfTextField, sizeOfTextField);
		tf.setMinSize(sizeOfTextField, sizeOfTextField);
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