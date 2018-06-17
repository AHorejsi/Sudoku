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
	
	public void create(int dimensions) {
		GridPane gp = new GridPane();
		TextField[][] cells = new TextField[dimensions][dimensions];
		gp.getStyleClass().addAll("blackBack", "centered", "gridPaneGaps", "bordered");
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
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
		tf.setMaxSize(60, 60);
		tf.setMinSize(60, 60);
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