package local_game;

import java.util.Objects;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

abstract class GridPaneCreator implements Runnable {
	protected GridPane gp;
	protected TextField[][] cells;
	protected GUIPuzzle puzzle;
	private static Background back = new Background(new BackgroundFill(Color.BLACK, null, null));
	
	public GridPaneCreator(GUIPuzzle puzzle) {
		this.puzzle = Objects.requireNonNull(puzzle);
	}
	
	public GridPane getGridPane() throws IllegalStateException {
		if (this.gp == null)
			throw new IllegalStateException();
		return this.gp;
	}
	
	public TextField[][] getCells() {
		return this.cells;
	}
	
	public void create(int dimensions) {
		GridPane gp = new GridPane();
		TextField[][] cells = new TextField[dimensions][dimensions];
		gp.setAlignment(Pos.CENTER);
		gp.setHgap(1);
		gp.setVgap(1);
		gp.setBackground(GridPaneCreator.back);
		
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
		tf.setAlignment(Pos.CENTER);
		tf.setMaxSize(30, 30);
		tf.setMinSize(30, 30);
		
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