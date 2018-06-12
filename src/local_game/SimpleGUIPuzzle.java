package local_game;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import sudoku_game.DefaultRNG;
import sudoku_game.Mixer;
import sudoku_game.Puzzle;
import sudoku_game.PuzzleFactory;

public class SimpleGUIPuzzle extends GUIPuzzle {
	private PuzzleFactory factory;
	private Puzzle puzzle;
	private Settings settings;
	private Random rng;
	private List<Mixer> mixers = new LinkedList<Mixer>();
	private BorderPane bp = new BorderPane();
	private Map<Integer, GridPane> gridpanes = new HashMap<Integer, GridPane>();
	private Map<Integer, TextField[][]> textfields = new HashMap<Integer, TextField[][]>();
	private Button play = new Button("Play New Puzzle");
	private Button settingsButton = new Button("Settings");
	private Button returnToMainMenuButton = new Button("Return to Main Menu");
	private HBox submit = new HBox();
	private HBox mainMenu = new HBox();
	private static ImageView view = new ImageView(new Image("https://www.livesudoku.com/artwork/singlesudoku.png"));
	
	public SimpleGUIPuzzle(PuzzleFactory factory, Collection<Mixer> mixers, Random rng) throws NullPointerException {
		this.getStylesheets().add("local_game/stylesheet.css");
		this.getStyleClass().add("centered");
		this.factory = Objects.requireNonNull(factory);
		this.rng = (rng == null) ? DefaultRNG.getDefaultGenerator() : rng;
		this.settings = new Settings();
		
		if (mixers != null)
			this.mixers.addAll(mixers);
		
		this.runGridPaneCreators();
		this.runSubGUIs();
		this.createGUIToUseLater();
		this.setUpBorderPane();
	}
	
	@Override
	public void generatePuzzle() {
		Puzzle puzzle = this.factory.createPuzzle(this.settings.toString(), this.rng, this.mixers);
		int dimensions = this.settings.dimensions();
		TextField[][] tfs = this.textfields.get(dimensions);
		GridPane gps = this.gridpanes.get(dimensions);
		
		for (int i = 0 ; i < dimensions ; i++) {
			for (int j = 0 ; j < dimensions ; j++) {
				tfs[i][j].setText(String.valueOf(puzzle.getValueAt(i, j)));
				
				if (puzzle.editableCellAt(i, j))
					tfs[i][j].getStyleClass().add("whiteBack");
				else {
					tfs[i][j].getStyleClass().addAll("yellowTextField", "textField");
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
		List<Thread> threads = new LinkedList<Thread>();
		threads.add(new Thread(this.settings));
		
		if (Title.getTitle() == null || SuccessScreen.getSuccessScreen() == null) {
			threads.add(new Thread(new Title()));
			threads.add(new Thread(new SuccessScreen()));
		}
		
		for (Thread th : threads)
			th.start();
		
		try {
			for (Thread th : threads)
				th.join();;
		} catch (InterruptedException ex) {
			throw new InternalError();
		}
	}
	
	private void setUpBorderPane() {
		BorderPane bp = new BorderPane();
		
		bp.setTop(Title.getTitle());
		bp.setCenter(SimpleGUIPuzzle.view);
		bp.setBottom(this.mainMenu);
		bp.getStyleClass().add("centered");
		
		this.bp = bp;
		this.getChildren().add(bp);
	}
	
	private void setUpMainMenu() {
		HBox mainMenu = new HBox();
		mainMenu.getStyleClass().add("bordered");
		mainMenu.getChildren().addAll(this.play, this.settingsButton);
		this.mainMenu = mainMenu;
	}
	
	private void createGUIToUseLater() {
		Thread t1 = new Thread(new CreateSettingsButton());
		Thread t2 = new Thread(new CreateReturnToMainMenuButton());
		Thread t3 = new Thread(new CreateSubmitButton());
		Thread t4 = new Thread(new CreatePlayButton());
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (InterruptedException ex) {
			throw new InternalError();
		}
		
		this.setUpMainMenu();
	}
	
	private class CreateSettingsButton implements Runnable {
		@Override
		public void run() {
			Settings set = SimpleGUIPuzzle.this.settings;
			BorderPane bp = SimpleGUIPuzzle.this.bp;
			List<Node> children = ((Pane)set.getChildren().get(0)).getChildren();
			
			SimpleGUIPuzzle.this.settingsButton.setOnMouseClicked(ev -> {
				bp.setCenter(set);
			});
			
			children.get(4).setOnMouseClicked(ev -> {
				bp.setCenter(SimpleGUIPuzzle.this.mainMenu);
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
				bp.setCenter(SimpleGUIPuzzle.view);
				bp.setBottom(SimpleGUIPuzzle.this.mainMenu);
			});
		}
	}
	
	private class CreateSubmitButton implements Runnable {
		@Override
		public void run() {
			Settings settings = SimpleGUIPuzzle.this.settings;
			Puzzle puzzle = SimpleGUIPuzzle.this.puzzle;
			BorderPane bp = SimpleGUIPuzzle.this.bp;
			HBox hb = SimpleGUIPuzzle.this.submit;
			Button submit = new Button("Submit");
			submit.getStyleClass().add("centered");
			hb.getStyleClass().addAll("whiteBack", "centered", "bordered");
			hb.getChildren().add(submit);
			
			submit.setOnMouseClicked(ev -> {
				int dimensions = settings.dimensions();
				TextField[][] tfs = SimpleGUIPuzzle.this.textfields.get(dimensions);
				char value;
				
				for (int i = 0 ; i < dimensions ; i++) {
					for (int j = 0 ; j < dimensions ; j++) {
						value = tfs[i][j].getText().charAt(0);
						
						if (!puzzle.isLegalValue(value))
							tfs[i][j].getStyleClass().add("redBack");
						else
							puzzle.setValueAt(value, i, j);
					}
				}
				
				if (puzzle.isSolved()) {
					bp.setCenter(SuccessScreen.getSuccessScreen());
					bp.setBottom(SimpleGUIPuzzle.this.returnToMainMenuButton);
				}
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
				SimpleGUIPuzzle.this.bp.setBottom(SimpleGUIPuzzle.this.submit);
			});
		}
	}
}