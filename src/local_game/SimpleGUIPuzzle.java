package local_game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sudoku_game.LocalFactory;
import sudoku_game.Mixer;
import sudoku_game.Puzzle;
import sudoku_game.PuzzleFactory;

/**
 * Simple implementation of
 * {@code GUIPuzzle}
 * @author Alex Horejsi
 */
public class SimpleGUIPuzzle extends GUIPuzzle {
	private Puzzle puzzle;
	private Settings settings = new Settings();
	private List<Mixer> mixers = new LinkedList<Mixer>();
	private BorderPane bp = new BorderPane();
	private Map<Integer, GridPane> gridpanes = new HashMap<Integer, GridPane>();
	private Map<Integer, TextField[][]> textfields = new HashMap<Integer, TextField[][]>();
	private Button play = new Button("Play New Puzzle");
	private Button settingsButton = new Button("Settings");
	private Button returnToMainMenuButton = new Button("Return to Main Menu");
	private Button exit = new Button("Exit");
	private HBox options = new HBox();
	private HBox mainMenu = new HBox();
	private HBox title = new HBox();
	private StackPane leftSide = new StackPane();
	private StackPane rightSide = new StackPane();
	private StackPane mainImage = new StackPane();
	private StackPane successScreen = new StackPane();
	private static PuzzleFactory factory = LocalFactory.getInstance();
	
	/**
	 * Creates an instance
	 * of {@code SimpleGUIPuzzle}
	 */
	public SimpleGUIPuzzle() {
		this.getStylesheets().add("local_game/stylesheet.css");
		this.getStyleClass().addAll("centered", "blackBack");
		this.createGUIToUseLater();
		this.setUpBorderPane();
	}
	
	private void createGUIToUseLater() {
		GridPaneCreator gpc4x4 = new GridPane4x4Creator();
		GridPaneCreator gpc9x9 = new GridPane9x9Creator();
		GridPaneCreator gpc16x16 = new GridPane16x16Creator();
		
		Thread t1 = new Thread(new CreateSettingsButton());
		Thread t2 = new Thread(new CreateReturnToMainMenuButton());
		Thread t3 = new Thread(new CreateSubmitButton());
		Thread t4 = new Thread(new CreatePlayButton());
		Thread t5 = new Thread(new CreateTitle());
		Thread t6 = new Thread(new CreateExitButton());
		Thread t7 = new Thread(new CreateSideBar());
		Thread t8 = new Thread(new CreateImage());
		Thread t9 = new Thread(new CreateSuccessScreen());
		Thread t10 = new Thread(gpc4x4);
		Thread t12 = new Thread(gpc9x9);
		Thread t14 = new Thread(gpc16x16);
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		t12.start();
		t14.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
			t12.join();
			t14.join();
		} catch (InterruptedException ex) {
			throw new InternalError(ex);
		}
		
		this.insertIntoMaps(gpc4x4, gpc9x9, gpc16x16);
		this.setUpMainMenu();
	}
	
	private void insertIntoMaps(GridPaneCreator... gpcs) {
		int dimensions;
		
		for (GridPaneCreator gpc : gpcs) {
			dimensions = gpc.getDimensions();
			this.gridpanes.put(dimensions, gpc.getGridPane());
			this.textfields.put(dimensions, gpc.getTextFields());
		}
	}
	
	@Override
	public void generatePuzzle() {
		Puzzle puzzle = SimpleGUIPuzzle.factory.createPuzzle(this.settings.toString(), this.mixers);
		int dimensions = this.settings.dimensions();
		TextField[][] tfs = this.textfields.get(dimensions);
		GridPane gps = this.gridpanes.get(dimensions);
		this.puzzle = puzzle;
		char ch;
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				ch = puzzle.getValueAt(i, j);
				tfs[i][j].getStyleClass().retainAll("centered", "textField");
				
				if (ch != '\u0000') {
					tfs[i][j].setText(String.valueOf(ch));
					
					if (puzzle.editableCellAt(i, j)) {
						tfs[i][j].getStyleClass().add("whiteBack");
						tfs[i][j].setEditable(true);
					}
					else {
						tfs[i][j].getStyleClass().add("yellowTextField");
						tfs[i][j].setEditable(false);
					}
				}
				else {
					tfs[i][j].getStyleClass().add("whiteBack");
					tfs[i][j].setText(null);
					tfs[i][j].setEditable(true);
				}
			}
		}
		
		this.bp.setCenter(gps);
	}
	
	@Override
	public List<Mixer> getMixers() {
		return this.mixers;
	}
	
	private void setUpBorderPane() {
		BorderPane bp = this.bp;
		
		bp.setTop(this.title);
		bp.setCenter(this.mainImage);
		bp.setBottom(this.mainMenu);
		bp.setLeft(this.leftSide);
		bp.setRight(this.rightSide);
		bp.getStyleClass().add("centered");
		
		this.getChildren().add(bp);
	}
	
	private void setUpMainMenu() {
		HBox mainMenu = new HBox();
		mainMenu.getStyleClass().addAll("bordered", "centered", "whiteBack");
		mainMenu.getChildren().addAll(this.play, this.settingsButton, this.exit);
		
		this.mainMenu = mainMenu;
	}
	
	private class Settings extends StackPane {
		private int dimensions = 9;
		private String difficulty = "Medium";
		
		Settings() {
			GridPane gp = new GridPane();
			gp.getStyleClass().addAll("grayBack", "centered", "settingsScreen");
			
			Label dimensionsLabel = new Label("Dimensions: ");
			Label difficultyLabel = new Label("Difficulty: ");
			this.styleLabels(dimensionsLabel, difficultyLabel);
			
			ComboBox<Integer> dimensionsDropDown = new ComboBox<Integer>();
			ComboBox<String> difficultyDropDown = new ComboBox<String>();
			
			dimensionsDropDown.getItems().addAll(4, 9, 16);
			difficultyDropDown.getItems().addAll("Basic", "Easy", "Medium", "Hard", "Insane");
			
			dimensionsDropDown.setSelectionModel(ArrayDimensionSelectionModel.getInstance());
			difficultyDropDown.setSelectionModel(ArrayDifficultySelectionModel.getInstance());
			
			gp.add(dimensionsLabel, 0, 0);
			gp.add(dimensionsDropDown, 1, 0);
			gp.add(difficultyLabel, 0, 1);
			gp.add(difficultyDropDown, 1, 1);
			
			Button exit = new Button("Apply and Exit");
			gp.add(exit, 0, 2);
			
			for (Node node : gp.getChildren())
				node.getStyleClass().add("centered");
			
			this.addEventHandlersToComboBoxes(dimensionsDropDown, difficultyDropDown);
			this.getChildren().add(gp);
			this.getStyleClass().add("centered");
		}
		
		public int dimensions() {
			return this.dimensions;
		}
		
		public void setDimensions(int dimensions) {
			this.dimensions = dimensions;
		}
		
		public void setDifficulty(String difficulty) {
			this.difficulty = difficulty;
		}
		
		private void styleLabels(Label... labels) {
			for (Label label : labels)
				label.getStyleClass().addAll("centered", "settingsLabel");
		}
		
		private void addEventHandlersToComboBoxes(ComboBox<?>... comboBoxes) {
			for (ComboBox<?> comboBox : comboBoxes) {
				comboBox.setOnMouseClicked(ev -> {
					SelectionModel<?> model = comboBox.getSelectionModel();
					
					if (model instanceof DimensionSelectionModel) {
						Integer dim = (Integer)model.getSelectedItem();
						if (dim != null)
							this.dimensions = dim;
					}
					else {
						String diff = (String)model.getSelectedItem();
						if (diff != null)
							this.difficulty = (String)model.getSelectedItem();
					}
						
				});
			}
		}
		
		@Override
		public String toString() {
			return this.dimensions + "x" + this.dimensions + " " + this.difficulty;
		}
	}
	
	private class CreateSettingsButton implements Runnable {
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			Settings set = SimpleGUIPuzzle.this.settings;
			BorderPane bp = SimpleGUIPuzzle.this.bp;
			List<Node> children = ((Pane)set.getChildren().get(0)).getChildren();
			
			SimpleGUIPuzzle.this.settingsButton.setOnMouseClicked(ev -> {
				bp.setCenter(set);
				SimpleGUIPuzzle.this.leftSide.getChildren().clear();
				SimpleGUIPuzzle.this.rightSide.getChildren().clear();
			});
			
			children.get(4).setOnMouseClicked(ev -> {
				bp.setCenter(SimpleGUIPuzzle.this.mainImage);
				SelectionModel<Integer> model1 = ((ComboBox<Integer>)children.get(1)).getSelectionModel();
				SelectionModel<String> model2 = ((ComboBox<String>)children.get(3)).getSelectionModel();
				
				Integer dimensions = model1.getSelectedItem();
				if (dimensions != null)
					set.setDimensions(dimensions);
				
				String difficulty = model2.getSelectedItem();
				if (difficulty != null)
					set.setDifficulty(difficulty);
			});
		}
	}
	
	private class CreateReturnToMainMenuButton implements Runnable {
		@Override
		public void run() {
			BorderPane bp = SimpleGUIPuzzle.this.bp;
			Button b = SimpleGUIPuzzle.this.returnToMainMenuButton;
			b.getStyleClass().add("centered");
			SimpleGUIPuzzle.this.options.getChildren().add(b);
			
			b.setOnMouseClicked(ev -> {
				bp.setCenter(SimpleGUIPuzzle.this.mainImage);
				bp.setBottom(SimpleGUIPuzzle.this.mainMenu);
				SimpleGUIPuzzle.this.leftSide.getChildren().clear();
				SimpleGUIPuzzle.this.rightSide.getChildren().clear();
				SimpleGUIPuzzle.this.puzzle = null;
			});
		}
	}
	
	private class CreateSubmitButton implements Runnable {		
		@Override
		public void run() {
			BorderPane bp = SimpleGUIPuzzle.this.bp;
			Settings settings = SimpleGUIPuzzle.this.settings;
			HBox hb = SimpleGUIPuzzle.this.options;
			Button submit = new Button("Submit");
			Button reset = new Button("Reset");
			Button exit = new Button("Exit");
			StackPane left = SimpleGUIPuzzle.this.leftSide;
			StackPane right = SimpleGUIPuzzle.this.rightSide;
			Label invalid1 = new Label("Invalid!");
			Label invalid2 = new Label("Invalid!");
			invalid1.getStyleClass().add("invalid");
			invalid2.getStyleClass().add("invalid");	
			submit.getStyleClass().add("centered");
			hb.getStyleClass().addAll("whiteBack", "centered", "bordered");
			hb.getChildren().addAll(submit, reset, exit);
			
			submit.setOnMouseClicked(ev -> {
				int dimensions = settings.dimensions();
				TextField[][] tfs = SimpleGUIPuzzle.this.textfields.get(dimensions);
				Puzzle puzzle = SimpleGUIPuzzle.this.puzzle;
				char value;
				
				for (int i = 0 ; i < dimensions ; i++) {
					for (int j = 0 ; j < dimensions ; j++) {
						if (puzzle.editableCellAt(i, j)) {
							String text = tfs[i][j].getText();
							TextField tf = tfs[i][j];
							
							if (text == null || text.isEmpty())
								puzzle.deleteValueAt(i, j);
							else {
								value = text.charAt(0);
								
								if (!puzzle.isLegalValue(value))
									tf.getStyleClass().add("redBack");
								else {
									puzzle.setValueAt(value, i, j);
									tf.getStyleClass().add("whiteBack");
								}
							}
						}
					}
				}
				
				if (SimpleGUIPuzzle.this.puzzle != null) {
					if (SimpleGUIPuzzle.this.puzzle.isSolved())
						bp.setCenter(SimpleGUIPuzzle.this.successScreen);
					else {
						if (left.getChildren().isEmpty()) {
							left.getChildren().add(invalid1);
							right.getChildren().add(invalid2);
						}
					}
				}
			});
			
			reset.setOnMouseClicked(ev -> {
				SimpleGUIPuzzle.this.generatePuzzle();
				SimpleGUIPuzzle.this.leftSide.getChildren().clear();
				SimpleGUIPuzzle.this.rightSide.getChildren().clear();
			});
			
			exit.setOnMouseClicked(ev -> {
				System.exit(0);
			});
		}
	}
	
	private class CreatePlayButton implements Runnable {
		@Override
		public void run() {
			Button play = SimpleGUIPuzzle.this.play;
			play.getStyleClass().add("centered");
			
			play.setOnMouseClicked(ev -> {
				SimpleGUIPuzzle.this.generatePuzzle();
				SimpleGUIPuzzle.this.bp.setBottom(SimpleGUIPuzzle.this.options);
				SimpleGUIPuzzle.this.leftSide.getChildren().clear();
				SimpleGUIPuzzle.this.rightSide.getChildren().clear();
			});
		}
	}
	
	private class CreateImage implements Runnable {		
		@Override
		public void run() {
			StackPane mainImage = SimpleGUIPuzzle.this.mainImage;
			ImageView view = new ImageView(new Image("https://www.livesudoku.com/artwork/singlesudoku.png"));
			mainImage.getStyleClass().add("whiteBack");
			mainImage.getChildren().add(view);
		}
	}
	
	private class CreateSideBar implements Runnable {		
		@Override
		public void run() {
			StackPane left = SimpleGUIPuzzle.this.leftSide;
			StackPane right = SimpleGUIPuzzle.this.rightSide;
			
			left.setMaxSize(100, 100);
			left.setMinSize(100, 100);
			left.getStyleClass().addAll("blackBack", "centered");
			
			right.setMaxSize(100, 100);
			right.setMinSize(100, 100);
			right.getStyleClass().addAll("blackBack", "centered");
		}
	}
	
	private class CreateTitle implements Runnable {
		@Override
		public void run() {
			HBox title = SimpleGUIPuzzle.this.title;
			Label label = new Label("Sudoku");
			label.getStyleClass().addAll("titleText", "centered", "whiteBack");
			title.getStyleClass().addAll("whiteBack", "centered", "bordered");
			title.getChildren().add(label);
		}
	}
	
	private class CreateExitButton implements Runnable {
		@Override
		public void run() {
			Button exit = SimpleGUIPuzzle.this.exit;
			exit.getStyleClass().add("centered");
			
			exit.setOnMouseClicked(ev -> {
				System.exit(0);
			});
		}
	}
	
	private class CreateSuccessScreen implements Runnable {
		@Override
		public void run() {
			StackPane screen = SimpleGUIPuzzle.this.successScreen;
			screen.getStyleClass().addAll("greenBack", "centered");
			Label label = new Label("Success!");
			label.getStyleClass().add("successLabel");
			screen.getChildren().add(label);
		}
	}
}