package local_game;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import sudoku_game.DefaultRNG;
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
	private Random rng;
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
	 * Creates a {@code SimpleGUIPuzzle}
	 * that initially uses a default
	 * random number generator and no
	 * mixers
	 */
	public SimpleGUIPuzzle() {
		this(null, null);
	}
	
	/**
	 * Creates a {@code SimpleGUIPuzzle}
	 * that initially uses the given
	 * mixers and a default random
	 * number generator
	 * @param mixers The mixers to be used
	 * by this {@code SimpleGUIPuzzle}
	 */
	public SimpleGUIPuzzle(Collection<Mixer> mixers) {
		this(mixers, null);
	}
	
	/**
	 * Creates a {@code SimpleGUIPuzzle}
	 * that initially uses the given mixers
	 * and the given random number
	 * generator
	 * @param mixers The mixers to be
	 * used by this {@code SimpleGUIPuzzle}
	 * @param rng The random number
	 * generator to be used by this
	 * {@code SimpleGUIPuzzle}
	 */
	public SimpleGUIPuzzle(Collection<Mixer> mixers, Random rng) {
		this.getStylesheets().add("local_game/stylesheet.css");
		this.getStyleClass().addAll("centered", "blackBack");
		this.rng = (rng == null) ? DefaultRNG.getDefaultGenerator() : rng;
		
		if (mixers != null)
			this.mixers.addAll(mixers);
		
		this.runGridPaneCreators();
		this.createGUIToUseLater();
		this.setUpBorderPane();
	}
	
	@Override
	public void generatePuzzle() {
		Puzzle puzzle = SimpleGUIPuzzle.factory.createPuzzle(this.settings.toString(), this.rng, this.mixers);
		int dimensions = this.settings.dimensions();
		TextField[][] tfs = this.textfields.get(dimensions);
		GridPane gps = this.gridpanes.get(dimensions);
		this.puzzle = puzzle;
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				tfs[i][j].setText(String.valueOf(puzzle.getValueAt(i, j)));
				tfs[i][j].getStyleClass().retainAll("centered", "textField");
				
				if (puzzle.editableCellAt(i, j)) {
					tfs[i][j].getStyleClass().add("whiteBack");
					tfs[i][j].setEditable(true);
				}
				else {
					tfs[i][j].getStyleClass().add("yellowTextField");
					tfs[i][j].setEditable(false);
				}
			}
		}
		
		this.bp.setCenter(gps);
	}
	
	@Override
	public void setRandomNumberGenerator(Random rng) {
		this.rng = rng;
	}
	
	@Override
	public Collection<Mixer> getMixers() {
		return this.mixers;
	}
	
	private void runGridPaneCreators() {
		GridPaneCreator gpc1 = new GridPane9x9Creator();
		//GridPaneCreator gpc2 = new GridPane16x16Creator();
		
		Thread t1 = new Thread(gpc1);
		//Thread t2 = new Thread(gpc2);
		t1.start();
		//t2.start();
		
		try {
			t1.join();
			//t2.join();
		} catch (InterruptedException ex) {
			throw new InternalError(ex);
		}
		
		this.gridpanes.put(9, gpc1.getGridPane());
		//this.gridpanes.put(16, gpc2.getGridPane());
		this.textfields.put(9, gpc1.getCells());
		//this.textfields.put(16, gpc2.getCells());
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
	
	private void createGUIToUseLater() {
		Thread t1 = new Thread(new CreateSettingsButton());
		Thread t2 = new Thread(new CreateReturnToMainMenuButton());
		Thread t3 = new Thread(new CreateSubmitButton());
		Thread t4 = new Thread(new CreatePlayButton());
		Thread t5 = new Thread(new CreateTitle());
		Thread t6 = new Thread(new CreateExitButton());
		Thread t7 = new Thread(new CreateSideBar());
		Thread t8 = new Thread(new CreateImage());
		Thread t9 = new Thread(new CreateSuccessScreen());
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		
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
		} catch (InterruptedException ex) {
			throw new InternalError(ex);
		}
		
		this.setUpMainMenu();
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
			});
			
			children.get(4).setOnMouseClicked(ev -> {
				bp.setCenter(SimpleGUIPuzzle.this.mainImage);
				int dimensions = ((ComboBox<Integer>)children.get(1)).getSelectionModel().getSelectedItem();
				String difficulty = ((ComboBox<String>)children.get(3)).getSelectionModel().getSelectedItem();
				set.setDimensions(dimensions);
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
			
			b.setOnMouseClicked(ev -> {
				bp.setCenter(SimpleGUIPuzzle.this.mainImage);
				bp.setBottom(SimpleGUIPuzzle.this.mainMenu);
			});
		}
	}
	
	private class CreateSubmitButton implements Runnable {		
		@Override
		public void run() {
			Settings settings = SimpleGUIPuzzle.this.settings;
			BorderPane bp = SimpleGUIPuzzle.this.bp;
			HBox hb = SimpleGUIPuzzle.this.options;
			Button submit = new Button("Submit");
			Button reset = new Button("Reset");
			Button exit = new Button("Exit");
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
							if (tfs[i][j].getText() == null || tfs[i][j].getText().isEmpty())
								puzzle.deleteValueAt(i, j);
							else {
								value = tfs[i][j].getText().charAt(0);
								
								if (!puzzle.isLegalValue(value))
									tfs[i][j].getStyleClass().add("redBack");
								else {
									puzzle.setValueAt(value, i, j);
									tfs[i][j].getStyleClass().add("whiteBack");
								}
							}
						}
					}
				}
				
				if (puzzle.isSolved()) {
					bp.setCenter(SimpleGUIPuzzle.this.successScreen);
					bp.setBottom(SimpleGUIPuzzle.this.returnToMainMenuButton);
				}
				else
					this.addInvalidMessage();
			});
			
			reset.setOnMouseClicked(ev -> {
				SimpleGUIPuzzle.this.generatePuzzle();
			});
			
			exit.setOnMouseClicked(ev -> {
				System.exit(0);
			});
		}
		
		private void addInvalidMessage() {//TODO
			BorderPane bp = SimpleGUIPuzzle.this.bp;
			HBox title = SimpleGUIPuzzle.this.title;
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
			left.setMaxSize(60, 60);
			left.setMinSize(60, 60);
			left.getStyleClass().add("blackBack");
			right.setMaxSize(60, 60);
			right.setMinSize(60, 60);
			right.getStyleClass().add("blackBack");
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
			screen.getChildren().add(label);
		}
	}
}