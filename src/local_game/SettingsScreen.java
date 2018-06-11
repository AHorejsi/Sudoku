package local_game;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

class SettingsScreen implements Runnable {
	private static GridPane screen;
	
	public static GridPane getSettingsScreen() {
		return SettingsScreen.screen;
	}
	
	@Override
	public void run() {
		GridPane gp = new GridPane();
		gp.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		gp.setVgap(20);
		gp.setHgap(5);
		gp.setAlignment(Pos.CENTER);
		
		Label dimensionsLabel = new Label("Dimensions");
		Label difficultyLabel = new Label("Difficulty");
		this.styleLabels(dimensionsLabel, difficultyLabel);
		
		ComboBox<Integer> dimensionsDropDown = new ComboBox<Integer>();
		ComboBox<String> difficultyDropDown = new ComboBox<String>();
		
		List<Integer> list1 = dimensionsDropDown.getItems();
		list1.add(9);
		list1.add(16);
		
		List<String> list2 = difficultyDropDown.getItems();
		list2.add("BASIC");
		list2.add("EASY");
		list2.add("MEDIUM");
		list2.add("HARD");
		list2.add("INSANE");
		
		gp.add(dimensionsLabel, 0, 0);
		gp.add(dimensionsDropDown, 1, 0);
		gp.add(difficultyLabel, 0, 1);
		gp.add(difficultyDropDown, 1, 1);
		
		Button leave = new Button("Exit");
		gp.add(leave, 2, 0);
		
		SettingsScreen.screen = gp;
	}
	
	private void styleLabels(Label... labels) {
		for (Label label : labels) {
			label.setAlignment(Pos.CENTER);
			label.setTextFill(Color.YELLOW);
			label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
		}
	}
}