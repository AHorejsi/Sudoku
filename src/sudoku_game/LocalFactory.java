package sudoku_game;

import java.util.Random;

public class LocalFactory implements PuzzleFactory {
	private static LocalFactory factory = new LocalFactory();
	
	private LocalFactory() {}
	
	public static PuzzleFactory getInstance() {
		return LocalFactory.factory;
	}
	
	@Override
	public Puzzle createPuzzle(String info, Random rng) {
		Puzzle puzzle = null;
		Board board = null;
		
		if (info.contains("9x9"))
			board = new Board9x9(rng);
		else if (info.contains("16x16"))
			board = new Board16x16(rng);
		
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
		
		RotateFlipSwapMixer.getInstance().mix(board, rng);
		
		return puzzle;
	}
}