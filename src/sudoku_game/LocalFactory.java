package sudoku_game;

import java.util.Collection;
import java.util.Random;

/**
 * Generates a new Sudoku puzzle
 * completely from scratch that
 * is ready for a player to
 * solve
 * @author Alex Horejsi
 */
public class LocalFactory implements PuzzleFactory {
	private static PuzzleFactory factory = new LocalFactory();
	private String four = "4x4";
	private String six = "6x6";
	private String eight = "8x8";
	private String nine = "9x9";
	private String twelve = "12x12";
	private String sixteen = "16x16";
	private String basic = "basic";
	private String easy = "easy";
	private String medium = "medium";
	private String hard = "hard";
	private String insane = "insane";
	
	private LocalFactory() {}
	
	/**
	 * Returns the single instance
	 * of {@code LocalFactory}
	 * @return The single instance
	 * of {@code LocalFactory}
	 */
	public static PuzzleFactory getInstance() {
		return LocalFactory.factory;
	}
	
	@Override
	public Puzzle createPuzzle(String info, Random rng, Collection<Mixer> mixers) {
		Puzzle puzzle = null;
		Board board = null;
		info = info.toLowerCase();
		
		if (info.contains(this.four))
			board = new Board4x4(rng);
		else if (info.contains(this.six))
			board = new Board6x6(rng);
		else if (info.contains(this.eight))
			board = new Board8x8(rng);
		else if (info.contains(this.nine))
			board = new Board9x9(rng);
		else if (info.contains(this.twelve))
			board = new Board12x12(rng);
		else if (info.contains(this.sixteen))
			board = new Board16x16(rng);
		
		if (board == null)
			return null;
		
		if (info.contains(this.basic))
			puzzle = new BasicPuzzle(board, rng);
		else if (info.contains(this.easy))
			puzzle = new EasyPuzzle(board, rng);
		else if (info.contains(this.medium))
			puzzle = new MediumPuzzle(board, rng);
		else if (info.contains(this.hard))
			puzzle = new HardPuzzle(board, rng);
		else if (info.contains(this.insane))
			puzzle = new InsanePuzzle(board, rng);
		
		if (mixers != null) {
			for (Mixer mixer : mixers)
				mixer.mix(board, rng);
		}
		
		return puzzle;
	}
}