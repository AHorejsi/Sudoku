package local_game;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import sudoku_game.DefaultRNG;
import sudoku_game.Mixer;
import sudoku_game.Puzzle;
import sudoku_game.PuzzleFactory;

public class SimpleGUIPuzzle extends GUIPuzzle {
	private PuzzleFactory factory;
	private Puzzle puzzle;
	private Settings settings;
	private Random rng;
	private BorderPane bp = new BorderPane();
	private Map<Integer, GridPane> gridpanes = new HashMap<Integer, GridPane>();
	private Map<Integer, TextField[][]> textfields = new HashMap<Integer, TextField[][]>();
	private List<Mixer> mixers = new LinkedList<Mixer>();
	private static Background white = new Background(new BackgroundFill(Color.WHITE, null, null));
	private static Background illegalValueColor = new Background(new BackgroundFill(Color.RED, null, null));
	private static Background uneditable = new Background(new BackgroundFill(Color.YELLOW, null, null));
	private static ImageView view = new ImageView(new Image("https://www.livesudoku.com/artwork/singlesudoku.png"));
	
	public SimpleGUIPuzzle(PuzzleFactory factory, Collection<Mixer> mixers, Random rng) {
		this(factory, mixers, new Settings(9, "medium"), rng);
	}
	
	public SimpleGUIPuzzle(PuzzleFactory factory, Collection<Mixer> mixers, Settings settings, Random rng) {
		this.getStylesheets().add("local_game/Styling.css");
		this.factory = Objects.requireNonNull(factory);
		this.settings = Objects.requireNonNull(settings);
		this.rng = (rng == null) ? DefaultRNG.getDefaultGenerator() : rng;
		
		if (mixers != null)
			this.mixers.addAll(mixers);
		
		this.runGridPaneCreators();
		this.runSubGUIs();
		System.out.println(Title.getTitle() + " " + SettingsScreen.getSettingsScreen() + " " + SuccessScreen.getSuccessScreen());
		this.setUpBorderPane();
	}
	
	@Override
	public void generatePuzzle() {
		Puzzle puzzle = this.factory.createPuzzle(this.settings.toString(), this.rng, this.mixers);
		int dimensions = this.settings.getDimensions();
		TextField[][] tfs = this.textfields.get(dimensions);
		GridPane gps = this.gridpanes.get(dimensions);
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				tfs[i][j].setText(String.valueOf(puzzle.getValueAt(i, j)));
				
				if (puzzle.editableCellAt(i, j))
					tfs[i][j].setBackground(SimpleGUIPuzzle.white);
				else {
					tfs[i][j].setBackground(SimpleGUIPuzzle.uneditable);
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
	public void setPuzzleFactory(PuzzleFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void addMixer(Mixer mixer) {
		this.mixers.add(mixer);
	}
	
	@Override
	public void removeMixer(Mixer mixer) {
		this.mixers.remove(mixer);
	}
	
	@Override
	public Collection<Mixer> getMixers() {
		return this.mixers;
	}
	
	private void runGridPaneCreators() {
		GridPaneCreator gpc1 = new GridPane9x9Creator(this);
		GridPaneCreator gpc2 = new GridPane16x16Creator(this);
		
		Thread t1 = new Thread(gpc1);
		Thread t2 = new Thread(gpc2);
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException ex) {
			throw new InternalError();
		}
		
		this.gridpanes.put(9, gpc1.getGridPane());
		this.gridpanes.put(16, gpc2.getGridPane());
		this.textfields.put(9, gpc1.getCells());
		this.textfields.put(16, gpc2.getCells());
	}
	
	private void runSubGUIs() {
		if (Title.getTitle() == null || 
				SettingsScreen.getSettingsScreen() == null || 
				SuccessScreen.getSuccessScreen() == null) {
			
			Thread t1 = new Thread(new SuccessScreen());
			Thread t2 = new Thread(new Title());
			Thread t3 = new Thread(new SettingsScreen());
			t1.start();
			t2.start();
			t3.start();
			
			try {
				t1.join();
				t2.join();
				t3.join();
			} catch (InterruptedException ex) {
				throw new InternalError();
			}
		}
	}
	
	private void setUpBorderPane() {
		BorderPane bp = new BorderPane();
		
		HBox mainMenu = this.setUpMainMenu();
		BorderPane.setAlignment(mainMenu, Pos.CENTER);
		
		bp.setTop(Title.getTitle());
		bp.setCenter(SimpleGUIPuzzle.view);
		bp.setBottom(mainMenu);
		
		this.bp = bp;
		this.getChildren().add(bp);
	}
	
	private HBox setUpMainMenu() {
		HBox mainMenu = new HBox();
		Button play = this.setUpPlayButton();
		Button settingsButton = this.setUpSettingsButton();
		mainMenu.getChildren().addAll(play, settingsButton);
		
		return mainMenu;
	}
	
	private Button setUpPlayButton() {
		Button play = new Button("Play New Puzzle");
		
		play.setOnMouseClicked(ev -> {
			this.generatePuzzle();
			this.bp.setBottom(this.setUpButtonBox());
		});
		
		return play;
	}
	
	@SuppressWarnings("unchecked")
	private Button setUpSettingsButton() {
		Button settingsButton = new Button("Settings");
		GridPane settingsScreen = SettingsScreen.getSettingsScreen();
		List<Node> children = settingsScreen.getChildren();
		
		settingsButton.setOnMouseClicked(ev -> {
			this.bp.setCenter(settingsScreen);
		});
		
		children.get(4).setOnMouseClicked(ev -> {
			int dimensions = ((ComboBox<Integer>)children.get(1)).getSelectionModel().getSelectedItem();
			String difficulty = ((ComboBox<String>)children.get(3)).getSelectionModel().getSelectedItem();
			this.settings.setDimensions(dimensions);
			this.settings.setDifficulty(difficulty);
		});
		
		return settingsButton;
	}
	
	private HBox setUpButtonBox() {
		HBox hb = new HBox();
		hb.setAlignment(Pos.CENTER);
		hb.setBackground(SimpleGUIPuzzle.white);
		Button submit = new Button("Submit");
		hb.getChildren().add(submit);
		
		submit.setOnMouseClicked(ev -> {
			int dimensions = this.settings.getDimensions();
			TextField[][] tfs = this.textfields.get(dimensions);
			char value;
			
			for (int i = 0 ; i < dimensions ; i++) {
				for (int j = 0 ; j < dimensions ; j++) {
					value = tfs[i][j].getText().charAt(0);
					
					if (!this.puzzle.isLegalValue(value))
						tfs[i][j].setBackground(SimpleGUIPuzzle.illegalValueColor);
					else
						this.puzzle.setValueAt(value, i, j);
				}
			}
			
			if (this.puzzle.isSolved()) {
				this.bp.setCenter(SuccessScreen.getSuccessScreen());
				this.bp.setBottom(this.setUpReturnToMainMenuButton());
			}
		});
		
		return hb;
	}
	
	private Button setUpReturnToMainMenuButton() {
		Button b = new Button("Return to Main Menu");
		
		b.setOnMouseClicked(ev -> {
			this.bp.setCenter(SimpleGUIPuzzle.view);
			this.bp.setBottom(this.setUpMainMenu());
		});
		
		return b;
	}
}