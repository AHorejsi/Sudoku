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
		
		if (info.contains("4x4"))
			board = new Board4x4(rng);
		else if (info.contains("6x6"))
			board = new Board6x6(rng);
		else if (info.contains("8x8"))
			board = new Board8x8(rng);
		else if (info.contains("9x9"))
			board = new Board9x9(rng);
		else if (info.contains("12x12"))
			board = new Board12x12(rng);
		else if (info.contains("16x16"))
			board = new Board16x16(rng);
		
		if (board == null)
			return null;
		
		if (info.contains("basic"))
			puzzle = new BasicPuzzle(board, rng);
		else if (info.contains("easy"))
			puzzle = new EasyPuzzle(board, rng);
		else if (info.contains("medium"))
			puzzle = new MediumPuzzle(board, rng);
		else if (info.contains("hard"))
			puzzle = new HardPuzzle(board, rng);
		else if (info.contains("insane"))
			puzzle = new InsanePuzzle(board, rng);
		
		if (mixers != null) {
			for (Mixer mixer : mixers)
				mixer.mix(board, rng);
		}
		
		return puzzle;
	}
}