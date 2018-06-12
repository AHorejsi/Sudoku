package local_game;

import java.util.Objects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

abstract class GridPaneCreator implements Runnable {
	protected GridPane gp;
	protected TextField[][] cells;
	protected GUIPuzzle puzzle;
	
	public GridPaneCreator(GUIPuzzle puzzle) throws NullPointerException {
		this.puzzle = Objects.requireNonNull(puzzle);
	}
	
	public GridPane getGridPane() throws IllegalStateException {
		if (this.gp == null)
			throw new IllegalStateException();
		return this.gp;
	}
	
	public TextField[][] getCells() throws IllegalStateException {
		if (this.cells == null)
			throw new IllegalStateException();
		return this.cells;
	}
	
	public void create(int dimensions) {
		GridPane gp = new GridPane();
		TextField[][] cells = new TextField[dimensions][dimensions];
		gp.setHgap(1);
		gp.setVgap(1);
		gp.getStyleClass().addAll("blackBack", "centered");
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				TextField tf = this.createTextField();
				tf.getStyleClass().add("centered");
				cells[i][j] = tf;
				gp.add(tf, j, i);
			}
		}
		
		this.gp = gp;
		this.cells = cells;
	}
	
	private TextField createTextField() {
		TextField tf = new TextField();
		tf.setMaxSize(30, 30);
		tf.setMinSize(30, 30);
		tf.getStyleClass().add("centered");
		
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