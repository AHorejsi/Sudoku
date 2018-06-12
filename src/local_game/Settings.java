package local_game;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

class Settings extends Pane implements Runnable {
	private int dimensions = 9;
	private String difficulty = "Medium";
	
	Settings() {}
	
	public int dimensions() {
		return this.dimensions;
	}
	
	@Override
	public void run() {
		GridPane gp = new GridPane();
		gp.getStyleClass().addAll("grayBack", "centered", "settingsScreen");
		
		Label dimensionsLabel = new Label("Dimensions: ");
		Label difficultyLabel = new Label("Difficulty: ");
		this.styleLabels(dimensionsLabel, difficultyLabel);
		
		ComboBox<Integer> dimensionsDropDown = new ComboBox<Integer>();
		ComboBox<String> difficultyDropDown = new ComboBox<String>();
		
		dimensionsDropDown.getItems().addAll(9, 16);
		difficultyDropDown.getItems().addAll("Basic", "Easy", "Medium", "Hard", "Insane");
		
		dimensionsDropDown.setSelectionModel(ArrayDimensionSelectionModel.getInstance());
		difficultyDropDown.setSelectionModel(ArrayDifficultySelectionModel.getInstance());
		
		gp.add(dimensionsLabel, 0, 0);
		gp.add(dimensionsDropDown, 1, 0);
		gp.add(difficultyLabel, 0, 1);
		gp.add(difficultyDropDown, 1, 1);
		
		Button leave = new Button("Exit");
		gp.add(leave, 0, 2);
		this.addEventHandlersToComboBoxes(leave, dimensionsDropDown, difficultyDropDown);
		
		this.getChildren().add(gp);
	}
	
	private void styleLabels(Label... labels) {
		for (Label label : labels)
			label.getStyleClass().addAll("centered", "settingsLabel");
	}
	
	private void addEventHandlersToComboBoxes(Button button, ComboBox<?>... comboBoxes) {
		for (ComboBox<?> comboBox : comboBoxes) {
			comboBox.setOnAction(ev -> {
				SelectionModel<?> model = comboBox.getSelectionModel();
				
				if (model instanceof DimensionSelectionModel)
					this.dimensions = (int)model.getSelectedItem();
				else
					this.difficulty = (String)model.getSelectedItem();
			});
		}
	}
	
	@Override
	public String toString() {
		return this.dimensions + "x" + this.dimensions + " " + this.difficulty;
	}
}