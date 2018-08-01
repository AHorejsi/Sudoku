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
	private static final PuzzleFactory FACTORY = new LocalFactory();
	private final String FOUR = "4x4";
	private final String SIX = "6x6";
	private final String EIGHT = "8x8";
	private final String NINE = "9x9";
	private final String TWELVE = "12x12";
	private final String SIXTEEN = "16x16";
	private final String BASIC = "basic";
	private final String EASY = "easy";
	private final String MEDIUM = "medium";
	private final String HARD = "hard";
	private final String INSANE = "insane";
	
	private LocalFactory() {}
	
	/**
	 * Returns the single instance
	 * of {@code LocalFactory}
	 * @return The single instance
	 * of {@code LocalFactory}
	 */
	public static PuzzleFactory getInstance() {
		return LocalFactory.FACTORY;
	}
	
	@Override
	public Puzzle createPuzzle(String info, Random rng, Collection<Mixer> mixers) {
		Puzzle puzzle = null;
		Board board = null;
		info = info.toLowerCase();
		
		if (info.contains(this.FOUR))
			board = new Board4x4(rng);
		else if (info.contains(this.SIX))
			board = new Board6x6(rng);
		else if (info.contains(this.EIGHT))
			board = new Board8x8(rng);
		else if (info.contains(this.NINE))
			board = new Board9x9(rng);
		else if (info.contains(this.TWELVE))
			board = new Board12x12(rng);
		else if (info.contains(this.SIXTEEN))
			board = new Board16x16(rng);
		
		if (board == null)
			return null;
		
		if (info.contains(this.BASIC))
			puzzle = new BasicPuzzle(board, rng);
		else if (info.contains(this.EASY))
			puzzle = new EasyPuzzle(board, rng);
		else if (info.contains(this.MEDIUM))
			puzzle = new MediumPuzzle(board, rng);
		else if (info.contains(this.HARD))
			puzzle = new HardPuzzle(board, rng);
		else if (info.contains(this.INSANE))
			puzzle = new InsanePuzzle(board, rng);
		
		if (puzzle == null)
			return null;
		
		if (mixers != null) {
			for (Mixer mixer : mixers)
				mixer.mix(board, rng);
		}
		
		return puzzle;
	}
}